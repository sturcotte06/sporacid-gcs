package gcs.webservices.services;

import gcs.webapp.utils.MessageType;
import gcs.webapp.utils.app.messages.IMessageLocalizer;
import gcs.webapp.utils.app.security.CrudOperation;
import gcs.webapp.utils.app.security.CrudOperator;
import gcs.webapp.utils.app.security.SecureModule;
import gcs.webservices.authentication.AuthorizedSession;
import gcs.webservices.authentication.SessionCache;
import gcs.webservices.services.beans.requests.GetMembreRequest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.core.InjectParam;

@Path("/membres")
@SecureModule(name = "members_module")
public class MembreService extends SecureHttpService
{
	@InjectParam
	private IMessageLocalizer messageLocalizer;
	
	@InjectParam
	private SessionCache sessionCache;
	
	@CrudOperator(operation = CrudOperation.Read)
	@GET @Path("/get-membre") 
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getMembre(@InjectParam GetMembreRequest request)
	{
		gcs.webservices.services.beans.responses.Response responseEntity = new gcs.webservices.services.beans.responses.Response();
		
		if (request != null) {
			// Get the session from the session cache
			AuthorizedSession session = sessionCache.getSession(request);

			if (session != null) {
				// User is authenticated;
				// check his role rights on the current module and crud op
				// if he has rights, proceed
				CrudOperator operator = this.resolveOperatorMetadata(MembreService.class);
				if (this.hasRights(session.getRoleName(), operator, MembreService.class)) {
					// Membre membre = membreDao.getMember(request.getMembreId());
					// responseEntity.setMembre(membre);
					responseEntity.addMessage(MessageType.Information, "members_get_member_successful");
					
					// Set the success flag in the response
					responseEntity.setSuccess(true);
				} else {
					responseEntity.addMessage(MessageType.Error, "members_get_member_invalid_request");
				}
			} else {
				responseEntity.addMessage(MessageType.Error, "security_authentication_required");
			}
		} else {
			responseEntity.addMessage(MessageType.Error, "security_denied_access");
		}
		
		// Localize the response in the default application locale
		responseEntity.localize(messageLocalizer);
		
		return Response.ok().entity(responseEntity).build();
	}
}
