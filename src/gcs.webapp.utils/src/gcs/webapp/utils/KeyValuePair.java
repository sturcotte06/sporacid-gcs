package gcs.webapp.utils;

import java.io.Serializable;

public class KeyValuePair<K, V> implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private K key;
	private V value;
	
	public KeyValuePair(K key, V value)
	{
		this.setKey(key);
		this.setValue(value);
	}
	
	public V getValue()
	{
		return value;
	}
	
	public void setValue(V value)
	{
		this.value = value;
	}
	
	public K getKey()
	{
		return key;
	}
	
	public void setKey(K key)
	{
		this.key = key;
	}
}
