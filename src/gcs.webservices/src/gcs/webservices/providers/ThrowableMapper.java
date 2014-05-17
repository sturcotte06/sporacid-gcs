package gcs.webservices.providers;

import gcs.webapp.utils.MessageType;
import gcs.webapp.utils.app.messages.IMessageLocalizer;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Simon Turcotte-Langevin
 */
@Provider
public class ThrowableMapper implements ExceptionMapper<Throwable>
{    
    /** Log4j logger. */
    private static final Logger logger = Logger.getLogger(ThrowableMapper.class);
    
    @Autowired
    protected IMessageLocalizer messageLocalizer;

    /**
     * Handles a throwable. This is the most generic exception scenario
     * possible.
     * 
     * @param exception The exception that has occured.
     */
    @Override
    @Produces({ MediaType.APPLICATION_JSON })
    public Response toResponse(Throwable exception)
    {
        logger.error("Uncaught exception in ThrowableMapper: ", exception);
        
        // Set up an error response to the client
        gcs.webservices.client.responses.Response responseEntity = new gcs.webservices.client.responses.Response();        
        responseEntity.addMessage(MessageType.Error, "base_exception_fatal");
        responseEntity.localize(messageLocalizer);

        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseEntity).build();
    }
}
