package gcs.webservices.services;

import gcs.webapp.utils.MessageType;
import gcs.webapp.utils.reflect.ReflectionUtils;
import gcs.webservices.client.beans.SessionToken;
import gcs.webservices.client.models.usagers.UserProfileBean;
import gcs.webservices.client.requests.usagers.*;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

@Component
@Path("/session/{ipv4Address}/{sessionKey}/usager")
public class UsagerService extends BaseHttpService
{
    @GET
    public Response getProfile(@BeanParam SessionToken sessionToken)
    {
        final GetProfileResponse responseEntity = new GetProfileResponse();

        // Get the membre associated with the session
        sessionCache.withSession(sessionToken, (session) -> {
            UserProfileBean profile = ReflectionUtils.generateBean(session.getMembre(), UserProfileBean.class);
            responseEntity.setEntity(profile);
        });

        responseEntity.setSuccess(true);
        responseEntity.addMessage(MessageType.Information, "usager_getprofile_successful");

        return completeRequest(responseEntity);
    }

    @PUT
    @Path("/{membreId}/preferences")
    public Response mergePreferences(@BeanParam SessionToken sessionToken, EditPreferenceRequest request)
    {
        gcs.webservices.client.responses.Response responseEntity = new gcs.webservices.client.responses.Response();
        return completeRequest(responseEntity);
    }

    @PUT
    @Path("/{membreId}")
    public Response editProfile(@BeanParam SessionToken sessionToken, EditRequest request)
    {
        gcs.webservices.client.responses.Response responseEntity = new gcs.webservices.client.responses.Response();
        return completeRequest(responseEntity);
    }
}
