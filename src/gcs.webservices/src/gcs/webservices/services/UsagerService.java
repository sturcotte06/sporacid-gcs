package gcs.webservices.services;

import java.util.ArrayList;
import java.util.Collection;

import gcs.webapp.utils.MessageType;
import gcs.webapp.utils.Wrapper;
import gcs.webapp.utils.reflect.ReflectionUtils;
import gcs.webservices.client.beans.SessionToken;
import gcs.webservices.client.models.ClubBean;
import gcs.webservices.client.models.usagers.UserProfileBean;
import gcs.webservices.client.requests.usagers.*;
import gcs.webservices.dao.IMembreDao;
import gcs.webservices.models.Club;
import gcs.webservices.models.Membre;

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
    private IMembreDao membreDao;
    
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
    public Response editProfile(@BeanParam SessionToken sessionToken, EditRequest request)
    {
        gcs.webservices.client.responses.Response responseEntity = new gcs.webservices.client.responses.Response();
        return completeRequest(responseEntity);
    }
    
    @GET
    @Path("/clubs")
    public Response getClubs(@BeanParam SessionToken sessionToken)
    {
        final GetClubsResponse responseEntity = new GetClubsResponse();
        
        final Wrapper<Collection<Club>> clubs = new Wrapper<>();
        sessionCache.withSession(sessionToken, (session) -> {
            Membre membre = session.getMembre();
            clubs.setObject(membreDao.getClubsOfMembre(membre));
        });
        
        Collection<ClubBean> clubBeans = new ArrayList<>(clubs.getObject().size());
        for (Club club : clubs.getObject()) {
            // Copy the system entity into a client bean
            clubBeans.add(ReflectionUtils.generateBean(club, ClubBean.class));
        }
        
        responseEntity.setEntity(clubBeans);
        responseEntity.setSuccess(true);
        responseEntity.addMessage(MessageType.Information, "usager_getclubs_successful");
                
        return completeRequest(responseEntity);
    }

    @PUT
    @Path("/preferences")
    public Response mergePreferences(@BeanParam SessionToken sessionToken, EditPreferenceRequest request)
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
}
