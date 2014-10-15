package news.nutz_mongodb;

import java.util.Date;

import org.nutz.mongo.annotation.Co;
import org.nutz.mongo.annotation.CoField;
import org.nutz.mongo.annotation.CoId;
import org.nutz.mongo.annotation.CoIdType;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年10月15日
 */
@Co(value = "pet")
public class Pet {

	@CoId(CoIdType.UU64)
	private String id;
	
	@CoField(value = "name")
	private String name;
	
	@CoField(value = "age")
	private int age;
	
	@CoField(value = "birthday")
	private String birthday;
	
	@CoField(value = "createTime")
	private Date createTime;
	
	@CoField(value = "pet_friend")
	private Pet[] petFriends;
	
	@CoField(value = "pet_food")
	private PetFood[] foods;
	
	public Pet(){
		
	}
	
	public Pet(String name, int age, String birthday, Date createTime){
		this.name = name;
		this.age = age;
		this.birthday = birthday;
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Pet[] getPetFriends() {
		return petFriends;
	}

	public void setPetFriends(Pet[] petFriends) {
		this.petFriends = petFriends;
	}

	public PetFood[] getFoods() {
		return foods;
	}

	public void setFoods(PetFood[] foods) {
		this.foods = foods;
	}
}
