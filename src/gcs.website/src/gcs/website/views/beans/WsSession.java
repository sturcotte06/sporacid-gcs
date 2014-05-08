package gcs.website.views.beans;

import java.util.Date;

/**
 * @author Simon Turcotte-Langevin
 */
public class WsSession
{
    private String username;
    private String sessionKey;
    private Date expirationDate;

    /**
     * @return the username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

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
     * @return the expirationDate
     */
    public Date getExpirationDate()
    {
        return expirationDate;
    }

    /**
     * @param expirationDate the expirationDate to set
     */
    public void setExpirationDate(Date expirationDate)
    {
        this.expirationDate = expirationDate;
    }
}
