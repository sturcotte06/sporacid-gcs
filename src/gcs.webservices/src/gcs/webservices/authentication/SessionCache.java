package gcs.webservices.authentication;

import gcs.webapp.utils.caching.Cache;
import gcs.webapp.utils.security.IHashProvider;
import gcs.webservices.models.Membre;
import gcs.webservices.services.beans.requests.AuthenticatedRequest;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class SessionCache extends Cache<PrivateSessionKey, AuthorizedSession>
{
	/**
	 * Regex to test ip addresses validity
	 */
	private static final String cIpAddressRegex = 
	        "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	
	/**
	 * 
	 */
	private IHashProvider hashProvider;
	
	/**
	 * Constructor
	 * @param validitySecondsSpan Number of second of validity for the session
	 */
	public SessionCache(int validitySecondsSpan) 
	{
		super(validitySecondsSpan);
	}
	
	/**
	 * Remove a session from the application
	 * @param key
	 */
	public void removeSession(String ipAddress, String key)
	{
		PrivateSessionKey sessionKey = new PrivateSessionKey(hashProvider, key, ipAddress);
		this.removeCacheValue(sessionKey, PrivateSessionKey.class);
	}
	
	/**
	 * Create a session within the application for a user
	 * @param username 
	 */
	public PublicSessionKey createSessionFor(String ipAddress, Membre membreInfo, LDAPAuthenticationToken token)
	{
		PublicSessionKey publicKey = null;
				
		if (ipAddress != null && ipAddress.matches(cIpAddressRegex)) {
			// Generate a new unique session key
			publicKey = PublicSessionKey.generate();
			
			AuthorizedSession session = new AuthorizedSession(membreInfo, token);
			PrivateSessionKey privateKey = new PrivateSessionKey(hashProvider, publicKey.getKey(), ipAddress);
			
			// Cache the newly create session
			this.cacheValue(privateKey, PrivateSessionKey.class, session);
		}
		
		return publicKey;
	}

	/**
	 * Get the user session from the application
	 * @param authenticatedRequest A request from an authenticated user
	 * @return The session object
	 */
	public AuthorizedSession getSession(AuthenticatedRequest authenticatedRequest)
	{
		return getSession(authenticatedRequest.getIpAddress(), authenticatedRequest.getSessionKey());
	}
	
	/**
	 * Get the user session from the application
	 * @param key The string token from the user
	 * @return The session object
	 */
	public AuthorizedSession getSession(String ipAddress, String key)
	{
		PrivateSessionKey privateKey = new PrivateSessionKey(hashProvider, key, ipAddress);
		return this.getCacheValue(privateKey, PrivateSessionKey.class);
	}

	/**
	 * Getter for the hash provider
	 * @return The hash provider
	 */
	public IHashProvider getHashProvider() 
	{
		return hashProvider;
	}

	/**
	 * Setter for the hash provider
	 * @param hashProvider The new hash provider
	 */
	public void setHashProvider(IHashProvider hashProvider) 
	{
		this.hashProvider = hashProvider;
	}
}
