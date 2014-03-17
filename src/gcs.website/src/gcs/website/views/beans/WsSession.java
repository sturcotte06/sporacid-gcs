package gcs.website.views.beans;

import java.util.Date;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class WsSession 
{
	private String username;
	private String sessionKey;
	private Date expirationDate;

	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}
	
	public String getSessionKey() 
	{
		return sessionKey;
	}
	
	public void setSessionKey(String sessionKey) 
	{
		this.sessionKey = sessionKey;
	}
	
	public Date getExpirationDate() 
	{
		return expirationDate;
	}
	
	public void setExpirationDate(Date expirationDate)
	{
		this.expirationDate = expirationDate;
	}
}
