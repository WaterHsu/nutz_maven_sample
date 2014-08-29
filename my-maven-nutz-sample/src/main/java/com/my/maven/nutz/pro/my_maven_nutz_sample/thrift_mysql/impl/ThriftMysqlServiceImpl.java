package com.my.maven.nutz.pro.my_maven_nutz_sample.thrift_mysql.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.thrift.TException;
import org.nutz.json.Json;

import com.my.maven.nutz.pro.my_maven_nutz_sample.thrift_mysql.dao.BaseService;
import com.my.maven.nutz.pro.my_maven_nutz_sample.thrift_mysql.rpc.Article;
import com.my.maven.nutz.pro.my_maven_nutz_sample.thrift_mysql.rpc.ThriftMysqlService.Iface;
import com.my.maven.nutz.pro.my_maven_nutz_sample.thrift_mysql.rpc.User;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年8月29日
 */
public class ThriftMysqlServiceImpl implements Iface {

	private BaseService baseService = new BaseService();
	
	@Override
	public void addUser(User user) throws TException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", String.valueOf(user.id));
		map.put("username", user.username);
		map.put("password", user.password);
		map.put("info", Json.toJson(user.info));
		map.put("emails", Json.toJson(user.emails));
		
		baseService.insertUser(map);
	}

	@Override
	public void addArticle(Article article) throws TException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", String.valueOf(article.id));
		map.put("title", article.title);
		map.put("abstractContent", article.abstractContent);
		map.put("content", article.content);
		map.put("username", article.username);
		map.put("times", article.time);
	}

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public List<User> queryAllUser() throws TException {
		List<Map> list = baseService.queryAllUsers();
		List<User> retList = new ArrayList<User>();
		
		for(Map map : list){
			User user = new User();
			user.id = Integer.valueOf(map.get("id").toString());
			user.username = map.get("username").toString();
			user.password = map.get("password").toString();
			user.info = Json.fromJson(Map.class, map.get("info").toString());
			user.emails = Json.fromJson(List.class, map.get("emails").toString());
			
			retList.add(user);
		}
		
		return retList;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Set<Article> queryAllArticle() throws TException {
		List<Map> list = baseService.queryAllArticles();
		Set<Article> retSet = new HashSet<Article>();
		
		for(Map map : list){
			Article article = new Article();
			article.id = Integer.valueOf(map.get("id").toString());
			article.title = map.get("title").toString();
			article.abstractContent = map.get("abstractContent").toString();
			article.username = map.get("username").toString();
			article.time = map.get("times").toString();
			
			retSet.add(article);
		}
		
		return retSet;
	}

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public User queryOneUser(int id) throws TException {
		Map map = baseService.queryOneUser(id);
		User user = new User();
		user.id = Integer.valueOf(map.get("id").toString());
		user.username = map.get("username").toString();
		user.password = map.get("password").toString();
		user.info = Json.fromJson(Map.class, map.get("info").toString());
		user.emails = Json.fromJson(List.class, map.get("emails").toString());
		
		return user;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, String> queryOneArticle(int id) throws TException {
		return (Map<String, String>)baseService.queryOneArticle(id);
	}

		
}
