package gcs.webservices.client.requests;

import gcs.webservices.client.beans.SessionToken;

import javax.validation.constraints.NotNull;

/**
 * @author Simon Turcotte-Langevin
 */
public class AuthenticatedRequest extends Request
{
    @NotNull
    private SessionToken sessionToken;

    public AuthenticatedRequest()
    {
        super();
    }

    public AuthenticatedRequest(SessionToken sessionToken)
    {
        this.sessionToken = sessionToken;
    }

    /**
     * @return the sessionToken
     */
    public SessionToken getSessionToken()
    {
        return sessionToken;
    }

    /**
     * @param sessionToken the sessionToken to set
     */
    public void setSessionToken(SessionToken sessionToken)
    {
        this.sessionToken = sessionToken;
    }

}
