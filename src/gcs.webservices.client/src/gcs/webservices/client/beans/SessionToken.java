package gcs.webservices.client.beans;

import gcs.webapp.utils.CommonPatterns;

import javax.validation.constraints.Pattern;
import javax.ws.rs.PathParam;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class SessionToken
{    
    @NotEmpty(message = "beans_sesstoken_sessionkey_notempty")
    @PathParam(value = "sessionKey")
    private String sessionKey;

    @NotEmpty(message = "beans_sesstoken_ipv4address_notempty")
    @Pattern(regexp = CommonPatterns.Ipv4Address, message = "beans_sesstoken_ipv4address_unrecognized_pattern")
    @PathParam(value = "ipv4Address")
    private String ipv4Address;

    public SessionToken()
    {
        super();
    }
    
    /**
     * Constructor
     * @param sessionKey
     * @param ipv4Address
     */
    public SessionToken(String sessionKey, String ipv4Address)
    {
        this.sessionKey = sessionKey;
        this.ipv4Address = ipv4Address;
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
     * @return the ipv4Address
     */
    public String getIpv4Address()
    {
        return ipv4Address;
    }

    /**
     * @param ipv4Address the ipv4Address to set
     */
    public void setIpv4Address(String ipv4Address)
    {
        this.ipv4Address = ipv4Address;
    }
}
