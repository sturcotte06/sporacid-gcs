package gcs.webapp.utils;

import java.io.Serializable;

public class KeyValuePair<K, V> implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private K key;
	private V value;
	
	public KeyValuePair(K key, V value)
	{
		this.setKey(key);
		this.setValue(value);
	}

    /**
     * @return the value
     */
    public V getValue()
    {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(V value)
    {
        this.value = value;
    }

    /**
     * @return the key
     */
    public K getKey()
    {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(K key)
    {
        this.key = key;
    }
}
