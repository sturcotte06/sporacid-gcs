package gcs.webservices.authentication;

import javax.security.auth.login.LoginException;

import gcs.webapp.utils.caching.ConcurrentCache;
import gcs.webapp.utils.caching.IWithCacheValueAction;
import gcs.webapp.utils.security.IHashProvider;
import gcs.webservices.models.Membre;
import gcs.webservices.services.beans.requests.AuthenticatedRequest;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class SessionCache extends ConcurrentCache<PrivateSessionKey, AuthorizedSession>
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
		final PrivateSessionKey sessionKey = new PrivateSessionKey(hashProvider, key, ipAddress);
		final IWithCacheValueAction<AuthorizedSession> action = new IWithCacheValueAction<AuthorizedSession>() {
         @Override
         public void withCacheValueDo(AuthorizedSession value)
         {
            removeCacheValue(sessionKey, PrivateSessionKey.class);
            
            try {
               value.getAuthenticationToken().getLoginContext().logout();
            } catch (LoginException ex) {
               // logger.error(String.format("Couldn't logout user %s : "), ex);
            }
         }
      };
      
      this.withCacheValue(sessionKey, PrivateSessionKey.class, action);
		/*AuthorizedSession session = this.acquireCacheValue(sessionKey, PrivateSessionKey.class);		
		this.removeCacheValue(sessionKey, PrivateSessionKey.class);
		
		try {
			session.getAuthenticationToken().getLoginContext().logout();
		} catch (LoginException ex) {
			// logger.error(String.format("Couldn't logout user %s : "), ex);
		}
		
		this.releaseCacheValue(sessionKey, PrivateSessionKey.class);*/
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

	public void withSession(AuthenticatedRequest authenticatedRequest, IWithCacheValueAction<AuthorizedSession> action)
	{
	   withSession(authenticatedRequest.getIpAddress(), authenticatedRequest.getSessionKey(), action);
	}
	
	public void withSession(String ipAddress, String key, IWithCacheValueAction<AuthorizedSession> action)
   {
	   PrivateSessionKey privateKey = new PrivateSessionKey(hashProvider, key, ipAddress);
      withCacheValue(privateKey, PrivateSessionKey.class, action);
   }
	
	/**
	 * Acquire the user session from the application with an exclusive lock.
	 * <b>
	 * Do not forget to call releaseSession() when you're done!
	 * </b>
	 * @param authenticatedRequest A request from an authenticated user
	 * @return The session object
	 */
	/*public AuthorizedSession acquireSession(AuthenticatedRequest authenticatedRequest) throws InternalException
	{
		return acquireSession(authenticatedRequest.getIpAddress(), authenticatedRequest.getSessionKey());
	}*/
	
	/**
	 * Acquire the user session from the application with an exclusive lock.
	 * <b>
	 * Do not forget to call releaseSession() when you're done!
	 * </b>
	 * @param key The string token from the user
	 * @return The session object
	 */
	/*public AuthorizedSession acquireSession(String ipAddress, String key) throws InternalException
	{
		PrivateSessionKey privateKey = new PrivateSessionKey(hashProvider, key, ipAddress);
		return this.acquireCacheValue(privateKey, PrivateSessionKey.class);
	}*/

	/**
	 * Release the user session's exclusive lock.
	 * <b>
	 * Everytime you forget to call me, I kill a kitten.
	 * </b>
	 * @param authenticatedRequest A request from an authenticated user
	 * @return The session object
	 */
	/*public void releaseSession(AuthenticatedRequest authenticatedRequest) throws InternalException
	{
		releaseSession(authenticatedRequest.getIpAddress(), authenticatedRequest.getSessionKey());
	}*/
	
	/**
	 * Release the user session's exclusive lock.
	 * <b>
	 * Everytime you forget to call me, I kill a kitten.
	 * </b>
	 * @param key The string token from the user
	 * @return The session object
	 */
	/*public void releaseSession(String ipAddress, String key) throws InternalException
	{
		PrivateSessionKey privateKey = new PrivateSessionKey(hashProvider, key, ipAddress);
		this.releaseCacheValue(privateKey, PrivateSessionKey.class);
	}*/
	
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
