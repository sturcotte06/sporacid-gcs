package gcs.webservices.authentication;

import gcs.webservices.models.Membre;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class AuthorizedSession 
{
   private final Map<String, Object> sessionProperties = new HashMap<String, Object>();
   
   @Getter
	private Membre membre;
   
   @Getter
	private LDAPAuthenticationToken authenticationToken;
	
	public AuthorizedSession(Membre membre, LDAPAuthenticationToken token)
	{
		this.membre = membre;
		this.authenticationToken = token;
	}
	
	public void setSessionProperty(String key, String value)
	{
		if (sessionProperties.containsKey(key)) {
			throw new IllegalArgumentException(String.format("Property %s already exists.", key));
		}
		
		sessionProperties.put(key, value);
	}
	
	public Object getSessionProperty(String key)
	{
		if (!sessionProperties.containsKey(key)) {
			return null;
		}
		
		return sessionProperties.get(key);
	}
}
