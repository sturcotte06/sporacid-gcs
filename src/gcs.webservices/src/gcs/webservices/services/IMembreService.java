package gcs.webservices.services;

import gcs.webservices.client.beans.ContextualSessionToken;
import gcs.webservices.client.requests.membres.AddRequest;
import gcs.webservices.client.requests.membres.BindRoleRequest;
import gcs.webservices.client.requests.membres.DeactivateRequest;
import gcs.webservices.client.requests.membres.GetRequest;

import javax.naming.NamingException;
import javax.ws.rs.core.Response;

public interface IMembreService
{
    /**
     * Add a membre in the system.
     * 
     * @param sessionToken
     * @param request
     * @return
     */
    public Response addBasic(ContextualSessionToken sessionToken, AddRequest request);
    
    /**
     * Adds a basic membre into the system.
     * 
     * @param request
     */
    public void addBasic(AddRequest request);

    /**
     * 
     * @param sessionToken
     * @return
     * @throws NamingException
     */
    public Response getAll(ContextualSessionToken sessionToken);
    
    /**
     * 
     * @param sessionToken
     * @param request
     * @return
     */
    public Response get(ContextualSessionToken sessionToken, GetRequest request);

    /**
     * 
     * @param sessionToken
     * @param request
     * @return
     */
    public Response deactivate(ContextualSessionToken sessionToken, DeactivateRequest request);

    /**
     * 
     * @param sessionToken
     * @param request
     * @return
     */
    public Response bindRole(ContextualSessionToken sessionToken, BindRoleRequest request);

}