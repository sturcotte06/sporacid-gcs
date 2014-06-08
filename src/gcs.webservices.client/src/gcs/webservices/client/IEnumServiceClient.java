package gcs.webservices.client;

import gcs.webservices.client.models.ConcentrationBean;
import gcs.webservices.client.responses.ResponseWithEntity;

import java.util.Collection;

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
    public ResponseWithEntity<Collection<ConcentrationBean>> getConcentrations();
}
