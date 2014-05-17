package gcs.webservices.services;

import gcs.webapp.utils.app.security.CrudOperation;
import gcs.webapp.utils.app.security.SecureModule;
import gcs.webservices.aop.Auditable;
import gcs.webservices.client.beans.ContextualSessionToken;
import gcs.webservices.client.requests.membres.*;
import gcs.webservices.dao.IMembreDao;
import gcs.webservices.ldap.search.ILdapSearcher;
import gcs.webservices.ldap.search.LdapUser;
import gcs.webservices.ldap.search.SearchBy;

import javax.naming.NamingException;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
    @Path("/membre")
    @Auditable
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response add(@BeanParam ContextualSessionToken sessionToken, AddRequest request)
    {
        final CrudOperation operation = CrudOperation.Create;
        gcs.webservices.client.responses.Response responseEntity = new gcs.webservices.client.responses.Response();

        return completeRequest(responseEntity);
    }

    public class LdapResponse extends gcs.webservices.client.responses.Response
    {
        private LdapUser ldapUser;

        /**
         * @return the ldapUser
         */
        public LdapUser getLdapUser()
        {
            return ldapUser;
        }

        /**
         * @param ldapUser the ldapUser to set
         */
        public void setLdapUser(LdapUser ldapUser)
        {
            this.ldapUser = ldapUser;
        }
    }
    
    @GET
    @Path("/membre")
    @Auditable
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getAll(@BeanParam ContextualSessionToken sessionToken) throws NamingException
    {
        final CrudOperation operation = CrudOperation.Create;
        LdapResponse responseEntity = new LdapResponse();

        responseEntity.setLdapUser(ldapSearcher.searchUser("AJ50440", SearchBy.SamAccountName));
        
        return completeRequest(responseEntity);
    }

    @GET
    @Path("/membre/{membreId}")
    @Auditable
    @Produces({ MediaType.APPLICATION_JSON })
    public Response get(@BeanParam ContextualSessionToken sessionToken, @BeanParam GetRequest request)
    {
        final CrudOperation operation = CrudOperation.Read;
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
    @Path("/membre/{membreId}/deactivate")
    @Auditable
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response deactivate(@BeanParam ContextualSessionToken sessionToken, DeactivateRequest request)
    {
        final CrudOperation operation = CrudOperation.Update;
        gcs.webservices.client.responses.Response responseEntity = new gcs.webservices.client.responses.Response();

        return completeRequest(responseEntity);
    }

    @PUT
    @Path("/membre/{membreId}/role/{roleName}")
    @Auditable
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response bindRole(@BeanParam ContextualSessionToken sessionToken, BindRoleRequest request)
    {
        final CrudOperation operation = CrudOperation.Update;
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
