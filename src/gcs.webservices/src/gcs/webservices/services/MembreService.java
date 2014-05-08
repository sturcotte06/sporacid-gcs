package gcs.webservices.services;

import gcs.webapp.utils.app.security.CrudOperation;
import gcs.webapp.utils.app.security.SecureModule;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// @Path("/session/{ipAddress}/{sessionKey}/membre")
@SecureModule(name = "members_module")
public class MembreService extends SecureHttpService
{
	@GET @Path("/membre/{membreId}") 
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getMembre(@PathParam("sessionKey") String sessionKey, 
	        @PathParam("membreId") Integer membreId)
	{
	    final CrudOperation operation = CrudOperation.Read;
		gcs.webservices.services.beans.responses.Response responseEntity = new gcs.webservices.services.beans.responses.Response();

//		if (request != null) {
//			// Get the session from the session cache
//			AuthorizedSession session = sessionCache.acquireSession(request);
//
//			if (session != null) {
//				// User is authenticated;
//				// check his role rights on the current module and crud op
//				// if he has rights, proceed
//				CrudOperator operator = this.resolveOperatorMetadata(MembreService.class);
//				if (/*this.hasRights(session.getRoleName(), operator, MembreService.class)*/true) {
//					// Membre membre = membreDao.getMember(request.getMembreId());
//					// responseEntity.setMembre(membre);
//					responseEntity.addMessage(MessageType.Information, "members_get_member_successful");
//					
//					// Set the success flag in the response
//					responseEntity.setSuccess(true);
//				} else {
//					responseEntity.addMessage(MessageType.Error, "members_get_member_invalid_request");
//				}
//			} else {
//				responseEntity.addMessage(MessageType.Error, "security_authentication_required");
//			}
//		} else {
//			responseEntity.addMessage(MessageType.Error, "security_denied_access");
//		}
				
		return endRequest(responseEntity);
	}
}
