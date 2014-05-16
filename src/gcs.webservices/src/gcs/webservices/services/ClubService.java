package gcs.webservices.services;

import gcs.webapp.utils.exceptions.InternalException;
import gcs.webservices.authentication.ActiveDirectory;
import gcs.webservices.dao.IClubDao;
import gcs.webservices.models.Club;

import java.util.Collection;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchResult;
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
	
	@GET
	@Path("/ad")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAD() {
		try {
			// Creating instance of ActiveDirectory
			ActiveDirectory activeDirectory = new ActiveDirectory("aj54280",
					"MzLaPq21",
					System.getProperty("java.security.krb5.realm"));

			// Searching
			NamingEnumeration<SearchResult> result = activeDirectory
					.searchUser("aj54280", "username",
							/*"dc=ens;dc=ad;dc=etsmtl;dc=ca;"*/null);

			if (result.hasMore()) 
			{
				final String[] adParams = 
					{ "sAMAccountName", "givenName", "cn",
						"mail", "objectClass", "objectCategory",
						"memberOf",  "objectGUID", "objectSid",
						"distinguishedName", "userPrincipalName",
						"sAMAccountType", "userAccountControl" 
						};

				SearchResult rs = (SearchResult) result.next();
				Attributes attrs = rs.getAttributes();

				for (int i = 0; i < adParams.length; i++) 
				{
					System.out.println(i + " : " + attrs.get(adParams[i]).toString());
				}
			} 
			else 
			{
				System.out.println("No search result found!");
			}

			return Response.ok().build();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.ok().build();
		}
	}
}
