package gcs.webservices.services;

import gcs.webservices.client.beans.SessionToken;
import gcs.webservices.client.requests.sessions.CreateRequest;

import javax.ws.rs.core.Response;

/**
 * @author Simon Turcotte-Langevin
 */
public interface ISessionService
{
    /**
     * Creates a session in the system.
     * 
     * @param request The session creation request.
     * @return A generic response with a success flag.
     */
    public Response create(CreateRequest request);

    /**
     * Invalidates a session in the system.
     * 
     * @param sessionToken The session token from the user.
     * @return A generic response with a success flag.
     */
    public Response invalidate(SessionToken sessionToken);
}
