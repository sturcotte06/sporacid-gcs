package gcs.webservices.client.requests.membres;

import org.hibernate.validator.constraints.NotEmpty;

import gcs.webservices.client.beans.Context;
import gcs.webservices.client.beans.SessionToken;
import gcs.webservices.client.requests.ContextualAuthenticatedRequest;

/**
 * @author Simon Turcotte-Langevin
 */
public class AddRequest extends ContextualAuthenticatedRequest
{
    /**
     * The search string to search for the user in the active directory. If the
     * user is found, it will be added with its informations.
     */
    @NotEmpty(message = "membres_ldadsearchstring_notempty")
    private String ldapSearchString;

    public AddRequest()
    {
        super();
    }
    
    public AddRequest(SessionToken sessionToken, Context context, String ldapSearchString)
    {
        super(sessionToken, context);
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
