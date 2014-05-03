package gcs.webapp.utils.caching;

import gcs.webapp.utils.caching.providers.IKeyProvider;
import gcs.webapp.utils.exceptions.ArgumentNullException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;
import lombok.Setter;

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
	protected final Map<CacheKey, V> cache;
	
	/**
	 * An invalidator thread to remove "dirty" cache entries 
	 * from the cache
	 */
	private final CacheInvalidator invalidator;

	/**
	 * Cache key provider for handling keys
	 */
	@Getter @Setter
	private IKeyProvider keyProvider;
	
	/**
	 * Constructor
	 * @param validitySecondsSpan Number of second of validity for the cache entry
	 */
	public Cache(int validitySecondsSpan)
	{
		this.cache = new ConcurrentHashMap<>(cHashMapInitialCapacity, cHashMapLoadFactor);
		
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
		if (keyObj == null) {
			throw new ArgumentNullException("keyObj");
		}
		
		if (keyClass == null) {
			throw new ArgumentNullException("keyClass");
		}
		
		CacheKey key = keyProvider.toKey(keyObj, keyClass);
		return cache.containsKey(key);
	}
		
	/**
	 * Cache an object into the cache
	 * @param keyObj	The key object
	 * @param keyClass	The class object of the key
	 * @param value		The object to cache
	 */
	public synchronized void cacheValue(K keyObj, Class<K> keyClass, V value)
	{
		if (keyObj == null) {
			throw new ArgumentNullException("keyObj");
		}
		
		if (keyClass == null) {
			throw new ArgumentNullException("keyClass");
		}
		
		if (value == null) {
			throw new ArgumentNullException("value");
		}
		
		CacheKey key = keyProvider.toKey(keyObj, keyClass);
		if (cache.containsKey(key)) {
			throw new IllegalArgumentException("Cache key already exists.");
			// cache.remove(key);
		}
		
		cache.put(key, value);
	}
	
	/**
	 * Get a cached object for the given key
	 * @param keyObj 	The key object
	 * @param keyClass 	The class object of the key
	 * @return The cached object for the given key
	 */
	public synchronized V getCacheValue(K keyObj, Class<K> keyClass)
	{
		if (keyObj == null) {
			throw new ArgumentNullException("keyObj");
		}
		
		if (keyClass == null) {
			throw new ArgumentNullException("keyClass");
		}
		
		V value = null;
		CacheKey key = keyProvider.toKey(keyObj, keyClass);
		if (cache.containsKey(key)) {
			value = cache.get(key);
		}
		
		return value;
	}
	
	/**
	 * Remove the cached object for the given key
	 * @param keyObj 	The key object
	 * @param keyClass 	The class object of the key
	 */
	public synchronized void removeCacheValue(K keyObj, Class<K> keyClass)
	{
		if (keyObj == null) {
			throw new ArgumentNullException("keyObj");
		}
		
		if (keyClass == null) {
			throw new ArgumentNullException("keyClass");
		}
		
		CacheKey key = keyProvider.toKey(keyObj, keyClass);
		if (cache.containsKey(key)) {
			cache.remove(key);
		}
	}
}