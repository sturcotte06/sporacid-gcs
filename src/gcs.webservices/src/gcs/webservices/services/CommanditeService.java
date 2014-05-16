package gcs.webservices.services;

import gcs.webapp.utils.MessageType;
import gcs.webapp.utils.app.security.CrudOperation;
import gcs.webapp.utils.app.security.SecureModule;
import gcs.webservices.dao.ICommanditeDao;
import gcs.webservices.services.beans.responses.CommanditeResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.core.InjectParam;

@Path("/context/{contextName}/session/{ipAddress}/{sessionKey}/commandite")
@SecureModule(name = "commandite_module")
public class CommanditeService extends SecureHttpService
{
	@InjectParam
	private ICommanditeDao commanditeDao;
	
	@GET @Path("/") 
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getMembre(@PathParam("sessionKey") String sessionKey, @PathParam("contextName") String contextName)
	{
	    final CrudOperation operation = CrudOperation.Read;
	    CommanditeResponse commanditeResponse = new CommanditeResponse();
	    
	    commanditeResponse.setCommandites(commanditeDao.getAllCommandite());
	    commanditeResponse.setSuccess(true);
	    commanditeResponse.addMessage(MessageType.Information , "Success");

				
		return endRequest(commanditeResponse);
	}
	/**
	 * @return the commanditeDao
	 */
	public ICommanditeDao getCommanditeDao() {
		return commanditeDao;
	}
	/**
	 * @param commanditeDao the commanditeDao to set
	 */
	public void setCommanditeDao(ICommanditeDao commanditeDao) {
		this.commanditeDao = commanditeDao;
	}
}
