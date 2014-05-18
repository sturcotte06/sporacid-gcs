package gcs.webservices.services;

import gcs.webapp.utils.app.security.CrudOperation;
import gcs.webapp.utils.app.security.CrudOperator;
import gcs.webapp.utils.app.security.SecureModule;
import gcs.webservices.client.beans.ContextualSessionToken;
import gcs.webservices.client.requests.membres.*;
import gcs.webservices.client.responses.DummyResponse;
import gcs.webservices.dao.IMembreDao;
import gcs.webservices.ldap.search.ILdapSearcher;
import gcs.webservices.ldap.search.SearchBy;

import javax.naming.NamingException;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SecureModule(name = "members_module")
@Path("/context/{contextName}/session/{ipv4Address}/{sessionKey}")
public class MembreService extends SecureHttpService
{
    @Autowired
    private IMembreDao membreDao;

    @Autowired
    private ILdapSearcher ldapSearcher;

    @POST
    @CrudOperator(CrudOperation.Create)
    @Path("/membre")
    public Response add(@BeanParam ContextualSessionToken sessionToken, AddRequest request)
    {
        gcs.webservices.client.responses.Response responseEntity = new gcs.webservices.client.responses.Response();

        return completeRequest(responseEntity);
    }

    @GET
    @CrudOperator(CrudOperation.Create)
    @Path("/membre")
    public Response getAll(@BeanParam ContextualSessionToken sessionToken) throws NamingException
    {
        DummyResponse responseEntity = new DummyResponse();
        responseEntity.setObject(ldapSearcher.searchUser("AJ50440", SearchBy.SamAccountName));

        return completeRequest(responseEntity);
    }

    @GET
    @CrudOperator(CrudOperation.Read)
    @Path("/membre/{membreId}")
    public Response get(@BeanParam ContextualSessionToken sessionToken, @BeanParam GetRequest request)
    {
        gcs.webservices.client.responses.Response responseEntity = new gcs.webservices.client.responses.Response();

        // if (request != null) {
        // // Get the session from the session cache
        // AuthorizedSession session = sessionCache.acquireSession(request);
        //
        // if (session != null) {
        // // User is authenticated;
        // // check his role rights on the current module and crud op
        // // if he has rights, proceed
        // CrudOperator operator =
        // this.resolveOperatorMetadata(MembreService.class);
        // if (/*this.hasRights(session.getRoleName(), operator,
        // MembreService.class)*/true) {
        // // Membre membre = membreDao.getMember(request.getMembreId());
        // // responseEntity.setMembre(membre);
        // responseEntity.addMessage(MessageType.Information,
        // "members_get_member_successful");
        //
        // // Set the success flag in the response
        // responseEntity.setSuccess(true);
        // } else {
        // responseEntity.addMessage(MessageType.Error,
        // "members_get_member_invalid_request");
        // }
        // } else {
        // responseEntity.addMessage(MessageType.Error,
        // "security_authentication_required");
        // }
        // } else {
        // responseEntity.addMessage(MessageType.Error,
        // "security_denied_access");
        // }

        return completeRequest(responseEntity);
    }

    @PUT
    @CrudOperator(CrudOperation.Update)
    @Path("/membre/{membreId}/deactivate")
    public Response deactivate(@BeanParam ContextualSessionToken sessionToken, DeactivateRequest request)
    {
        gcs.webservices.client.responses.Response responseEntity = new gcs.webservices.client.responses.Response();

        return completeRequest(responseEntity);
    }

    @PUT
    @CrudOperator(CrudOperation.Update)
    @Path("/membre/{membreId}/role/{roleName}")
    public Response bindRole(@BeanParam ContextualSessionToken sessionToken, BindRoleRequest request)
    {
        gcs.webservices.client.responses.Response responseEntity = new gcs.webservices.client.responses.Response();

        return completeRequest(responseEntity);
    }

    /**
     * @return the membreDao
     */
    public IMembreDao getMembreDao()
    {
        return membreDao;
    }

    /**
     * @param membreDao the membreDao to set
     */
    public void setMembreDao(IMembreDao membreDao)
    {
        this.membreDao = membreDao;
    }

    /**
     * @return the ldapSearcher
     */
    public ILdapSearcher getLdapSearcher()
    {
        return ldapSearcher;
    }

    /**
     * @param ldapSearcher the ldapSearcher to set
     */
    public void setLdapSearcher(ILdapSearcher ldapSearcher)
    {
        this.ldapSearcher = ldapSearcher;
    }
}
