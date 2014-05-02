package gcs.webapp.utils;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class KeyValuePair<K, V> implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter @Setter
	private K key;
	
	@Getter @Setter
	private V value;
	
	public KeyValuePair(K key, V value)
	{
		this.setKey(key);
		this.setValue(value);
	}
}
