package gcs.webservices.client;

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
     * @param wsSessionKey The web service session key.
     * @param clubName The name of the club for which we want the membres.
     * @return A collection of membre beans.
     */
    public GetAllMembresOfClubResponse getAllMembresOfClub(String ipv4Address, String wsSessionKey, String clubName);
}
