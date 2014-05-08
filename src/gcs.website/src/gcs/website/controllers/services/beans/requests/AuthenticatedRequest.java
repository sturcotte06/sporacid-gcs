package gcs.website.controllers.services.beans.requests;

import javax.ws.rs.QueryParam;

/**
 * @author Simon Turcotte-Langevin
 */
public class AuthenticatedRequest extends Request
{
    @QueryParam(value = "sessionKey")
    private String sessionKey;

    @QueryParam(value = "ipAddress")
    private String ipAddress;

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

    /**
     * @return the ipAddress
     */
    public String getIpAddress()
    {
        return ipAddress;
    }

    /**
     * @param ipAddress the ipAddress to set
     */
    public void setIpAddress(String ipAddress)
    {
        this.ipAddress = ipAddress;
    }
}
