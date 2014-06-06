package gcs.webapp.utils.caching.providers;

import gcs.webapp.utils.caching.CacheKey;
import gcs.webapp.utils.reflect.ReflectionUtils;

import java.util.Map;
import java.util.Set;

/**
 * @author Simon
 */
public class QueryStringKeyProvider implements IKeyProvider
{
    @Override
    public <TValue> CacheKey toKey(TValue object, Class<TValue> classObj)
    {
        StringBuffer keyBuilder = new StringBuffer();

        Map<String, Object> objProperties = ReflectionUtils.getObjectProperties(object, classObj);
        Set<String> propNames = objProperties.keySet();

        // Append all properties as query strings
        for (String propName : propNames) {
            keyBuilder.append(propName + "=" + objProperties.get(propName).toString());
            keyBuilder.append("&");
        }

        // Append request type, because we could have different type of
        // requests with the same parameters
        keyBuilder.append("requestType=" + classObj.toString());

        return new CacheKey(keyBuilder.toString());
    }
}
