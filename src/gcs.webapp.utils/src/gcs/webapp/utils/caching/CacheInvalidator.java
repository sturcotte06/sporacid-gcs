package gcs.webapp.utils.caching;

import java.util.Iterator;
import java.util.Map;
import java.util.Date;

/**
 * Thread-safe cache invalidator.
 * Thread that checks for cached responses invalidity and
 * remove them from the cache.
 * @author Simon Turcotte-Langevin
 */
public class CacheInvalidator extends Thread
{
	/**
	 * Number of milliseconds to wait between invalidation checks
	 */
	private static final int cThreadSleep = 5000;
	
	/**
	 * Flag whether the invalidator should be running or not
	 */
	private volatile boolean isRunning = true;
	
	/**
	 * Number of seconds that a cached response is valid
	 */
	private final int validitySecondsSpan;
	
	/**
	 * The cache to check for invalidation
	 */
	private Map<CacheKey, ?> cache;
	
	/**
	 * Constructor
	 */
	public CacheInvalidator(int validitySecondsSpan, Map<CacheKey, ?> cache)
	{
		this.validitySecondsSpan = validitySecondsSpan;
		this.cache = cache;
	}

	/**
	 * Shutdown the cache invalidator
	 */
	public synchronized void shutdown()
	{
		// Set the flag that the invalidator should be running to false
		// The thread will close shortly
		this.isRunning = false;
	}
	
	/**
	 * Invalidator main task
	 * It checks once in a while for invalidated cached object and remove them
	 * from the cache
	 */
	@Override
	public void run()
	{
		// Stop the loop only if a request for shutdown was asked
		while (isRunning) {
			try {
				// Don't build a new date object everytime
				// we hit a key; Use a reference date for every checks
				// of this turn
				long refDate = new Date().getTime();
				Iterator<CacheKey> iterator = cache.keySet().iterator();
				while (iterator.hasNext()) {
					CacheKey key = iterator.next();
					long secDiff = (refDate - key.getTimeCached().getTime()) / 1000;
					if (secDiff > validitySecondsSpan) {
						// The cache value must be invalidated
						synchronized (cache) {
							iterator.remove();
						}
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
