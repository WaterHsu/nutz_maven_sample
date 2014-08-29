var ioc = {
		dataSource:{
			type: "com.alibaba.druid.pool.DruidDataSource",
			events: {create: 'init', depose: 'close'},
			fields: {
				driverClassName: "com.mysql.jdbc.Driver",
				url: "jdbc:mysql://127.0.0.1:3309/thrift_mysql",
				username: "root",
				password: "475356336"
			}
		},
		sqlManager:{
			type: "org.nutz.dao.impl.FileSqlManager",
			args: ["sqls.sql"]
		},
		dao:{
			type: "org.nutz.dao.impl.NutDao",
			args:[{
				refer: "dataSource",
			},{
				refer: "sqlManager"
			}]
		}
}