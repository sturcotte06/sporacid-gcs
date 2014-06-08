package gcs.webservices.client;

import java.util.Collection;

import gcs.webapp.utils.HttpMethod;
import gcs.webservices.client.models.ConcentrationBean;
import gcs.webservices.client.responses.ResponseWithEntity;
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
    @SuppressWarnings("unchecked")
    public GetConcentrationResponse getConcentrations()
    {
        final HttpServiceRoute route = new HttpServiceRoute("/enumerations/concentrations", HttpMethod.Get);
        return getResponse(route, GetConcentrationResponse.class);
    }
}
