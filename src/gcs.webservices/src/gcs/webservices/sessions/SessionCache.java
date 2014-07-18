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
public class SessionCache extends ConcurrentCache<PrivateSessionKey, AuthorizedSession> implements ISessionCache
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

    /* (non-Javadoc)
     * @see gcs.webservices.sessions.ISessionCache#sessionExists(gcs.webservices.client.beans.SessionToken)
     */
    @Override
    public boolean sessionExists(SessionToken sessionToken)
    {
        return sessionExists(sessionToken.getIpv4Address(), sessionToken.getSessionKey());
    }

    /* (non-Javadoc)
     * @see gcs.webservices.sessions.ISessionCache#sessionExists(java.lang.String, java.lang.String)
     */
    @Override
    public boolean sessionExists(String ipv4Address, String sessionKey)
    {
        final PrivateSessionKey privateKey = new PrivateSessionKey(hashProvider, sessionKey, ipv4Address);
        return this.has(privateKey);
    }

    /* (non-Javadoc)
     * @see gcs.webservices.sessions.ISessionCache#removeSession(gcs.webservices.client.beans.SessionToken)
     */
    @Override
    public void removeSession(SessionToken sessionToken)
    {
        removeSession(sessionToken.getIpv4Address(), sessionToken.getSessionKey());
    }

    /* (non-Javadoc)
     * @see gcs.webservices.sessions.ISessionCache#removeSession(java.lang.String, java.lang.String)
     */
    @Override
    public void removeSession(String ipv4Address, String sessionKey)
    {
        final PrivateSessionKey privateKey = new PrivateSessionKey(hashProvider, sessionKey, ipv4Address);
        final IWithCacheValueAction<AuthorizedSession> withSession = (session) -> {
            super.remove(privateKey);

            try {
                session.getAuthenticationToken().getLoginContext().logout();
            } catch (LoginException ex) {
                // Nothing we can do
            }
        };

        // Do the actual remove
        this.withCacheValue(privateKey, withSession);
    }

    /* (non-Javadoc)
     * @see gcs.webservices.sessions.ISessionCache#createSessionFor(java.lang.String, gcs.webservices.models.Membre, gcs.webservices.ldap.authentication.LdapAuthenticationToken)
     */
    @Override
    public PublicSessionKey createSessionFor(String ipv4Address, Membre membre, LdapAuthenticationToken token)
    {
        // Generate a new unique session key
        PublicSessionKey publicKey = PublicSessionKey.generate();

        AuthorizedSession session = new AuthorizedSession(membre, token);
        PrivateSessionKey privateKey = new PrivateSessionKey(hashProvider, publicKey.getKey(), ipv4Address);

        // Cache the newly create session
        super.put(privateKey, session);

        return publicKey;
    }

    /* (non-Javadoc)
     * @see gcs.webservices.sessions.ISessionCache#withSession(gcs.webservices.client.beans.SessionToken, gcs.webapp.utils.caching.IWithCacheValueAction)
     */
    @Override
    public void withSession(SessionToken sessionToken, IWithCacheValueAction<AuthorizedSession> action)
    {
        withSession(sessionToken.getIpv4Address(), sessionToken.getSessionKey(), action);
    }

    /* (non-Javadoc)
     * @see gcs.webservices.sessions.ISessionCache#withSession(java.lang.String, java.lang.String, gcs.webapp.utils.caching.IWithCacheValueAction)
     */
    @Override
    public void withSession(String ipv4Address, String sessionKey, IWithCacheValueAction<AuthorizedSession> action)
    {
        PrivateSessionKey privateKey = new PrivateSessionKey(hashProvider, sessionKey, ipv4Address);
        withCacheValue(privateKey, action);
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
