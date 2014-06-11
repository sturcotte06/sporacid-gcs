package gcs.webservices.client;

import gcs.webapp.utils.HttpMethod;
import gcs.webservices.client.responses.membres.GetAllMembresOfClubResponse;

/**
 * @author Simon Turcotte-Langevin
 */
public class MembreServiceClient extends HttpServiceClient implements IMembreServiceClient
{
    /**
     * Get all the membre beans from the webservices for the given club.
     * 
     * @param ipv4Address Ipv4 address of the requester.
     * @param sessionKey The web service session key.
     * @param clubName The name of the club for which we want the membres.
     * @return A collection of membre beans.
     */
    @Override
    public GetAllMembresOfClubResponse getAllMembresOfClub(String ipv4Address, String sessionKey, String clubName)
    {
        final HttpServiceRoute route = new HttpServiceRoute(String.format("/context/%s/session/%s/%s/membre", clubName,
                ipv4Address, sessionKey), HttpMethod.Get);
        return getResponse(route, GetAllMembresOfClubResponse.class);
    }
}
