package gcs.webservices.services;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.sun.jersey.api.core.InjectParam;

import gcs.webapp.utils.MessageType;
import gcs.webapp.utils.app.messages.IMessageLocalizer;
import gcs.webapp.utils.exceptions.InternalException;
import gcs.webservices.authentication.SessionCache;
import gcs.webservices.services.beans.responses.Response;

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
	 * Handles an exception by adding the 
	 * @param exception
	 * @param response
	 */
	public void handleException(Exception exception, Response response)
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
}
