package gcs.webservices.client;

import gcs.webapp.utils.HttpMethod;
import gcs.webservices.client.responses.enums.GetClubsResponse;
import gcs.webservices.client.responses.enums.GetConcentrationResponse;

/**
 * @author Simon Turcotte-Langevin
 */
public class EnumServiceClient extends HttpServiceClient implements IEnumServiceClient
{    
    /**
     * Get all the concentration beans enumeration values from the webservices.
     * 
     * @return A collection of concentration beans.
     */
    public GetConcentrationResponse getConcentrations()
    {
        final HttpServiceRoute route = new HttpServiceRoute("/enumerations/concentrations", HttpMethod.Get);
        return getResponse(route, GetConcentrationResponse.class);
    }
    
    /**
     * Get all the club beans enumeration values from the webservices.
     * 
     * @return A collection of club beans.
     */
    public GetClubsResponse getClubs()
    {
        final HttpServiceRoute route = new HttpServiceRoute("/enumerations/clubs", HttpMethod.Get);
        final HttpServiceCache cache = getCache();
        
        if (cache.has(route)) {
            // Cache value exists, return it.
            return (GetClubsResponse) cache.get(route);
        } else {
            // Cache value doesn't exist. Call the web services and cache the response.
            GetClubsResponse response = getResponse(route, GetClubsResponse.class);
            cache.put(route, response);
            
            return response;
        }
    }
}
