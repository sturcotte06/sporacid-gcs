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
    /** Map of keys and their corresponding lock. */
	private final Map<CacheKey, Lock> cacheLocks; 
	
	/**
	 * Constructor.
	 * @param validitySecondsSpan
	 */
	public ConcurrentCache(int validitySecondsSpan) 
	{
		super(validitySecondsSpan);		
		this.cacheLocks = new ConcurrentHashMap<>();
	}
	
	@Override
	public void put(K keyObj, V value)
	{
		if (keyObj == null) {
			throw new ArgumentNullException("keyObj");
		}
				
		if (value == null) {
			throw new ArgumentNullException("value");
		}
		
		CacheKey key = getKeyProvider().toKey(keyObj);
		if (cache.containsKey(key)) {
			throw new IllegalArgumentException("Cache key already exists.");
		}
		
		cache.put(key, value);
		cacheLocks.put(key, new ReentrantLock());
	}
	
	@Override
	public V get(K keyObj)
	{
		throw new NotImplementedException("Use acquireCacheValue() and releaseCacheValue() instead.");
	}
		
	public void withCacheValue(K keyObj, IWithCacheValueAction<V> action)
	{
      if (keyObj == null) {
         throw new ArgumentNullException("keyObj");
      }
            
      if (action == null) {
         throw new ArgumentNullException("action");
      }
      
      V value = acquire(keyObj);
      
      try {
          action.withCacheValueDo(value);
      } finally {
          release(keyObj);
      }
	}
	
	private V acquire(K keyObj)
	{
		if (keyObj == null) {
			throw new ArgumentNullException("keyObj");
		}
				
		CacheKey key = getKeyProvider().toKey(keyObj);
		Lock lock = null;
		
		try {
	        if (cacheLocks.containsKey(key)) {
	            lock = cacheLocks.get(key);
	            lock.lock();
	        }
	        
	        return super.get(keyObj);
		} catch (Throwable t) {
		    if (lock != null) {
		        lock.unlock();
		    }
		    
		    throw t;
		}
	}
	
	private void release(K keyObj)
	{
		if (keyObj == null) {
			throw new ArgumentNullException("keyObj");
		}
				
		CacheKey key = getKeyProvider().toKey(keyObj);
		if (cacheLocks.containsKey(key)) {
			Lock lock = cacheLocks.get(key);
			lock.unlock();
		}
	}
	
	@Override
	public void remove(K keyObj)
	{
		if (keyObj == null) {
			throw new ArgumentNullException("keyObj");
		}
				
		// Compute the key with the key provider
		CacheKey key = getKeyProvider().toKey(keyObj);
		Lock lock = null;

        try {
    		if (cacheLocks.containsKey(key)) {
			    lock = cacheLocks.get(key);
			    lock.lock();
			    
			    cacheLocks.remove(key);
			    remove(keyObj);
			}
    		
		} finally {
            if (lock != null) {
                lock.unlock();
            }
        }
	}
}
