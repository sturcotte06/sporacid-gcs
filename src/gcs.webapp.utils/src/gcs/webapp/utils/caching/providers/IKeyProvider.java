package gcs.webapp.utils.caching.providers;

import gcs.webapp.utils.aspects.logging.Loggable;
import gcs.webapp.utils.caching.CacheKey;

/**
 * Interface for cache keys providers. A provider is responsible of handling key
 * unicity
 * 
 * @author Simon Turcotte-Langevin
 */
@Loggable
public interface IKeyProvider
{
    /**
     * Transform an object into a cache key
     * 
     * @param value
     * @return
     */
    public <TValue> CacheKey toKey(TValue value);
}
