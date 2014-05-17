package gcs.webservices.client;

import gcs.webapp.utils.HandledByHttpService;
import gcs.webapp.utils.HttpMethod;
import gcs.webservices.client.beans.SessionToken;
import gcs.webservices.client.requests.sessions.CreateRequest;
import gcs.webservices.client.responses.Response;
import gcs.webservices.client.responses.sessions.CreateResponse;

/**
 * @author Simon Turcotte-Langevin
 */
public class AuthenticationServiceClient extends HttpServiceClient implements IAuthenticationServiceClient
{
    /**
     * Validates the credentials of a user to create a web services' session
     * 
     * @param ipAddress Ip address of the requester
     * @param username The user's unique identifier
     * @param password The user's password
     * @return The login response from the web services
     */
    @Override
    public CreateResponse login(String username, String ipv4Address, String password)
    {
        final HandledByHttpService metadata = new HandledByHttpService("/authentication/login", HttpMethod.Post);
        CreateRequest request = new CreateRequest(username, ipv4Address, password);
        validateRequest(request);
        return getResponse(metadata, CreateResponse.class, request);
    }

    /**
     * Invalidates a web services' session.
     * 
     * @param ipAddress Ip address of the requester.
     * @param sessionKey The web service session key.
     * @return The logout response from the web services.
     */
    @Override
    public Response logout(String ipv4Address, String sessionKey)
    {
        final HandledByHttpService metadata = new HandledByHttpService("/authentication/logout", HttpMethod.Delete);

        SessionToken token = new SessionToken(ipv4Address, sessionKey);
        // validateRequest(request);
        return null;
        // return getResponse(metadata, Response.class, request);
    }
}
