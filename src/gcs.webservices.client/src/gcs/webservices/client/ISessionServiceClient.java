package gcs.webservices.client;

import gcs.webservices.client.responses.Response;
import gcs.webservices.client.responses.sessions.CreateResponse;

/**
 * @author Simon Turcotte-Langevin
 */
public interface ISessionServiceClient
{
    /**
     * Validates the credentials of a user and create a web services' session.
     * 
     * @param ipv4Address Ipv4 address of the requester.
     * @param username The user's unique identifier.
     * @param password The user's password.
     * @return The login response from the web services.
     */
    public CreateResponse create(String ipv4Address, String username, String password);

    /**
     * Invalidates a web services' session.
     * 
     * @param ipAddress Ip address of the requester.
     * @param wsSessionKey The web service session key.
     * @return The logout response from the web services.
     */
    public Response invalidate(String ipv4Address, String wsSessionKey);
}
