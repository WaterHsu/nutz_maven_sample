namespace java com.my.maven.nutz.pro.my_maven_nutz_sample.thrift_mysql

#thrift -gen java xxx.thrift  运行命令


struct Article{
	1:required i32 id
	2:required string title
	3:required string abstractContent
	4:required string content
	5:required string username
	6:required string time
}

struct User{
	1:required i32 id
	2:required string username
	3:required string password
	4:required map<string, string> info
	5:required list<string> emails
}


service ThriftMysqlService{
	void addUser(1:User user)
	void addArticle(1:Article article)
	list<User> queryAllUser()
	set<Article> queryAllArticle()
	User queryOneUser(1:i32 id)
	map<string, string> queryOneArticle(1:i32 id)
}





