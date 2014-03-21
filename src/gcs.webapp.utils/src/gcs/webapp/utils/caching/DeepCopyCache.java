package gcs.webapp.utils.caching;

import gcs.webapp.utils.reflect.ReflectionUtils;

import com.google.gson.Gson;

/**
 * Cache instance that returns deep copies of cached
 * values instead of a reference to the original value.
 * @author Simon Turcotte-Langevin
 */
public class DeepCopyCache<K, V> extends Cache<K, V> 
{
	/**
	 * Json serialiser for deep cloning
	 */
	private Gson jsonSerialiser;
	
	public DeepCopyCache(int validitySecondsSpan) 
	{
		super(validitySecondsSpan);
	}

	@Override
	public synchronized void cacheValue(K keyObj, Class<K> keyClass, V value)
	{
		super.cacheValue(keyObj, keyClass, ReflectionUtils.deepCopy(value, jsonSerialiser));
	}
	
	@Override
	public synchronized V getCacheValue(K keyObj, Class<K> keyClass)
	{
		return ReflectionUtils.deepCopy(super.getCacheValue(keyObj, keyClass));
	}
	
	public Gson getJsonSerialiser() 
	{
		return jsonSerialiser;
	}

	public void setJsonSerialiser(Gson jsonSerialiser) 
	{
		this.jsonSerialiser = jsonSerialiser;
	}
}
