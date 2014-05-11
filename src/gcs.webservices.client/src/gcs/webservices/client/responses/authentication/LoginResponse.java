package gcs.webservices.client.responses.authentication;

import gcs.webservices.client.responses.Response;

/**
 * @author Simon Turcotte-Langevin
 */
public class LoginResponse extends Response
{
    private String sessionKey;

    /**
     * @return the sessionKey
     */
    public String getSessionKey()
    {
        return sessionKey;
    }

    /**
     * @param sessionKey the sessionKey to set
     */
    public void setSessionKey(String sessionKey)
    {
        this.sessionKey = sessionKey;
    }
}
