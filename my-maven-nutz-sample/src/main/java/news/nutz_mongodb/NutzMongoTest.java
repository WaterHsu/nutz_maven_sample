package news.nutz_mongodb;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.nutz.lang.random.R;
import org.nutz.mongo.MongoConnector;
import org.nutz.mongo.MongoDao;
import org.nutz.mongo.util.Moo;
import org.nutz.json.Json;

import com.mongodb.MongoException;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年10月15日
 */
public class NutzMongoTest {

	private static MongoDao dao;
	
	private static MongoConnector mongoConn;
	
	static{
		try {
			mongoConn = new MongoConnector("127.0.0.1", 27017);
			dao = mongoConn.getDao("my_nutz_mongo");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MongoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void insert(){
		dao.create(Pet.class, true);
		for(int i = 0; i < 5; i++){
			Pet pet = new Pet("et_" + i, R.random(15, 25), "19890509", new Date());
			dao.save(pet);
		}
	}
	
	public static void query(){
		dao.create(Pet.class, false);
		List<Pet> pets = dao.find(Pet.class, Moo.NEW("name", "et_2"),  null);
		
		for(Pet pet : pets){
			System.out.println(Json.toJson(pet).toString());
		}
		
		System.out.println("==============================");
		
		pets = dao.find(Pet.class, Moo.NEW().ne("name", "et_2"), null);
		
		for(Pet pet : pets){
			System.out.println(Json.toJson(pet).toString());
		}
		
		System.out.println("==============================");
		
		String id = dao.findOne(Pet.class, Moo.NEW("name", "et_1")).getId();
		Moo q = Moo.LTE(new ObjectId(id));
		pets = dao.find(Pet.class, q, null);
		
		for(Pet pet : pets){
			System.out.println(Json.toJson(pet).toString());
		}
		
		System.out.println("==============================");
	}
	
	public static void findAndModify(){
		dao.create(Pet.class, false);
		Pet pet = dao.findAndModify(Pet.class, Moo.NEW("name", "et_0"), Moo.SET("age", 666));
		
		System.out.println(Json.toJson(pet).toString());
		
		pet = dao.findAndRemove(Pet.class, Moo.NEW("name", "et_4"));
	}
	
	public static void update(){
		PetFood food = new PetFood();
		food.setName("grass");
		food.setPrice(100);
		dao.create(PetFood.class, true);
		dao.save(food);
		
		dao.create(Pet.class, false);
		Pet pet = dao.save(new Pet("fish", 12, "20000505", new Date()));
		
		dao.updateById(Pet.class, pet.getId(), Moo.NEW().push("pet_food", food));
		
		pet = dao.findById(Pet.class, pet.getId());
		System.out.println(Json.toJson(pet).toString());
	}
	
	public static void main(String args[]){
		//NutzMongoTest.insert();
	//	NutzMongoTest.update();
	//	NutzMongoTest.query();
		System.out.println("+++++++++++++++++++++++++");
		NutzMongoTest.findAndModify();
	}
}
