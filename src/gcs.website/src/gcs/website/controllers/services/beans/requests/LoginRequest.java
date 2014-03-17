package gcs.website.controllers.services.beans.requests;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class LoginRequest extends Request 
{
	private String username;
	private String ipAddress;
	private String password;

	public String getUsername() 
	{
		return username;
	}
	
	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getIpAddress() 
	{
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) 
	{
		this.ipAddress = ipAddress;
	}
	
	public String getPassword() 
	{
		return password;
	}
	
	public void setPassword(String password) 
	{
		this.password = password;
	}
}
