package news.database;

import java.util.List;
import java.util.Map;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年10月16日
 */
@SuppressWarnings("rawtypes")
public interface DBDao {

	public abstract void insert(Map map);
	
	public abstract List<Map> select(Map condition); 
	
	public abstract Map delete(Map condition);
}
