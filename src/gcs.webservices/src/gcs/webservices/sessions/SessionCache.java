package gcs.webservices.sessions;

import javax.security.auth.login.LoginException;

import gcs.webapp.utils.caching.ConcurrentCache;
import gcs.webapp.utils.caching.IWithCacheValueAction;
import gcs.webapp.utils.security.IHashProvider;
import gcs.webservices.client.beans.SessionToken;
import gcs.webservices.ldap.authentication.LdapAuthenticationToken;
import gcs.webservices.models.Membre;

/**
 * Concurrent cache sub class that stores user sessions. The sessions can only
 * be accessed by one thread at a time.
 * 
 * @author Simon Turcotte-Langevin
 */
public class SessionCache extends ConcurrentCache<PrivateSessionKey, AuthorizedSession>
{
    /** The hash provider to hash ip addresses and session keys together. */
    private IHashProvider hashProvider;

    /**
     * Constructor.
     * 
     * @param validitySecondsSpan Number of second of validity for the session.
     */
    public SessionCache(int validitySecondsSpan)
    {
        super(validitySecondsSpan);
    }

    /**
     * Returns whether a session exists for the given session token.
     * 
     * @param sessionToken The user's session token.
     * @return Whether a session exists or not.
     */
    public boolean sessionExists(SessionToken sessionToken)
    {
        return sessionExists(sessionToken.getIpv4Address(), sessionToken.getSessionKey());
    }

    /**
     * Returns whether a session exists for the given ipv4 address and session
     * key.
     * 
     * @param ipv4Address The ipv4 address of the user.
     * @param sessionKey The session key of the user.
     * @return Whether a session exists or not.
     */
    public boolean sessionExists(String ipv4Address, String sessionKey)
    {
        final PrivateSessionKey privateKey = new PrivateSessionKey(hashProvider, sessionKey, ipv4Address);
        return this.isValueCached(privateKey, PrivateSessionKey.class);
    }

    /**
     * Removes the session associated with the given session token.
     * 
     * @param sessionToken The user's session token.
     */
    public void removeSession(SessionToken sessionToken)
    {
        removeSession(sessionToken.getIpv4Address(), sessionToken.getSessionKey());
    }

    /**
     * Removes the session associated with the given ipv4 address and session
     * key.
     * 
     * @param ipv4Address The ipv4 address of the user.
     * @param sessionKey The session key of the user.
     */
    public void removeSession(String ipv4Address, String sessionKey)
    {
        final PrivateSessionKey privateKey = new PrivateSessionKey(hashProvider, sessionKey, ipv4Address);
        final IWithCacheValueAction<AuthorizedSession> withSession = (session) -> {
            removeCacheValue(privateKey, PrivateSessionKey.class);

            try {
                session.getAuthenticationToken().getLoginContext().logout();
            } catch (LoginException ex) {
                // Nothing we can do
            }
        };

        // Do the actual remove
        this.withCacheValue(privateKey, PrivateSessionKey.class, withSession);
    }

    /**
     * Creates a new session in the session cache. A new session need the ip
     * address of the user, its Membre entity from the database and an ldap
     * authentication token that validates the identity of the user.
     * 
     * @param ipv4Address The ipv4 address of the user.
     * @param membre The membre entity from the database for the user.
     * @param token The ldap token generated for the user.
     * @return The public session key to access the session in the future.
     */
    public PublicSessionKey createSessionFor(String ipv4Address, Membre membre, LdapAuthenticationToken token)
    {

        // Generate a new unique session key
        PublicSessionKey publicKey = PublicSessionKey.generate();

        AuthorizedSession session = new AuthorizedSession(membre, token);
        PrivateSessionKey privateKey = new PrivateSessionKey(hashProvider, publicKey.getKey(), ipv4Address);

        // Cache the newly create session
        this.cacheValue(privateKey, PrivateSessionKey.class, session);

        return publicKey;
    }

    /**
     * Executes a given action with a session while having an exclusive lock on
     * it.
     * 
     * @param sessionToken The user's session token.
     * @param action The action to take with the session.
     */
    public void withSession(SessionToken sessionToken, IWithCacheValueAction<AuthorizedSession> action)
    {
        withSession(sessionToken.getIpv4Address(), sessionToken.getSessionKey(), action);
    }

    /**
     * Executes a given action with a session while having an exclusive lock on
     * it.
     * 
     * @param ipv4Address The ipv4 address of the user.
     * @param sessionKey The session key of the user.
     * @param action The action to take with the session.
     */
    public void withSession(String ipv4Address, String sessionKey, IWithCacheValueAction<AuthorizedSession> action)
    {
        PrivateSessionKey privateKey = new PrivateSessionKey(hashProvider, sessionKey, ipv4Address);
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
