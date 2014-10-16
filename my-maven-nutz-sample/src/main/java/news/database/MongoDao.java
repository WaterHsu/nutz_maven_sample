/**
 * 秀吧网络科技有限公司版权所有
 * Copyright (C) xiu8 Corporation. All Rights Reserved
 */
package news.database;

import java.util.List;
import java.util.Map;

import com.mongodb.Mongo;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年10月16日
 */
public class MongoDao implements DBDao {
	
	private Mongo mongo;

	public MongoDao(){
		
	}

	
	@Override
	public void insert(Map map) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Map> select(Map condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map delete(Map condition) {
		// TODO Auto-generated method stub
		return null;
	}

}
