package gcs.webservices.services;

import gcs.webapp.utils.app.security.CrudOperation;
import gcs.webapp.utils.app.security.CrudOperator;
import gcs.webservices.client.requests.usagers.*;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

@Component
@Path("/session/{ipv4Address}/{sessionKey}/user")
public class UserService extends BaseHttpService
{
    // @GET
    // @Path("/user/{membreId}")
    // @Auditable
    // @Produces({ MediaType.APPLICATION_JSON })
    // public Response get(EditPreferenceRequest request)
    // {
    // final CrudOperation operation = CrudOperation.Create;
    // gcs.webservices.client.responses.Response responseEntity = new
    // gcs.webservices.client.responses.Response();
    //
    // return endRequest(responseEntity);
    // }

    @PUT
    @Path("/{membreId}/preferences")
    @CrudOperator(CrudOperation.Update)
    public Response editPreferences(EditPreferenceRequest request)
    {
        gcs.webservices.client.responses.Response responseEntity = new gcs.webservices.client.responses.Response();
        return completeRequest(responseEntity);
    }

    @PUT
    @Path("/{membreId}")
    @CrudOperator(CrudOperation.Update)
    public Response edit(EditRequest request)
    {
        gcs.webservices.client.responses.Response responseEntity = new gcs.webservices.client.responses.Response();
        return completeRequest(responseEntity);
    }
}
