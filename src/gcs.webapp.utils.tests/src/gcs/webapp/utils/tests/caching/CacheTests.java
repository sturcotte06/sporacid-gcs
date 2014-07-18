package gcs.webapp.utils.tests.caching;

import static org.junit.Assert.*;

import gcs.webapp.utils.KeyValuePair;
import gcs.webapp.utils.caching.Cache;
import gcs.webapp.utils.caching.CacheKey;
import gcs.webapp.utils.caching.providers.Base64KeyProvider;
import gcs.webapp.utils.caching.providers.IKeyProvider;
import gcs.webapp.utils.caching.providers.QueryStringKeyProvider;

import org.junit.Test;

public class CacheTests {

	private final int cValidityTimeSpan = 60;
	
	private final IKeyProvider[] providers = {
		new Base64KeyProvider(),
		new QueryStringKeyProvider()
	};
	
	private Cache<KeyValuePair<String, String>, Object> cache = new Cache<KeyValuePair<String, String>, Object>(cValidityTimeSpan) { };
	
	@Test
	public void test() {
		final KeyValuePair<String, String> key1 = new KeyValuePair<String, String>("clé111", "key111");
		final KeyValuePair<String, String> key2 = new KeyValuePair<String, String>("clé222", "key222");
		final Object value1 = "valeur1";
		final Object value2 = "valeur2";
		
		for (IKeyProvider provider : providers) {
			cache.setKeyProvider(provider);
			CacheKey cacheKey1 = provider.toKey(key1);
			CacheKey cacheKey2 = provider.toKey(key2);
			
			assertNotNull("Cache key is null.", cacheKey1);
			assertNotEquals("Cache keys are not unique.", cacheKey1, cacheKey2);
			
			cache.put(key1, value1);
			cache.put(key2, value2);
			
			assertTrue("Cache add failed (1).", cache.has(key1));
			assertTrue("Cache add failed (2).", cache.has(key2));
			assertEquals("Discrepency between cache key and value (1).", value1, cache.get(key1));
			assertEquals("Discrepency between cache key and value (2).", value2, cache.get(key2));
			
			cache.remove(key1);
			cache.remove(key2);
			
			assertNull("Cache removal failed (1).", cache.get(key1));
			assertNull("Cache removal failed (2).", cache.get(key2));
		}
		
		cache = new Cache<KeyValuePair<String,String>, Object>(2) {};
		cache.setKeyProvider(providers[0]);
		cache.put(key1, value1);
		cache.put(key2, value2);
		
		try {
			// time of validity ± 5s from the invalidator ± a small buffer of 0.5s
			Thread.sleep(7500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertFalse("Cache invalidator failed (1).", cache.has(key1));
		assertFalse("Cache invalidator failed (2).", cache.has(key2));
	}
}
