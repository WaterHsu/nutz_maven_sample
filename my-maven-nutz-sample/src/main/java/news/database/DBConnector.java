package news.database;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年10月16日
 */
public interface DBConnector<T> {
	
	public abstract void connect(T t);
	
	public abstract T get();
	
	public abstract void close(T t);
}
