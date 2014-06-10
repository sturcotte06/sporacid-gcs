package gcs.webapp.utils.caching.providers;

import gcs.webapp.utils.caching.CacheKey;
import gcs.webapp.utils.reflect.ReflectionUtils;

import java.util.Map;

/**
 * @author Simon
 */
public class QueryStringKeyProvider implements IKeyProvider
{
    @Override
    public <TValue> CacheKey toKey(TValue object, Class<TValue> classObj)
    {
        StringBuffer keyBuilder = new StringBuffer();

        // Append all properties as query strings
        Map<String, Object> objProperties = ReflectionUtils.flatten(object);
        for (Map.Entry<String, Object> objProperty : objProperties.entrySet()) {
            keyBuilder.append(objProperty.getKey() + "=" + objProperty.getValue());
            keyBuilder.append("&");
        }

        if (keyBuilder.length() > 0) {
            // Truncate last ampersand
            keyBuilder.setLength(keyBuilder.length() - 1);
        }

        // Append request type, because we could have different type of
        // requests with the same parameters
        keyBuilder.append("requestType=" + classObj.toString());

        return new CacheKey(keyBuilder.toString());
    }
}
