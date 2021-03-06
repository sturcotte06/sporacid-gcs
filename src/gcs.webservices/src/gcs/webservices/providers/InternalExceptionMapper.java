package gcs.webservices.providers;

import gcs.webapp.utils.Message;
import gcs.webapp.utils.app.messages.IMessageLocalizer;
import gcs.webapp.utils.exceptions.EntityNotFoundException;
import gcs.webapp.utils.exceptions.InternalException;
import gcs.webapp.utils.exceptions.NotAuthenticatedException;
import gcs.webapp.utils.exceptions.UnauthorizedException;
import gcs.webapp.utils.exceptions.ValidationException;
import gcs.webapp.utils.exceptions.WrongCredentialsException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Simon Turcotte-Langevin
 */
@Provider
public class InternalExceptionMapper implements ExceptionMapper<InternalException>
{
    @Autowired
    protected IMessageLocalizer messageLocalizer;

    /**
     * Handles an internal exception. Http status will be deducted from the
     * sub-type of the exception.
     * 
     * @param exception The internal exception that has occured.
     */
    @Override
    public Response toResponse(InternalException exception)
    {
        gcs.webservices.client.responses.Response responseEntity = new gcs.webservices.client.responses.Response();
        ResponseBuilder builder = null;

        // Add the localized message to the response's messages.
        responseEntity.addMessage(exception.toMessage());

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
        } else if (exception instanceof UnauthorizedException || exception instanceof WrongCredentialsException) {
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

        // Localize the exception and log it
        // exception.localize(messageLocalizer);
        // logger.error("Uncaught exception in InternalExceptionMapper: ",
        // exception);

        // Return the response with the error status and the response entity
        return builder.entity(responseEntity).type(MediaType.APPLICATION_JSON).build();
    }
}
