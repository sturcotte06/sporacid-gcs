package gcs.webservices.services;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import gcs.webapp.utils.app.security.SecureModule;
import gcs.webapp.utils.exceptions.InternalException;
import gcs.webservices.dao.IClubDao;
import gcs.webservices.dao.IFournisseurDao;
import gcs.webservices.models.Club;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SecureModule(name = "suppliers_module")
@Path("/context/{contextName}/session/{ipv4Address}/{sessionKey}/fournisseur")
public class FournisseurService extends SecureHttpService
{
	@Autowired
    private IFournisseurDao fournisseurDao;

    @GET
    // @Path("/")
    public Response getClubs()
    {
        //TOOD
    	return null;
    }
}
