package gcs.webservices.client.requests.authentication;

import gcs.webservices.client.beans.SessionToken;
import gcs.webservices.client.requests.AuthenticatedRequest;

/**
 * @author Simon Turcotte-Langevin
 */
public class LogoutRequest extends AuthenticatedRequest
{
    public LogoutRequest()
    {
        super();
    }
    
    public LogoutRequest(SessionToken sessionToken)
    {
        super(sessionToken);
    }
}
