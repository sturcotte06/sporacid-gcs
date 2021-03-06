package gcs.webservices.sessions;

import gcs.webservices.ldap.authentication.LdapAuthenticationToken;
import gcs.webservices.models.Membre;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Simon Turcotte-Langevin
 */
public class AuthorizedSession
{
    private final Map<String, Object> sessionProperties = new HashMap<String, Object>();
    private Membre membre;
    private LdapAuthenticationToken authenticationToken;

    public AuthorizedSession(Membre membre, LdapAuthenticationToken token)
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

    /**
     * @return the membre
     */
    public Membre getMembre()
    {
        return membre;
    }

    /**
     * @return the authenticationToken
     */
    public LdapAuthenticationToken getAuthenticationToken()
    {
        return authenticationToken;
    }
}
