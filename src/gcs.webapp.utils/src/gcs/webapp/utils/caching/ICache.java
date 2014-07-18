package gcs.webapp.utils.caching;

import gcs.webapp.utils.aspects.logging.Loggable;

@Loggable
public interface ICache<K, V>
{
    /**
     * Returns whether an object is cached for the given key.
     * 
     * @param keyObj The key object.
     * @param keyClass The class object of the key.
     * @return Whether an object is cached for the given key.
     */
    public boolean has(K keyObj);
    
    /**
     * Cache an object into the cache.
     * 
     * @param keyObj The key object.
     * @param keyClass The class object of the key.
     * @param value The object to cache.
     */
    public void put(K keyObj, V value);
    
    /**
     * Get a cached object for the given key.
     * 
     * @param keyObj The key object.
     * @param keyClass The class object of the key.
     * @return The cached object for the given key.
     */
    public V get(K keyObj);
    
    /**
     * Remove the cached object for the given key
     * 
     * @param keyObj The key object
     * @param keyClass The class object of the key
     */
    public void remove(K keyObj);
}
