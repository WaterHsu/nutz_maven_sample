package com.my.maven.nutz.pro.my_maven_nutz_sample.thrift_mysql.dao;

import java.util.List;
import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年8月29日
 */
@IocBean
public class BaseService {
	@Inject
	private Dao dao;
	
	private static Log log = Logs.get();
	
	/**
	 * 插入User
	 * @param map
	 */
	public void insertUser(Map<String, String> map){
		Sql sql = dao.sqls().create("user_insert");
		sql.params().set("id", Integer.valueOf(map.get("id")));
		sql.params().set("username", map.get("username"));
		sql.params().set("password", map.get("password"));
		sql.params().set("info", map.get("info"));
		sql.params().set("emails", map.get("emails"));
		dao.execute(sql);
		log.info("插入一条user数据");
	}
	
	/**
	 * 查询所有user数据
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryAllUsers(){
		Sql sql = dao.sqls().create("user_selectAll");
		sql.setCallback(Sqls.callback.maps());
		dao.execute(sql);
		List<Map> list = sql.getList(Map.class);
		return list;
	}
	
	/**
	 * 查询一条user数据
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map queryOneUser(int id){
		Sql sql = dao.sqls().create("user_selectOne");
		sql.params().set("id", id);
		sql.setCallback(Sqls.callback.maps());
		dao.execute(sql);
		List<Map> list = sql.getList(Map.class);
		if(null == list || list.size() <= 0){
			return null;
		}
		return list.get(0);
	}
	
	/**
	 * 插入Article数据
	 * @param map
	 */
	public void insertArticle(Map<String, String> map){
		Sql sql = dao.sqls().create("article_insert");
		sql.params().set("id", Integer.valueOf(map.get("id")));
		sql.params().set("title", map.get("title"));
		sql.params().set("abstractContent", map.get("abstractContent"));
		sql.params().set("content", map.get("content"));
		sql.params().set("username", map.get("username"));
		sql.params().set("times", map.get("times"));
		
		dao.execute(sql);
		
	}
	
	/**
	 * 查询所有文章数据
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryAllArticles(){
		Sql sql = dao.sqls().create("article_selectAll");
		sql.setCallback(Sqls.callback.maps());
		dao.execute(sql);
		List<Map> list = sql.getList(Map.class);
		
		return list;
	}
	
	/**
	 * 查询一条article数据
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map queryOneArticle(int id){
		Sql sql = dao.sqls().create("article_selectOne");
		sql.params().set("id", id);
		sql.setCallback(Sqls.callback.maps());
		dao.execute(sql);
		List<Map> list = sql.getList(Map.class);
		if(null == list || list.size() <= 0){
			return null;
		}
		
		return list.get(0);
	}
}
