package gcs.webapp.utils.caching;

import gcs.webapp.utils.reflect.ReflectionUtils;

import com.google.gson.Gson;

/**
 * Cache instance that returns deep copies of cached
 * values instead of a reference to the original value.
 * @author Simon Turcotte-Langevin
 */
public class DeepCopyCache<K, V> extends Cache<K, V> 
{
	/**
	 * Json serialiser for deep cloning
	 */
	private Gson jsonSerialiser;
	
	public DeepCopyCache(int validitySecondsSpan) 
	{
		super(validitySecondsSpan);
	}

	@Override
	public void put(K keyObj, V value)
	{
	    super.put(keyObj, ReflectionUtils.deepCopy(value, jsonSerialiser));
	}
	
	@Override
	public V get(K keyObj)
	{
	    return ReflectionUtils.deepCopy(super.get(keyObj));
	}

    /**
     * @return the jsonSerialiser
     */
    public Gson getJsonSerialiser()
    {
        return jsonSerialiser;
    }

    /**
     * @param jsonSerialiser the jsonSerialiser to set
     */
    public void setJsonSerialiser(Gson jsonSerialiser)
    {
        this.jsonSerialiser = jsonSerialiser;
    }
}
