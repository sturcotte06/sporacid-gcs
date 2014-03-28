package gcs.webservices.services;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.sun.jersey.api.core.InjectParam;

import gcs.webapp.utils.MessageType;
import gcs.webapp.utils.app.messages.IMessageLocalizer;
import gcs.webapp.utils.exceptions.InternalException;
import gcs.webservices.authentication.SessionCache;

/**
 * 
 * @author Simon Turcotte-Langevin
 *
 */
@Component
public abstract class BaseHttpService 
{
	private static final Logger logger = Logger.getLogger(BaseHttpService.class);

	@InjectParam
	protected SessionCache sessionCache;
	
	@InjectParam
	protected IMessageLocalizer messageLocalizer;
		
	/**
	 * Handles an exception. Reason and cause of the exception will be
	 * added to the response entity.
	 * @param exception The exception that has occured
	 * @param response The response tntity to enrich
	 */
	public void handleException(Exception exception, gcs.webservices.services.beans.responses.Response response)
	{
		if (exception instanceof InternalException) {
			InternalException internalEx = (InternalException) exception;
			response.addMessage(MessageType.Error, internalEx.getMessageKey());
		} else {
			response.addMessage(MessageType.Error, exception.getMessage());
		}
		
		if (exception.getCause() != null) {
			response.addMessage(MessageType.Error, "base_exception_cause", exception.getCause().getMessage());
		}
		
		logger.error("An exception occured in an http service : ", exception);
	}
	
	/**
	 * Ends an http request by making last adjustements to the response
	 * and by sending the http response.
	 * @param responseEntity The response entity so far
	 * @return The http response to the client
	 */
	public Response endRequest(gcs.webservices.services.beans.responses.Response responseEntity)
	{
     // Localize the response in the default application locale
      responseEntity.localize(messageLocalizer);
      
	   return Response.ok().entity(responseEntity).build();
	}
}
