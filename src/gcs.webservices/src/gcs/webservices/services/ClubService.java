package gcs.webservices.services;

import gcs.webapp.utils.exceptions.InternalException;
import gcs.webservices.dao.IClubDao;
import gcs.webservices.models.Club;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.sun.jersey.api.core.InjectParam;

@Path("/clubs")
//@SecureModule(name = "members_module")
@Component
public class ClubService 
{
	@InjectParam
	private IClubDao clubDao;
	
	@GET @Path("/") 
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getClubs()
	{
		//final CrudOperation operation = CrudOperation.Read;
		//gcs.webservices.services.beans.responses.Response responseEntity = new gcs.webservices.services.beans.responses.Response();
		
		// Logique d<affaire va dans le service !
		try
		{	
			Collection<Club> clubs = clubDao.getClubs();
			return Response.ok().entity(clubs).build();
		}
		catch(InternalException intex)
		{
			return null;
		}
	}
}
