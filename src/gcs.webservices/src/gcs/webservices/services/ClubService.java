package gcs.webservices.services;

import gcs.webapp.utils.app.security.SecureModule;
import gcs.webapp.utils.exceptions.InternalException;
import gcs.webservices.dao.IClubDao;
import gcs.webservices.models.Club;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SecureModule(name = "clubs_module")
@Path("/clubs")
public class ClubService extends SecureHttpService
{
    @Autowired
    private IClubDao clubDao;

    @GET
    // @Path("/")
    public Response getClubs()
    {
        // final CrudOperation operation = CrudOperation.Read;
        // gcs.webservices.services.beans.responses.Response responseEntity =
        // new gcs.webservices.services.beans.responses.Response();

        // Logique d<affaire va dans le service !
        try {
            Collection<Club> clubs = clubDao.getClubs();
            return Response.ok().entity(clubs).build();
        } catch (InternalException intex) {
            return null;
        }
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
