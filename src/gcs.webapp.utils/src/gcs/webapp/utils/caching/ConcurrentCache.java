package gcs.webapp.utils.caching;

import gcs.webapp.utils.exceptions.ArgumentNullException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang.NotImplementedException;

/**
 * A cache that gives exclusive locking to cached values
 * when accessed. All accessed values must be returned to 
 * the cache to avoid dead locks.
 * @author Simon Turcotte-Langevin
 *
 */
public class ConcurrentCache<K, V> extends Cache<K, V>
{
	private final Map<CacheKey, Lock> cacheLocks; 
	
	public ConcurrentCache(int validitySecondsSpan) 
	{
		super(validitySecondsSpan);
		
		this.cacheLocks = new ConcurrentHashMap<>();
	}
	
	@Override
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
		}
		
		cache.put(key, value);
		cacheLocks.put(key, new ReentrantLock());
	}
	
	@Override
	public synchronized V getCacheValue(K keyObj, Class<K> keyClass)
	{
		throw new NotImplementedException("Use acquireCacheValue() and releaseCacheValue() instead.");
	}
	
	public synchronized void withCacheValue(K keyObj, Class<K> keyClass, IWithCacheValueAction<V> action)
	{
      if (keyObj == null) {
         throw new ArgumentNullException("keyObj");
      }
      
      if (keyClass == null) {
         throw new ArgumentNullException("keyClass");
      }
      
      if (action == null) {
         throw new ArgumentNullException("action");
      }
      
      V value = acquireCacheValue(keyObj, keyClass);
      action.withCacheValueDo(value);
      releaseCacheValue(keyObj, keyClass);
	}
	
	private synchronized V acquireCacheValue(K keyObj, Class<K> keyClass)
	{
		if (keyObj == null) {
			throw new ArgumentNullException("keyObj");
		}
		
		if (keyClass == null) {
			throw new ArgumentNullException("keyClass");
		}
		
		CacheKey key = keyProvider.toKey(keyObj, keyClass);
		if (cacheLocks.containsKey(key)) {
			Lock lock = cacheLocks.get(key);
			lock.lock();
		}
		
		return super.getCacheValue(keyObj, keyClass);
	}
	
	private synchronized void releaseCacheValue(K keyObj, Class<K> keyClass)
	{
		if (keyObj == null) {
			throw new ArgumentNullException("keyObj");
		}
		
		if (keyClass == null) {
			throw new ArgumentNullException("keyClass");
		}
		
		CacheKey key = keyProvider.toKey(keyObj, keyClass);
		if (cacheLocks.containsKey(key)) {
			Lock lock = cacheLocks.get(key);
			lock.unlock();
		}
	}
	
	@Override
	public synchronized void removeCacheValue(K keyObj, Class<K> keyClass)
	{
		if (keyObj == null) {
			throw new ArgumentNullException("keyObj");
		}
		
		if (keyClass == null) {
			throw new ArgumentNullException("keyClass");
		}
		
		CacheKey key = keyProvider.toKey(keyObj, keyClass);
		if (cacheLocks.containsKey(key)) {
			Lock lock = cacheLocks.get(key);
			lock.lock();
			cacheLocks.remove(key);
			lock.unlock();
			lock = null;
		}
		
		super.removeCacheValue(keyObj, keyClass);
	}
}
