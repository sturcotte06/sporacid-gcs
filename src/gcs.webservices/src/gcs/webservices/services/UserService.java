package gcs.webservices.services;

import gcs.webapp.utils.app.security.CrudOperation;
import gcs.webservices.aop.Auditable;
import gcs.webservices.client.requests.usagers.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

@Component
public class UserService extends SecureHttpService
{
    /*@GET
    @Path("/user/{membreId}")
    @Auditable
    @Produces({ MediaType.APPLICATION_JSON })
    public Response get(EditPreferenceRequest request)
    {
        final CrudOperation operation = CrudOperation.Create;
        gcs.webservices.client.responses.Response responseEntity = new gcs.webservices.client.responses.Response();

        return endRequest(responseEntity);
    }*/

    @PUT
    @Path("/user/{membreId}/preferences")
    @Auditable
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response editPreferences(EditPreferenceRequest request)
    {
        final CrudOperation operation = CrudOperation.Create;
        gcs.webservices.client.responses.Response responseEntity = new gcs.webservices.client.responses.Response();

        return endRequest(responseEntity);
    }

    @PUT
    @Path("/user/{membreId}")
    @Auditable
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response edit(EditRequest request)
    {
        final CrudOperation operation = CrudOperation.Create;
        gcs.webservices.client.responses.Response responseEntity = new gcs.webservices.client.responses.Response();

        return endRequest(responseEntity);
    }
}
