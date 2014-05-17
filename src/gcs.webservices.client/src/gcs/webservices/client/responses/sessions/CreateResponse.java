package gcs.webservices.client.responses.sessions;

import gcs.webservices.client.responses.Response;

/**
 * @author Simon Turcotte-Langevin
 */
public class CreateResponse extends Response
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
