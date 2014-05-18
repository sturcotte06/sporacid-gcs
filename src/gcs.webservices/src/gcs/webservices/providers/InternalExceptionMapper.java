package gcs.webservices.providers;

import gcs.webapp.utils.Message;
import gcs.webapp.utils.MessageType;
import gcs.webapp.utils.app.messages.IMessageLocalizer;
import gcs.webapp.utils.exceptions.EntityNotFoundException;
import gcs.webapp.utils.exceptions.InternalException;
import gcs.webapp.utils.exceptions.NotAuthenticatedException;
import gcs.webapp.utils.exceptions.UnauthorizedException;
import gcs.webapp.utils.exceptions.ValidationException;
import gcs.webapp.utils.exceptions.WrongCredentialsException;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Simon Turcotte-Langevin
 */
@Provider
public class InternalExceptionMapper implements ExceptionMapper<InternalException>
{    
    /** Log4j logger. */
    private static final Logger logger = Logger.getLogger(InternalExceptionMapper.class);
    
    @Autowired
    protected IMessageLocalizer messageLocalizer;

    /**
     * Handles an internal exception. Http status will be deducted from the
     * sub-type of the exception.
     * 
     * @param exception The internal exception that has occured.
     */
    @Override
    @Produces({ MediaType.APPLICATION_JSON })
    public Response toResponse(InternalException exception)
    {
        logger.error("Uncaught exception in InternalExceptionMapper: ", exception);
        
        gcs.webservices.client.responses.Response responseEntity = new gcs.webservices.client.responses.Response();
        ResponseBuilder builder = null;

        // Localize the exception
        // exception.localize(messageLocalizer);

        // Add the localized message to the response's messages.
        responseEntity.addMessage(MessageType.Error, exception.getMessageKey());

        // Assign the response's status according to the type of exception
        // generated.
        if (exception instanceof EntityNotFoundException) {
            // Set the response as status 404
            builder = Response.status(Status.NOT_FOUND);
        } else if (exception instanceof ValidationException) {
            ValidationException validationEx = (ValidationException) exception;
            for (Message message : validationEx.getValidationMessages()) {
                responseEntity.addMessage(message);
            }

            // Set the response as status 400
            builder = Response.status(Status.BAD_REQUEST);
        } else if (exception instanceof UnauthorizedException || 
                   exception instanceof WrongCredentialsException) {
            // Set the response as status 403
            builder = Response.status(Status.FORBIDDEN);
        } else if (exception instanceof NotAuthenticatedException) {
            // Set the response as status 401
            builder = Response.status(Status.UNAUTHORIZED);
        } else {
            // Set the response as status 500
            builder = Response.status(Status.INTERNAL_SERVER_ERROR);
        }

        // Localize the response entity
        responseEntity.localize(messageLocalizer);

        // Return the response with the error status and the response entity
        return builder.entity(responseEntity).build();
    }
}
