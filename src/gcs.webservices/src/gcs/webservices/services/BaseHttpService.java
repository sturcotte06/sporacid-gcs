package gcs.webservices.services;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.sun.jersey.api.core.InjectParam;

import gcs.webapp.utils.MessageType;
import gcs.webapp.utils.app.messages.IMessageLocalizer;
import gcs.webservices.services.beans.responses.Response;

/**
 * 
 * @author Simon Turcotte-Langevin
 *
 */
@Component
public abstract class BaseHttpService 
{
	private Logger logger = Logger.getLogger(this.getClass());
	
	@InjectParam
	protected IMessageLocalizer messageLocalizer;
	
	/**
	 * Handles an exception by adding the 
	 * @param exception
	 * @param response
	 */
	public void handleException(Exception exception, Response response)
	{
		response.addMessage(MessageType.Error, exception.getMessage());
		if (exception.getCause() != null) {
			response.addMessage(MessageType.Error, "Caused by : " + exception.getCause().getMessage());
		}
		
		logger.error("An exception occured in an http service : ", exception);
	}
}
