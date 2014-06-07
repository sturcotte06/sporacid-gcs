package gcs.webservices.client;

import gcs.webapp.utils.HttpMethod;
import gcs.webservices.client.models.MembreBean;
import gcs.webservices.client.requests.sessions.CreateRequest;
import gcs.webservices.client.responses.ResponseWithEntity;
import gcs.webservices.client.responses.sessions.CreateResponse;

import java.util.Collection;

/**
 * @author Simon Turcotte-Langevin
 */
public class MembreServiceClient extends HttpServiceClient implements IMembreServiceClient
{
    /**
     * Get all the membre beans from the webservices for the given club.
     * 
     * @param ipv4Address Ipv4 address of the requester.
     * @param wsSessionKey The web service session key.
     * @param clubName The name of the club for which we want the membres.
     * @return A collection of membre beans.
     */
    @Override
    public ResponseWithEntity getAllMembresOfClub(String ipv4Address, String wsSessionKey, String clubName)
    {
        final HttpServiceRoute route = new HttpServiceRoute("/context/%s/session/%s/%s", HttpMethod.Get);
        return getResponse(route, ResponseWithEntity.class);
    }
}
