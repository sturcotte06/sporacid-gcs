package gcs.webservices.authentication;

import java.io.Serializable;

import gcs.webapp.utils.security.IHashProvider;

public class PrivateSessionKey implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7414010532159015180L;
	
	private String hashedKey;

	public PrivateSessionKey(IHashProvider hashProvider, String key, String ipAddress) 
	{
		hashedKey = hashProvider.hash(key, ipAddress).getHashedString();
	}
	
	public String getHashedKey() 
	{
		return hashedKey;
	}

	public void setHashedKey(String hashedKey) 
	{
		this.hashedKey = hashedKey;
	}
}
