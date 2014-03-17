package gcs.website.controllers.services.beans.requests;

import javax.ws.rs.QueryParam;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class AuthenticatedRequest extends Request 
{
	@QueryParam(value = "sessionKey")
	private String sessionKey;
	
	@QueryParam(value = "ipAddress")
	private String ipAddress;

	public String getSessionKey() 
	{
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) 
	{
		this.sessionKey = sessionKey;
	}

	public String getIpAddress() 
	{
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) 
	{
		this.ipAddress = ipAddress;
	}
}
