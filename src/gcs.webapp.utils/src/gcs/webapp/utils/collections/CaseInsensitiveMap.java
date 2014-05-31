package gcs.webapp.utils.collections;

import java.util.HashMap;

public class CaseInsensitiveMap<TValue> extends HashMap<String, TValue>
{
    /** */
    private static final long serialVersionUID = 4846450116886592439L;

    @Override
    public TValue put(String key, TValue value)
    {
        return super.put(key.toLowerCase(), value);
    }
    
    @Override
    public TValue get(Object key)
    {
        if (!(key instanceof String)) {
            return null;
        }
        
        return super.get(((String)key).toLowerCase());
    }
}
