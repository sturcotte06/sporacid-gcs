package gcs.webservices.authentication;

import javax.security.auth.login.LoginException;

import gcs.webapp.utils.CommonPatterns;
import gcs.webapp.utils.caching.ConcurrentCache;
import gcs.webapp.utils.caching.IWithCacheValueAction;
import gcs.webapp.utils.security.IHashProvider;
import gcs.webservices.client.beans.SessionToken;
import gcs.webservices.models.Membre;

/**
 * @author Simon Turcotte-Langevin
 */
public class SessionCache extends ConcurrentCache<PrivateSessionKey, AuthorizedSession>
{
    /** The hash provider to hash ip addresses and session keys. */
    private IHashProvider hashProvider;

    /**
     * Constructor
     * 
     * @param validitySecondsSpan Number of second of validity for the session
     */
    public SessionCache(int validitySecondsSpan)
    {
        super(validitySecondsSpan);
    }

    /**
     * @param ipAddress
     * @param key
     * @return
     */
    public boolean sessionExists(String ipAddress, String key)
    {
        final PrivateSessionKey sessionKey = new PrivateSessionKey(hashProvider, key, ipAddress);
        return this.isValueCached(sessionKey, PrivateSessionKey.class);
    }

    /**
     * Remove a session from the application
     * 
     * @param key
     */
    public void removeSession(String ipAddress, String key)
    {
        final PrivateSessionKey sessionKey = new PrivateSessionKey(hashProvider, key, ipAddress);
        final IWithCacheValueAction<AuthorizedSession> action = value -> {
            removeCacheValue(sessionKey, PrivateSessionKey.class);

            try {
                value.getAuthenticationToken().getLoginContext().logout();
            } catch (LoginException ex) {
                // Nothing we can do
            }
        };

        this.withCacheValue(sessionKey, PrivateSessionKey.class, action);
    }

    /**
     * Create a session within the application for a user
     * 
     * @param username
     */
    public PublicSessionKey createSessionFor(String ipAddress, Membre membreInfo, LDAPAuthenticationToken token)
    {
        PublicSessionKey publicKey = null;

        if (ipAddress != null && ipAddress.matches(CommonPatterns.Ipv4Address)) {
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
     * @param sessionToken
     * @param action
     */
    public void withSession(SessionToken sessionToken, IWithCacheValueAction<AuthorizedSession> action)
    {
        withSession(sessionToken.getIpv4Address(), sessionToken.getSessionKey(), action);
    }

    /**
     * @param ipAddress
     * @param key
     * @param action
     */
    public void withSession(String ipAddress, String key, IWithCacheValueAction<AuthorizedSession> action)
    {
        PrivateSessionKey privateKey = new PrivateSessionKey(hashProvider, key, ipAddress);
        withCacheValue(privateKey, PrivateSessionKey.class, action);
    }

    /**
     * @return the hashProvider
     */
    public IHashProvider getHashProvider()
    {
        return hashProvider;
    }

    /**
     * @param hashProvider the hashProvider to set
     */
    public void setHashProvider(IHashProvider hashProvider)
    {
        this.hashProvider = hashProvider;
    }
}
