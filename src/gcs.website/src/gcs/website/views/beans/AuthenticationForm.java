package gcs.website.views.beans;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Simon Turcotte-Langevin
 */
public class AuthenticationForm
{
    @Length(min = 1, max = 10, message = "validation_authentication_username_bad_length")
    private String username;

    @NotEmpty(message = "validation_authentication_password_empty")
    private String password;

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
}
