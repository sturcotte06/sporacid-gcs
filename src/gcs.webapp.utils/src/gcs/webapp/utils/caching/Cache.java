package gcs.webapp.utils.caching;

import gcs.webapp.utils.caching.providers.IKeyProvider;
import gcs.webapp.utils.reflect.ReflectionUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.gson.Gson;

/**
 * A cache provider for caching requests and responses for Http services
 * @author Simon Turcotte-Langevin
 */
public abstract class Cache <K, V>
{
	/**
	 * 
	 */
	private static final int cHashMapInitialCapacity = 100;
	
	/**
	 * 
	 */
	private static final float cHashMapLoadFactor = 0.5f;
	
	/**
	 * Hash table for the cache
	 */
	private Map<CacheKey, V> cache;

	/**
	 * Cache key provider for handling keys
	 */
	private IKeyProvider keyProvider;
	
	/**
	 * An invalidator thread to remove "dirty" cache entries 
	 * from the cache
	 */
	private CacheInvalidator invalidator;
	
	/**
	 * Json serialiser for deep cloning
	 */
	private Gson jsonSerialiser;
	
	/**
	 * Constructor
	 * @param validitySecondsSpan Number of second of validity for the cache entry
	 */
	public Cache(int validitySecondsSpan)
	{
		this.cache = new ConcurrentHashMap<CacheKey, V>(cHashMapInitialCapacity, cHashMapLoadFactor);
		this.jsonSerialiser = new Gson();
		this.invalidator = new CacheInvalidator(validitySecondsSpan, cache);
		this.invalidator.start();
	}
	
	/**
	 * Destructor
	 */
	@Override
	public void finalize() throws Throwable
	{
		this.invalidator.shutdown();
		super.finalize();
	}
	
	/**
	 * Returns whether an object is cached for the given key
	 * @param keyObj	The key object
	 * @param keyClass	The class object of the key
	 * @return Whether an object is cached for the given key
	 */
	public synchronized boolean isValueCached(K keyObj, Class<K> keyClass)
	{
		CacheKey key = keyProvider.toKey(keyObj, keyClass);
		return cache.containsKey(key);
	}
	
	/*public synchronized Date valueExpirationDate(K keyObj, Class<K> keyClass)
	{
		Date expirationDate = null;
		CacheKey key = keyProvider.toKey(keyObj, keyClass);
		if (cache.containsKey(keyObj)) {
		}
	}*/
	
	/**
	 * Cache an object into the cache
	 * @param keyObj	The key object
	 * @param keyClass	The class object of the key
	 * @param value		The object to cache
	 */
	public synchronized void cacheValue(K keyObj, Class<K> keyClass, V value)
	{
		CacheKey key = keyProvider.toKey(keyObj, keyClass);
		if (cache.containsKey(key)) {
			cache.remove(key);
		}
		
		// cache.put(key, cloneValue(value));
		cache.put(key, ReflectionUtils.deepCopy(value, jsonSerialiser));
	}
	
	/**
	 * Get a cached object for the given key
	 * @param keyObj 	The key object
	 * @param keyClass 	The class object of the key
	 * @return The cached object for the given key
	 */
	public synchronized V getCacheValue(K keyObj, Class<K> keyClass)
	{
		V value = null;
		CacheKey key = keyProvider.toKey(keyObj, keyClass);
		if (cache.containsKey(key)) {
			value = ReflectionUtils.deepCopy(cache.get(key), jsonSerialiser);
		}
		
		// return cloneValue(value);
		return value;
	}
	
	/**
	 * Remove the cached object for the given key
	 * @param keyObj 	The key object
	 * @param keyClass 	The class object of the key
	 */
	public synchronized void removeCacheValue(K keyObj, Class<K> keyClass)
	{
		CacheKey key = keyProvider.toKey(keyObj, keyClass);
		if (cache.containsKey(key)) {
			cache.remove(key);
		}
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	/*@SuppressWarnings("unchecked")*/
	/*private synchronized V cloneValue(V model) 
	{
		V clone = null;
		if (model != null) {
			String json = jsonSerialiser.toJson(model);
			clone = (V) jsonSerialiser.fromJson(json, model.getClass());
		}
	   return clone;
	}*/
	
	/**
	 * Getter for the key provider
	 * @return The key provider
	 */
	public IKeyProvider getKeyProvider()
	{
		return keyProvider;
	}

	/**
	 * Setter for the key provider
	 * @param keyProvider The new key provider
	 */
	public void setKeyProvider(IKeyProvider keyProvider)
	{
		this.keyProvider = keyProvider;
	}
}