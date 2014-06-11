package gcs.webservices.services;

import java.util.ArrayList;
import java.util.Collection;

import gcs.webapp.utils.MessageType;
import gcs.webapp.utils.app.security.CrudOperation;
import gcs.webapp.utils.app.security.CrudOperator;
import gcs.webapp.utils.app.security.SecureModule;
import gcs.webapp.utils.exceptions.EntityNotFoundException;
import gcs.webapp.utils.reflect.ReflectionUtils;
import gcs.webservices.client.beans.ContextualSessionToken;
import gcs.webservices.client.models.membres.BaseMembreBean;
import gcs.webservices.client.requests.membres.*;
import gcs.webservices.client.responses.membres.GetAllMembresOfClubResponse;
import gcs.webservices.dao.IMembreDao;
import gcs.webservices.ldap.search.ILdapSearcher;
import gcs.webservices.ldap.search.LdapUser;
import gcs.webservices.ldap.search.SearchBy;
import gcs.webservices.models.Membre;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

@Component
@SecureModule(name = "members_module")
@Path("/context/{contextName}/session/{ipv4Address}/{sessionKey}/membre")
public class MembreService extends SecureHttpService implements IMembreService
{
    /** IMembreDao instance to access membre data. */
    private IMembreDao membreDao;

    /** ILdapSearcher instance to search for users in an ldap system. */
    private ILdapSearcher ldapSearcher;

    @Override
    @POST
    @CrudOperator(CrudOperation.Create)
    public Response addBasic(@BeanParam ContextualSessionToken sessionToken, AddRequest request)
    {
        // Delegate to the add that doesn't need authorization gasp
        addBasic(request);

        // Set the success flag in the response
        gcs.webservices.client.responses.Response response = new gcs.webservices.client.responses.Response();
        response.addMessage(MessageType.Information, "members_add_member_successful");
        response.setSuccess(true);

        return completeRequest(response);
    }

    /**
     * Adds a basic membre into the system.
     * <p>
     * Do not put any jax-rs annotation, because this method is internal.
     * </p>
     * 
     * @param request
     */
    @Override
    public void addBasic(AddRequest request)
    {
        LdapUser ldapUser = new LdapUser();
        boolean ldapUserFound = false;

        // Search the ldap for all criterion defined in the ldap search.
        SearchBy[] criterion = SearchBy.values();
        for (SearchBy criteria : criterion) {
            // Try to search the user for the current criteria
            ldapUserFound = ldapSearcher.trySearchUser(request.getLdapSearchString(), criteria, ldapUser);
            if (ldapUserFound) {
                // User was found. No need to search further.
                break;
            }
        }

        if (!ldapUserFound) {
            // We searched the ldap for the user for all supported criterion.
            // It was not found, nothing we can do.
            throw new EntityNotFoundException("ldap user", request.getLdapSearchString());
        }

        // Create a new membre from the information of the ldap user
        Membre membreToAdd = new Membre();
        membreToAdd.setCodeUniversel(ldapUser.getUsername());
        membreToAdd.setCourriel(ldapUser.getEmail());
        membreToAdd.setPrenom(ldapUser.getFirstName());
        membreToAdd.setNom(ldapUser.getLastName());
        // TODO have a deault value for concentration
        // membreToAdd.setConcentration(membreDao.getConcentrationByAcronyme("ti"));
        membreToAdd.setActif(true);

        // Add the membre to the system
        membreDao.addMembre(membreToAdd);
    }

    @Override
    @GET
    @CrudOperator(CrudOperation.Read)
    public Response getAll(@BeanParam ContextualSessionToken sessionToken)
    {
        Collection<Membre> membres = membreDao.getMembresByClubName(sessionToken.getContext().getName());
        Collection<BaseMembreBean> membreBeans = new ArrayList<>(membres.size());
        for (Membre membre : membres) {
            // Copy the system entity into a client bean
            membreBeans.add(ReflectionUtils.generateBean(membre, BaseMembreBean.class));
        }

        GetAllMembresOfClubResponse responseEntity = new GetAllMembresOfClubResponse();
        responseEntity.setEntity(membreBeans);
        responseEntity.addMessage(MessageType.Information, "members_getall_member_successful");
        responseEntity.setSuccess(true);

        return completeRequest(responseEntity);
    }

    @Override
    @GET
    @CrudOperator(CrudOperation.Read)
    @Path("/{membreId}")
    public Response get(@BeanParam ContextualSessionToken sessionToken, @BeanParam GetRequest request)
    {
        gcs.webservices.client.responses.Response responseEntity = new gcs.webservices.client.responses.Response();

        return completeRequest(responseEntity);
    }

    @Override
    @PUT
    @CrudOperator(CrudOperation.Update)
    @Path("/{membreId}/deactivate")
    public Response deactivate(@BeanParam ContextualSessionToken sessionToken, DeactivateRequest request)
    {
        gcs.webservices.client.responses.Response responseEntity = new gcs.webservices.client.responses.Response();

        return completeRequest(responseEntity);
    }

    @Override
    @PUT
    @CrudOperator(CrudOperation.Update)
    @Path("/{membreId}/role/{roleName}")
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
