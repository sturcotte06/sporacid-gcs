package gcs.webservices.client;

import gcs.webapp.utils.HttpMethod;
import gcs.webservices.client.requests.usagers.GetProfileResponse;
import gcs.webservices.client.responses.enums.GetClubsResponse;

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
        final HttpServiceRoute route = new HttpServiceRoute(String.format("/session/%s/%s/usager", ipv4Address,
                sessionKey), HttpMethod.Get);
        return getResponse(route, GetProfileResponse.class);
    }

    /**
     * Get all clubs associated with the web service's session.
     * 
     * @param ipv4Address Ipv4 address of the requester.
     * @param sessionKey The web service session key.
     * @return The get clubs response from the web services.
     */
    @Override
    public GetClubsResponse getClubs(String ipv4Address, String sessionKey)
    {
        final HttpServiceRoute route = new HttpServiceRoute("/session/%s/%s/usager/clubs", HttpMethod.Get);
        final HttpServiceCache cache = getCache();

        if (cache.has(route)) {
            // Cache value exists, return it.
            return (GetClubsResponse) cache.get(route);
        } else {
            // Cache value doesn't exist. Call the web services and cache the
            // response.
            GetClubsResponse response = getResponse(route, GetClubsResponse.class);
            cache.put(route, response);

            return response;
        }
    }
}
