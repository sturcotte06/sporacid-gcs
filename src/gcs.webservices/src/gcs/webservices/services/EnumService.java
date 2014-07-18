package gcs.webservices.services;

import gcs.webapp.utils.MessageType;
import gcs.webapp.utils.reflect.ReflectionUtils;
import gcs.webservices.client.models.ClubBean;
import gcs.webservices.client.models.ConcentrationBean;
import gcs.webservices.client.responses.enums.GetClubsResponse;
import gcs.webservices.client.responses.enums.GetConcentrationResponse;
import gcs.webservices.dao.IClubDao;
import gcs.webservices.dao.IMembreDao;
import gcs.webservices.models.Club;
import gcs.webservices.models.Concentration;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

@Path("/enumerations")
@Component
public class EnumService extends BaseHttpService
{
    private IClubDao clubDao;
    private IMembreDao membreDao;

    @GET
    @Path("/concentrations")
    public Response getConcentrations()
    {
        Collection<Concentration> concentrations = membreDao.getConcentrations();
        Collection<ConcentrationBean> concentrationBeans = new ArrayList<>();
        for (Concentration concentration : concentrations) {
            concentrationBeans.add(ReflectionUtils.generateBean(concentration, ConcentrationBean.class));
        }

        GetConcentrationResponse responseEntity = new GetConcentrationResponse();
        responseEntity.setEntity(concentrationBeans);
        responseEntity.addMessage(MessageType.Information, "enum_getconcentrations_successful");
        responseEntity.setSuccess(true);

        return completeRequest(responseEntity);
    }

    @GET
    @Path("/clubs")
    public Response getClubs() {
        Collection<Club> clubs = clubDao.getClubs();
        Collection<ClubBean> clubBeans = new ArrayList<>();
        for (Club club : clubs) {
            clubBeans.add(ReflectionUtils.generateBean(club, ClubBean.class));
        }
        
        GetClubsResponse responseEntity = new GetClubsResponse();
        responseEntity.setEntity(clubBeans);
        responseEntity.addMessage(MessageType.Information, "enum_getclubs_successful");
        responseEntity.setSuccess(true);
        
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
     * @return the clubDao
     */
    public IClubDao getClubDao()
    {
        return clubDao;
    }

    /**
     * @param clubDao the clubDao to set
     */
    public void setClubDao(IClubDao clubDao)
    {
        this.clubDao = clubDao;
    }
}
