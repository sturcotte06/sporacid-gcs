package gcs.webservices.services;

import gcs.webapp.utils.app.security.CrudOperation;
import gcs.webapp.utils.app.security.SecureModule;
import gcs.webservices.aop.Auditable;
import gcs.webservices.client.beans.Context;
import gcs.webservices.client.beans.SessionToken;
import gcs.webservices.client.beans.providers.InjectContext;
import gcs.webservices.client.requests.*;
import gcs.webservices.client.requests.membres.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.sun.jersey.api.core.InjectParam;

@Path("/context/{contextName}/session/{ipv4Address}/{sessionKey}")
@Component
@SecureModule(name = "members_module")
public class MembreService extends SecureHttpService
{
    @POST
    @Path("/membre")
    @Auditable
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response add(@InjectParam Context context, @InjectParam SessionToken sessionToken, AddRequest request)
    {
        final CrudOperation operation = CrudOperation.Create;
        gcs.webservices.client.responses.Response responseEntity = new gcs.webservices.client.responses.Response();

        return endRequest(responseEntity);
    }

    @GET
    @Path("/membre")
    @Auditable
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getAll(@InjectParam Context context, @InjectParam SessionToken sessionToken)
    {
        final CrudOperation operation = CrudOperation.Create;
        gcs.webservices.client.responses.Response responseEntity = new gcs.webservices.client.responses.Response();

        return endRequest(responseEntity);
    }

    @GET
    @Path("/membre/{membreId}")
    @Auditable
    @Produces({ MediaType.APPLICATION_JSON })
    public Response get(@InjectParam GetRequest request, @InjectParam Context context)
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

        return endRequest(responseEntity);
    }

    @PUT
    @Path("/membre/{membreId}/deactivate")
    @Auditable
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response deactivate(DeactivateRequest request)
    {
        final CrudOperation operation = CrudOperation.Update;
        gcs.webservices.client.responses.Response responseEntity = new gcs.webservices.client.responses.Response();

        return endRequest(responseEntity);
    }

    @PUT
    @Path("/membre/{membreId}/role/{roleName}")
    @Auditable
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response bindRole(BindRoleRequest request)
    {
        final CrudOperation operation = CrudOperation.Update;
        gcs.webservices.client.responses.Response responseEntity = new gcs.webservices.client.responses.Response();

        return endRequest(responseEntity);
    }
}
