package gcs.webservices.client.requests.membres;

import javax.ws.rs.FormParam;

import org.hibernate.validator.constraints.NotEmpty;

import gcs.webservices.client.requests.Request;

/**
 * @author Simon Turcotte-Langevin
 */
public class AddRequest extends Request
{
    /**
     * The search string to search for the user in the active directory. If the
     * user is found, it will be added with its informations.
     */
    @NotEmpty(message = "membres_ldadsearchstring_notempty")
    @FormParam("ldapSearchString")
    private String ldapSearchString;

    public AddRequest()
    {
    }

    public AddRequest(String ldapSearchString)
    {
        this.ldapSearchString = ldapSearchString;
    }

    /**
     * @return the ldapSearchString
     */
    public String getLdapSearchString()
    {
        return ldapSearchString;
    }

    /**
     * @param ldapSearchString the ldapSearchString to set
     */
    public void setLdapSearchString(String ldapSearchString)
    {
        this.ldapSearchString = ldapSearchString;
    }
}
