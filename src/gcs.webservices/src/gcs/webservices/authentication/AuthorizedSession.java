package gcs.webservices.authentication;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Simon Turcotte-Langevin
 *
 */
public class AuthorizedSession 
{
	private String username;
	private String roleName;
	private Map<String, Object> cachedObjects = new HashMap<String, Object>();
	
	public String getUsername() 
	{
		return username;
	}
	
	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getRoleName() 
	{
		return roleName;
	}

	public void setRoleName(String roleName) 
	{
		this.roleName = roleName;
	}
	
	public Map<String, Object> getCachedObjects() 
	{
		return cachedObjects;
	}
}
