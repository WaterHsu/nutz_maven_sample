package news.mongodb_manager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年10月16日
 */
public class MongoDao {

	private DB db;
	
	public MongoDao(DB db){
		this.db = db;
	}
	
	/**
	 * 根据集合名获得一个Collection集合
	 * @param collName
	 * @return
	 */
	public DBCollection getSingleCollection(String collName){
		return db.getCollection(collName);
	}
	
	/**
	 * 获得所有集合的名字
	 * @return
	 */
	public Set<String> getCollectionNames(){
		Set<String> collNames = db.getCollectionNames();
		return collNames;
	}
	
	/**
	 * 获得所有的集合
	 * @return
	 */
	public Set<DBCollection> getCollections(){
		Set<String> collNames = db.getCollectionNames();
		Set<DBCollection> collections = new HashSet<DBCollection>();
		for(String collName : collNames){
			collections.add(db.getCollection(collName));
		}
		return collections;
	}
	
	/**
	 * 插入一条map类型数据
	 * @param collName 集合名
	 * @param map  要插入的数据
	 */
	public void addData(String collName, Map<String, Object> map){
		getSingleCollection(collName).insert(new BasicDBObject(map));
	}
	
	/**
	 * 插入一条json类型数据
	 * @param collName 集合名
	 * @param jsonStr  json格式字符串
	 */
	public void addData(String collName, String jsonStr){
		DBObject dbObject = (DBObject)JSON.parse(jsonStr);
		getSingleCollection(collName).insert(dbObject);
	}
	
	/**
	 * 查询某个集合中的第1条数据
	 * @param collName
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map findFirstDocument(String collName){
		 DBCollection collection = getSingleCollection(collName);
		 DBObject dbObject = collection.findOne();
		 return dbObject.toMap();
	}
	
	/**
	 * 查询某个集合中的所有记录
	 * @param collName 集合名
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> findAllDocuments(String collName){
		DBCollection collection = getSingleCollection(collName);
		DBCursor cursor = collection.find();
		return returnValue(cursor);
	}
	
	/**
	 * 在某个集合中根据条件查询集合
	 * 类似于 select * from ** where *=* and *=*
	 * @param collName  集合名
	 * @param condition  查询条件  
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> findDocumentsWithCondition(String collName, Map<String, Object> condition){
		BasicDBObject query = new BasicDBObject(condition);
		DBCursor cursor = find(collName, query);
		return returnValue(cursor);
	}
	
	/**
	 * 根据条件查询以及排序
	 * 类似于 select * from ** where *=* and *=* order by **
	 * @param collName  集合名
	 * @param condition  查询条件
	 * @param sortName  要排序的字段
	 * @param index  1顺序  -1倒序
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> findDocumentsWithCondition(String collName, Map<String, Object> condition, String sortName, int index){
		BasicDBObject query = new BasicDBObject(condition);
		DBCursor cursor = find(collName, query);
		cursor = cursor.sort(new BasicDBObject(sortName, index));
		return returnValue(cursor);
	}
	
	/**
	 * 根据条件查询多少条数据并对这些数据排序  
	 * 根据条件查询排序后选择多少条数据
	 * 类似于 select top 100 * from ** where *=* and *=* order by **
	 * @param collName  集合名
	 * @param condition  查询条件
	 * @param sortName  要排序的字段
	 * @param limit  限制要查询的条数
	 * @param index  1顺序  -1倒序
	 * @param bf  true排序之前选择多少条数据    false排序之后选择多少条数据
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> findDocumentsWithCondition(String collName, Map<String, Object> condition, String sortName, int limit, int index, boolean fb){
		BasicDBObject query = new BasicDBObject(condition);
		DBCursor cursor = find(collName, query);
		if(fb){
			cursor = cursor.limit(limit).sort(new BasicDBObject(sortName, index));
		}else{
			cursor = cursor.sort(new BasicDBObject(sortName, index)).limit(limit);
		}
		return returnValue(cursor);
	}
	
	/**
	 * 查询某个字段不等于某个值得所有记录
	 * 类似于 select * from ** where *>* and *<*
	 * 类似于 select * from ** where * in *
	 * @param collName
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> findDocumentsMoreCondition(String collName, String fieldName, List<Object> values, String type){
		if(values.size() < 2){
			System.out.println("参数不够");
			return null;
		}
		BasicDBObject query = new BasicDBObject();
		if("ne".equals(type)){
			query.put(fieldName, new BasicDBObject("$ne", values.get(0)));
		}else if("gt".equals(type)){
			query.put(fieldName, new BasicDBObject("$gt", values.get(0)));
		}else if("gte".equals(type)){
			query.put(fieldName, new BasicDBObject("$gte", values.get(0)));
		}else if("lt".equals(type)){
			query.put(fieldName, new BasicDBObject("$lt", values.get(0)));
		}else if("lte".equals(type)){
			query.put(fieldName, new BasicDBObject("$lte", values.get(0)));
		}else if("gtlt".equals(type)){
			query.put(fieldName, new BasicDBObject("$gt", values.get(0)).append("$lt", values.get(0)));
		}else if("gtelte".equals(type)){
			query.put(fieldName, new BasicDBObject("$gte", values.get(0)).append("$lte", values.get(0)));
		}else if("gtlte".equals(type)){
			query.put(fieldName, new BasicDBObject("$gt", values.get(0)).append("$lte", values.get(0)));
		}else if("gtelt".equals(type)){
			query.put(fieldName, new BasicDBObject("$gte", values.get(0)).append("$lt", values.get(0)));
		}else if("in".equals(type)){
			query.put(fieldName, new BasicDBObject("$in", values.toArray()));
		}else if("nin".equals(type)){
			query.put(fieldName, new BasicDBObject("$nin", values.toArray()));
		}
		
		DBCursor cursor = find(collName, query);
		return returnValue(cursor);
	}
	
	/**
	 * 更新记录
	 * @param collName  集合名
	 * @param condition  要更新的数据
	 * @param values   更新的值
	 * @param operation  更新的动作 inc  set 
	 */
	@SuppressWarnings("rawtypes")
	public void updateDocuments(String collName, Map<String, Object> condition, Map<String, Object> values, String operation){
		BasicDBObject query = new BasicDBObject(condition);
		BasicDBObject valueObject = new BasicDBObject();
		BasicDBObject temp = new BasicDBObject();
		for(Entry entry : values.entrySet()){
			temp.append(entry.getKey().toString(), entry.getValue());
		}
		valueObject.append("$" + operation, temp);
		update(collName, query, valueObject, false, false);
	} 
	
	/**
	 * 公共查询方法
	 * @param collName
	 * @param query
	 * @return
	 */
	private DBCursor find(String collName, BasicDBObject query){
		DBCollection collection = getSingleCollection(collName);
		DBCursor cursor = collection.find(query);
		return cursor;
	}
	
	/**
	 * 公共返回方法
	 * @param dbCursor
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private List<Map> returnValue(DBCursor cursor){
		List<Map> list = new ArrayList<Map>();
		while(cursor.hasNext()){
			list.add(cursor.next().toMap());
		}
		return list;
	}
	
	/**
	 * 公共更新方法
	 * @param query
	 * @param value
	 */
	private void update(String collName, BasicDBObject query, BasicDBObject value, boolean updateSet, boolean multi){
		DBCollection collection = getSingleCollection(collName);
		collection.update(query, value, updateSet, multi);
	}
}
