package gcs.webservices.providers;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Simon Turcotte-Langevin
 */
@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException>
{
    @Override
    public Response toResponse(WebApplicationException exception)
    {
        // This exception is already mapped to an http response.
        return exception.getResponse();
    }
}
