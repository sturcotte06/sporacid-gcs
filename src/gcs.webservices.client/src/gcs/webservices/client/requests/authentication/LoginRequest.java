package gcs.webservices.client.requests.authentication;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import gcs.webapp.utils.CommonPatterns;
import gcs.webservices.client.requests.Request;

/**
 * @author Simon Turcotte-Langevin
 */
public class LoginRequest extends Request
{
    @NotEmpty(message = "authentication_username_notempty")
    @Pattern(regexp = CommonPatterns.GcsUsername, message = "authentication_username_unrecognized_pattern")
    private String username;

    @NotEmpty(message = "authentication_ipv4address_notempty")
    @Pattern(regexp = CommonPatterns.Ipv4Address, message = "authentication_ipv4address_unrecognized_pattern")
    private String ipAddress;

    @NotEmpty(message = "authentication_password_notempty")
    private String password;

    public LoginRequest()
    {
        super();
    }

    public LoginRequest(String username, String ipAddress, String password)
    {
        super();
        this.username = username;
        this.ipAddress = ipAddress;
        this.password = password;
    }

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

    /**
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password)
    {
        this.password = password;
    }
}
