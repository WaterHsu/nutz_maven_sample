package news.mongodb;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;


/**
 * @author WaterHsu@xiu8.com
 * @version 2014年10月15日
 */
public class App {

	public static void main(String args[]){
		try{
			Mongo mongo = new Mongo("localhost", 27017);
			DB db = mongo.getDB("new_test");
			DBCollection collection = db.getCollection("mm");
			BasicDBObject document = new BasicDBObject();
			document.put("id", "1001");
			document.put("msg", "ffff");
			collection.insert(document);
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("id", "1001");
			DBCursor cursor = collection.find(searchQuery);
			while(cursor.hasNext()){
				System.out.println(cursor.next());
			}
			
			Map<String, Object> documentMap = new HashMap<String, Object>();
			documentMap.put("id", 1000);
			documentMap.put("name", "LiLi");
			documentMap.put("date", new Date());
			documentMap.put("keys", true);
			collection.insert(new BasicDBObject(documentMap));
			
			String json = "{'id':11111, 'name':'eefefe', 'active':'true'}";
			DBObject dbObject = (DBObject)JSON.parse(json);
			collection.insert(dbObject);
			
			DBCursor cursor2 = collection.find();
			while(cursor2.hasNext()){
				System.out.println(cursor2.next());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
