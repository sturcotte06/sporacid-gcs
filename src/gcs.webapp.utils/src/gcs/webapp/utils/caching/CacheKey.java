package gcs.webapp.utils.caching;

import java.util.Date;

/**
 * @author Simon Turcotte-Langevin
 */
public class CacheKey
{
    private String key;
    private Date timeCached;

    /**
     * Constructor
     * 
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

    /**
     * @return the key
     */
    public String getKey()
    {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key)
    {
        this.key = key;
    }

    /**
     * @return the timeCached
     */
    public Date getTimeCached()
    {
        return timeCached;
    }

    /**
     * @param timeCached the timeCached to set
     */
    public void setTimeCached(Date timeCached)
    {
        this.timeCached = timeCached;
    }
}
