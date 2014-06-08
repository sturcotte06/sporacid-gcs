package gcs.webservices.services;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import gcs.webapp.utils.MessageType;
import gcs.webapp.utils.reflect.ReflectionUtils;
import gcs.webservices.client.models.ConcentrationBean;
import gcs.webservices.client.responses.ResponseWithEntity;
import gcs.webservices.dao.IMembreDao;
import gcs.webservices.models.Concentration;

import org.springframework.stereotype.Component;

@Path("/enumerations")
@Component
public class EnumService extends BaseHttpService
{
    private IMembreDao membreDao;

    @GET
    @Path("/concentrations")
    public Response getConcentrations()
    {
        Collection<Concentration> concentrations = membreDao.getConcentrations();
        Collection<ConcentrationBean> concentrationBeans = new ArrayList<>();
        for (Concentration concentration : concentrations) {
            concentrationBeans.add(ReflectionUtils.generateBean(concentration, Concentration.class,
                    ConcentrationBean.class));
        }

        ResponseWithEntity<Collection<ConcentrationBean>> responseEntity = new ResponseWithEntity<>();
        responseEntity.setEntity(concentrationBeans);
        responseEntity.addMessage(MessageType.Information, "enum_getconcentrations_successful");
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
}
