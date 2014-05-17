package gcs.webservices.ldap.search;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class LdapUser
{
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    
    public LdapUser(String username, String email, String firstName, String lastName)
    {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
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
     * @return the email
     */
    public String getEmail()
    {
        return email;
    }
    
    /**
     * @param email the email to set
     */
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    /**
     * @return the firstName
     */
    public String getFirstName()
    {
        return firstName;
    }
    
    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    
    /**
     * @return the lastName
     */
    public String getLastName()
    {
        return lastName;
    }
    
    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
}
