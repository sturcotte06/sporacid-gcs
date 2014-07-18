package gcs.webservices.client;

import gcs.webservices.client.requests.usagers.GetProfileResponse;
import gcs.webservices.client.responses.enums.GetClubsResponse;

/**
 * @author Simon Turcotte-Langevin
 */
public interface IUsagerServiceClient
{
    /**
     * Get the user profile associated with the web service's session.
     * 
     * @param ipv4Address Ipv4 address of the requester.
     * @param sessionKey The web service session key.
     * @return The get profile response from the web services.
     */
    public GetProfileResponse getProfile(String ipv4Address, String sessionKey);

    /**
     * Get all clubs associated with the web service's session.
     * 
     * @param ipv4Address Ipv4 address of the requester.
     * @param sessionKey The web service session key.
     * @return The get clubs response from the web services.
     */
    public GetClubsResponse getClubs(String ipv4Address, String sessionKey);
}
