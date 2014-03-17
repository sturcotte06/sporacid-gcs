package gcs.webapp.utils.caching.providers;

import gcs.webapp.utils.caching.CacheKey;

/**
 * Interface for cache keys providers.
 * A provider is responsible of handling key unicity
 * @author Simon Turcotte-Langevin
 */
public interface IKeyProvider
{
	/**
	 * Transform an object into a cache key
	 * @param object 
	 * @param classObj
	 * @return
	 */
	public <E> CacheKey toKey(E object, Class<E> classObj);
}
