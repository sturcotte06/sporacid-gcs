package gcs.webapp.utils.caching.providers;

import gcs.webapp.utils.caching.CacheKey;
import gcs.webapp.utils.reflect.ReflectionUtils;

import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Simon
 */
public class QueryStringKeyProvider implements IKeyProvider
{
	@Override
	public <E> CacheKey toKey(E object, Class<E> classObj)
	{
		StringBuffer keyBuilder = new StringBuffer();
		
		try {
			Map<String, Object> objProperties =
					ReflectionUtils.getObjectProperties(object, classObj);
			Set<String> propNames = objProperties.keySet();
			
			// Append all properties as query strings
			for (String propName : propNames) {
				keyBuilder.append(propName + "=" + objProperties.get(propName).toString());
				keyBuilder.append("&");
			}
			
			// Append request type, because we could have different type of
			// requests with the same parameters
			keyBuilder.append("requestType=" + classObj.toString());
			
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new CacheKey(keyBuilder.toString());
	}
}
