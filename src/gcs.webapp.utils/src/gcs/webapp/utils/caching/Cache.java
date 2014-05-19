package gcs.webapp.utils.caching;

import gcs.webapp.utils.aspects.logging.Loggable;
import gcs.webapp.utils.caching.providers.IKeyProvider;
import gcs.webapp.utils.exceptions.ArgumentNullException;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A cache provider for caching requests and responses for Http services
 * 
 * @author Simon Turcotte-Langevin
 */
@Loggable
public abstract class Cache<K, V>
{
    /** */
    private static final int cHashMapInitialCapacity = 100;

    /** */
    private static final float cHashMapLoadFactor = 0.5f;

    /** Hash table for the cache. */
    protected final Map<CacheKey, V> cache;

    /** The lock for accessing the cache. */
    private final Lock cacheLock = new ReentrantLock();

    /** An invalidator thread to remove stale cache entries from the cache. */
    private final CacheInvalidator invalidator;

    /** Cache key provider for computing keys. */
    private IKeyProvider keyProvider;

    /**
     * Constructor.
     * 
     * @param validitySecondsSpan Number of second of validity for the cache
     *            entry.
     */
    public Cache(int validitySecondsSpan)
    {
        this.cache = new HashMap<>(cHashMapInitialCapacity, cHashMapLoadFactor);

        this.invalidator = new CacheInvalidator(validitySecondsSpan);
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
     * Returns whether an object is cached for the given key.
     * 
     * @param keyObj The key object.
     * @param keyClass The class object of the key.
     * @return Whether an object is cached for the given key.
     */
    public boolean isValueCached(K keyObj, Class<K> keyClass)
    {
        if (keyObj == null) {
            throw new ArgumentNullException("keyObj");
        }

        if (keyClass == null) {
            throw new ArgumentNullException("keyClass");
        }

        CacheKey key = keyProvider.toKey(keyObj, keyClass);
        boolean containsKey = false;
        try {
            cacheLock.lock();
            containsKey = cache.containsKey(key);
        } finally {
            cacheLock.unlock();
        }

        return containsKey;
    }

    /**
     * Cache an object into the cache.
     * 
     * @param keyObj The key object.
     * @param keyClass The class object of the key.
     * @param value The object to cache.
     */
    public void cacheValue(K keyObj, Class<K> keyClass, V value)
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

        try {
            cacheLock.lock();

            if (cache.containsKey(key)) {
                throw new IllegalArgumentException("Cache key already exists.");
            }

            cache.put(key, value);
        } finally {
            cacheLock.unlock();
        }
    }

    /**
     * Get a cached object for the given key.
     * 
     * @param keyObj The key object.
     * @param keyClass The class object of the key.
     * @return The cached object for the given key.
     */
    public V getCacheValue(K keyObj, Class<K> keyClass)
    {
        if (keyObj == null) {
            throw new ArgumentNullException("keyObj");
        }

        if (keyClass == null) {
            throw new ArgumentNullException("keyClass");
        }

        V value = null;
        CacheKey key = keyProvider.toKey(keyObj, keyClass);

        try {
            cacheLock.lock();

            if (cache.containsKey(key)) {
                value = cache.get(key);
            }
        } finally {
            cacheLock.unlock();
        }

        return value;
    }

    /**
     * Remove the cached object for the given key
     * 
     * @param keyObj The key object
     * @param keyClass The class object of the key
     */
    public void removeCacheValue(K keyObj, Class<K> keyClass)
    {
        if (keyObj == null) {
            throw new ArgumentNullException("keyObj");
        }

        if (keyClass == null) {
            throw new ArgumentNullException("keyClass");
        }

        CacheKey key = keyProvider.toKey(keyObj, keyClass);

        try {
            cacheLock.lock();

            if (cache.containsKey(key)) {
                cache.remove(key);
            }
        } finally {
            cacheLock.unlock();
        }
    }

    /**
     * @return the keyProvider
     */
    public IKeyProvider getKeyProvider()
    {
        return keyProvider;
    }

    /**
     * @param keyProvider the keyProvider to set
     */
    public void setKeyProvider(IKeyProvider keyProvider)
    {
        this.keyProvider = keyProvider;
    }

    /**
     * Thread-safe cache invalidator. Thread that checks for cached responses
     * invalidity and remove them from the cache.
     * 
     * @author Simon Turcotte-Langevin
     */
    @Loggable
    private class CacheInvalidator extends Thread
    {
        /** Number of milliseconds to wait between invalidation checks. */
        private static final int cThreadSleep = 10000;

        /** Flag whether the invalidator should be running or not. */
        private volatile boolean isRunning = true;

        /** Number of seconds that a cached response is valid. */
        private final int validitySecondsSpan;

        /**
         * Constructor
         */
        public CacheInvalidator(int validitySecondsSpan)
        {
            this.validitySecondsSpan = validitySecondsSpan;
        }

        /**
         * Shutdown the cache invalidator
         */
        public void shutdown()
        {
            // Set the flag that the invalidator should be running to false
            // The thread will close shortly
            this.isRunning = false;

            try {
                this.join();
            } catch (InterruptedException e) {
                // Propagate the interruption
                Thread.currentThread().interrupt();
            }
        }

        /**
         * Invalidator main task. It checks once in a while for invalidated
         * cached object and remove them from the cache.
         */
        @Override
        public void run()
        {
            // Stop the loop only if shutdown was requested
            while (isRunning) {
                try {
                    // Don't build a new date object everytime
                    // we hit a key; Use a reference date for every checks
                    // of this iteration.
                    long refDate = new Date().getTime();
                    Iterator<CacheKey> iterator = cache.keySet().iterator();
                    while (iterator.hasNext()) {
                        CacheKey key = iterator.next();
                        long secDiff = (refDate - key.getTimeCached().getTime()) / 1000;
                        if (secDiff > validitySecondsSpan) {
                            // The cache value must be invalidated
                            try {
                                cacheLock.lock();
                                cache.remove(key);
                                // iterator.remove();
                            } finally {
                                cacheLock.unlock();
                            }
                            // synchronized (cache) {
                            // iterator.remove();
                            // }
                        }
                    }

                    // Sleep the thread for some time; cache invalidation
                    // isn't that much of a deal, so we don't care if it's
                    // invalidated some time after its real invalidation
                    Thread.sleep(cThreadSleep);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}