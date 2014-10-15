/**
 * 秀吧网络科技有限公司版权所有
 * Copyright (C) xiu8 Corporation. All Rights Reserved
 */
package news.nutz_mongodb;

import java.io.Serializable;

import org.nutz.mongo.annotation.Co;
import org.nutz.mongo.annotation.CoField;
import org.nutz.mongo.annotation.CoId;
import org.nutz.mongo.annotation.CoIdType;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年10月15日
 */
@Co
public class PetFood{
	
	@CoId(value = CoIdType.AUTO_INC)
	private int id;
	
	@CoField
	private int price;

	@CoField
	private String name;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
