package gcs.webservices.client;

import gcs.webservices.client.requests.membres.AddRequest;
import gcs.webservices.client.responses.Response;
import gcs.webservices.client.responses.membres.GetAllMembresOfClubResponse;

/**
 * @author Simon Turcotte-Langevin
 */
public interface IMembreServiceClient
{
    /**
     * Get all the membre beans from the webservices for the given club.
     * 
     * @param ipv4Address Ipv4 address of the requester.
     * @param sessionKey The web service session key.
     * @param clubName The name of the club for which we want the membres.
     * @return A collection of membre beans.
     */
    public GetAllMembresOfClubResponse getAllMembresOfClub(String ipv4Address, String sessionKey, String clubName);
    
    /**
     * self explanatory
     * 
     * @param ipv4Address
     * @param sessionKey
     * @param ldapSearchString
     * @param clubName
     * @return
     */
    public Response addMembre(String ipv4Address, String sessionKey, String clubName, AddRequest request);
}
