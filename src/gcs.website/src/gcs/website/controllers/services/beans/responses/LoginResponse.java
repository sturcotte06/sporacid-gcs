package gcs.website.controllers.services.beans.responses;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class LoginResponse extends Response 
{
	private String sessionKey;

	public String getSessionKey() 
	{
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) 
	{
		this.sessionKey = sessionKey;
	}
}
