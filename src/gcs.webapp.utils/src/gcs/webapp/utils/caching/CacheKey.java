package gcs.webapp.utils.caching;

import java.util.Date;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class CacheKey
{
	/**
	 * 
	 */
	private String key;
	
	/**
	 * 
	 */
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

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}
	
	public Date getTimeCached()
	{
		return timeCached;
	}
	
	public void setTimeCached(Date timeCached)
	{
		this.timeCached = timeCached;
	}
}
