package gcs.webservices.sessions;

import gcs.webapp.utils.caching.IWithCacheValueAction;
import gcs.webservices.client.beans.SessionToken;
import gcs.webservices.ldap.authentication.LdapAuthenticationToken;
import gcs.webservices.models.Membre;

public interface ISessionCache
{

    /**
     * Returns whether a session exists for the given session token.
     * 
     * @param sessionToken The user's session token.
     * @return Whether a session exists or not.
     */
    public boolean sessionExists(SessionToken sessionToken);

    /**
     * Returns whether a session exists for the given ipv4 address and session
     * key.
     * 
     * @param ipv4Address The ipv4 address of the user.
     * @param sessionKey The session key of the user.
     * @return Whether a session exists or not.
     */
    public boolean sessionExists(String ipv4Address, String sessionKey);

    /**
     * Removes the session associated with the given session token.
     * 
     * @param sessionToken The user's session token.
     */
    public void removeSession(SessionToken sessionToken);

    /**
     * Removes the session associated with the given ipv4 address and session
     * key.
     * 
     * @param ipv4Address The ipv4 address of the user.
     * @param sessionKey The session key of the user.
     */
    public void removeSession(String ipv4Address, String sessionKey);

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
    public PublicSessionKey createSessionFor(String ipv4Address, Membre membre, LdapAuthenticationToken token);

    /**
     * Executes a given action with a session while having an exclusive lock on
     * it.
     * 
     * @param sessionToken The user's session token.
     * @param action The action to take with the session.
     */
    public void withSession(SessionToken sessionToken, IWithCacheValueAction<AuthorizedSession> action);

    /**
     * Executes a given action with a session while having an exclusive lock on
     * it.
     * 
     * @param ipv4Address The ipv4 address of the user.
     * @param sessionKey The session key of the user.
     * @param action The action to take with the session.
     */
    public void withSession(String ipv4Address, String sessionKey, IWithCacheValueAction<AuthorizedSession> action);

}