package com.my.maven.nutz.pro.my_maven_nutz_sample.thrift_mysql;

import org.nutz.dao.Dao;
import org.nutz.ioc.Ioc;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年8月29日
 */

@Modules(scanPackage = true)
@IocBy(type = ComboIocProvider.class, args={"*org.nutz.ioc.loader.json.JsonLoader",
	                                          "application.js",
	                                          "*org.nutz.ioc.loader.annotation.AnnotationIocLoader"})
@SetupBy(MainModule.class)
public class MainModule implements Setup, Runnable {

	private Log log = Logs.getLog("MainModule");
	private Ioc ioc;
	
	@Override
	public void run() {
		try{
			ioc.get(Dao.class, "dao");
			log.debug("数据库初始化成功!");
			
		}catch(Exception e){
			log.fatal("数据库连接初始化失败!!! ", e);
		}
	}

	@Override
	public void init(NutConfig nc) {
		ioc = nc.getIoc();
		Thread t = new Thread(this);
		t.setPriority(Thread.MAX_PRIORITY);
		t.start();
	}

	@Override
	public void destroy(NutConfig nc) {
		// TODO Auto-generated method stub
		
	}
	
}
