package gcs.webapp.utils.caching;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class CacheKey
{
   @Getter @Setter
	private String key;
	
   @Getter @Setter
	private Date timeCached;
	
	/**
	 * Constructor
	 * @param key String value for the key
	 */
	public CacheKey(String key)
	{
		this.key = key;
		this.timeCached = new Date();
	}
	
	@Override
	public int hashCode()
	{
		return key.hashCode();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		boolean equals = false;
		CacheKey cacheKey = (CacheKey) obj;
		
		if (cacheKey != null)
			equals = key.equals(cacheKey.getKey());
		
		return equals;
	}
}
