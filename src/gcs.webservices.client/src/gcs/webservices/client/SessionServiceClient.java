package gcs.webservices.client;

import gcs.webapp.utils.HttpMethod;
import gcs.webservices.client.requests.sessions.CreateRequest;
import gcs.webservices.client.responses.Response;
import gcs.webservices.client.responses.sessions.CreateResponse;

/**
 * @author Simon Turcotte-Langevin
 */
public class SessionServiceClient extends HttpServiceClient implements ISessionServiceClient
{
    /**
     * Validates the credentials of a user and create a web services' session.
     * 
     * @param ipv4Address Ipv4 address of the requester.
     * @param username The user's unique identifier.
     * @param password The user's password.
     * @return The login response from the web services.
     */
    @Override
    public CreateResponse create(String username, String ipv4Address, String password)
    {
        final HttpServiceRoute route = new HttpServiceRoute("/session", HttpMethod.Post);

        CreateRequest request = new CreateRequest(username, ipv4Address, password);
        return getResponse(route, CreateResponse.class, request);
    }

    /**
     * Invalidates a web services' session.
     * 
     * @param ipAddress Ip address of the requester.
     * @param wsSessionKey The web service session key.
     * @return The logout response from the web services.
     */
    @Override
    public Response invalidate(String ipv4Address, String sessionKey)
    {
        HttpServiceRoute route = new HttpServiceRoute(
                String.format("/session/%s/%s", ipv4Address, sessionKey), HttpMethod.Delete);

        return getResponse(route, Response.class);
    }
}
