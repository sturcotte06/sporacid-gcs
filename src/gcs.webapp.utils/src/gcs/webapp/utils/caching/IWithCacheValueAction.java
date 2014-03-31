package gcs.webapp.utils.caching;

/**
 * Interface for an action to do while
 * having an exclusive lock on a cache value.
 * @author Simon Turcotte-Langevin
 *
 * @param <V> The cache value type
 */
public interface IWithCacheValueAction<V>
{
   /**
    * An action to do while having an exclusive 
    * lock on a cache value.
    * @param value
    */
   public void withCacheValueDo(V value);
}
