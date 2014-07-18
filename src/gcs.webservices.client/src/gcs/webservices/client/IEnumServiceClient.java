package gcs.webservices.client;

import gcs.webservices.client.responses.enums.GetClubsResponse;
import gcs.webservices.client.responses.enums.GetConcentrationResponse;

/**
 * @author Simon Turcotte-Langevin
 */
public interface IEnumServiceClient
{
    /**
     * Get all the concentration beans enumeration values from the webservices.
     * 
     * @return A collection of concentration beans.
     */
    public GetConcentrationResponse getConcentrations();
    
    /**
     * Get all the club beans enumeration values from the webservices.
     * 
     * @return A collection of club beans.
     */
    public GetClubsResponse getClubs();
}
