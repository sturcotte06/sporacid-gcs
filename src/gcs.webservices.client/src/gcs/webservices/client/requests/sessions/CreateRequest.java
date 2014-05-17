package gcs.webservices.client.requests.sessions;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import gcs.webapp.utils.CommonPatterns;
import gcs.webservices.client.requests.Request;

/**
 * @author Simon Turcotte-Langevin
 */
public class CreateRequest extends Request
{
    @NotEmpty(message = "authentication_username_notempty")
    @Pattern(regexp = CommonPatterns.GcsUsername, message = "authentication_username_unrecognized_pattern")
    private String username;

    @NotEmpty(message = "authentication_ipv4address_notempty")
    @Pattern(regexp = CommonPatterns.Ipv4Address, message = "authentication_ipv4address_unrecognized_pattern")
    private String ipv4Address;

    @NotEmpty(message = "authentication_password_notempty")
    private String password;

    public CreateRequest()
    {
        super();
    }

    public CreateRequest(String username, String ipv4Address, String password)
    {
        super();
        this.username = username;
        this.ipv4Address = ipv4Address;
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
