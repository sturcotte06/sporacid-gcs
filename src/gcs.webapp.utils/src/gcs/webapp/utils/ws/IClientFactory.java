package gcs.webapp.utils.ws;

import gcs.webapp.utils.exceptions.InternalException;

import javax.ws.rs.client.Client;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public interface IClientFactory
{
    /**
     * Creates a web service client to consume web services.
     * 
     * @return A web service client to consume web services.
     * @throws InternalException
     */
    public Client createClient() throws InternalException;
}
