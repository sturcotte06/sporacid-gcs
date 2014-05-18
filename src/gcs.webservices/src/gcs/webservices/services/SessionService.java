package gcs.webservices.services;

import gcs.webapp.utils.MessageType;
import gcs.webapp.utils.exceptions.EntityNotFoundException;
import gcs.webservices.client.beans.SessionToken;
import gcs.webservices.client.requests.sessions.*;
import gcs.webservices.client.responses.sessions.CreateResponse;
import gcs.webservices.dao.IMembreDao;
import gcs.webservices.ldap.authentication.ILdapAuthenticator;
import gcs.webservices.ldap.authentication.LdapAuthenticationToken;
import gcs.webservices.ldap.search.ILdapSearcher;
import gcs.webservices.models.Membre;
import gcs.webservices.sessions.PublicSessionKey;

import javax.ws.rs.BeanParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/")
public class SessionService extends BaseHttpService
{
    @Autowired
    private ILdapAuthenticator ldapAuthenticator;

    @Autowired
    private IMembreDao membreDao;

    @Autowired
    private ILdapSearcher ldapSearcher;

    @POST
    @Path("/session")
    public Response create(CreateRequest request)
    {
        CreateResponse responseEntity = new CreateResponse();

        // Try to authenticate the user
        LdapAuthenticationToken authenticationToken = ldapAuthenticator.authenticate(request.getUsername(),
                request.getPassword());

        // Get the member infos from the database for the authenticated
        // user
        Membre membre = null;
        try {
            membre = membreDao.getMembre(request.getUsername());
        } catch (EntityNotFoundException ex) {
            // User does not exist, create it.
            // TODO User does not exist, create it.
        }

        // The Ldap verified and approved the credentials
        // Create a new session in the application
        PublicSessionKey sessionKey = sessionCache.createSessionFor(request.getIpv4Address(), membre,
                authenticationToken);
        responseEntity.setSessionKey(sessionKey.getKey());
        responseEntity.addMessage(MessageType.Information, "session_create_success");

        // Set the success flag in the response
        responseEntity.setSuccess(true);

        return completeRequest(responseEntity);
    }

    @DELETE
    @Path("/session/{ipv4Address}/{sessionKey}")
    public Response logout(@BeanParam SessionToken sessionToken)
    {
        gcs.webservices.client.responses.Response responseEntity = new gcs.webservices.client.responses.Response();

        // Remove the session from the cache; Any action taken
        // from now on with the previous key will be refused
        sessionCache.removeSession(sessionToken.getIpv4Address(), sessionToken.getSessionKey());
        responseEntity.addMessage(MessageType.Information, "session_logout_success");

        // Set the success flag in the response
        responseEntity.setSuccess(true);

        return completeRequest(responseEntity);
    }

    /**
     * @return the ldapAuthenticator
     */
    public ILdapAuthenticator getLdapAuthenticator()
    {
        return ldapAuthenticator;
    }

    /**
     * @param ldapAuthenticator the ldapAuthenticator to set
     */
    public void setLdapAuthenticator(ILdapAuthenticator ldapAuthenticator)
    {
        this.ldapAuthenticator = ldapAuthenticator;
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
}
