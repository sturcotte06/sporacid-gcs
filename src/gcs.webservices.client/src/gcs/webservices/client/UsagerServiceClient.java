package gcs.webservices.client;

import gcs.webapp.utils.HttpMethod;
import gcs.webservices.client.requests.usagers.GetProfileResponse;

/**
 * @author Simon Turcotte-Langevin
 */
public class UsagerServiceClient extends HttpServiceClient implements IUsagerServiceClient
{
    /**
     * Get the user profile associated with the web service's session.
     * 
     * @param ipv4Address Ipv4 address of the requester.
     * @param sessionKey The web service session key.
     * @return The get profile response from the web services.
     */
    @Override
    public GetProfileResponse getProfile(String ipv4Address, String sessionKey)
    {
        HttpServiceRoute route = new HttpServiceRoute(String.format("/session/%s/%s/usager", ipv4Address, sessionKey),
                HttpMethod.Get);
        return getResponse(route, GetProfileResponse.class);
    }
}
