package gcs.webservices.client.exceptions;

import gcs.webapp.utils.exceptions.InternalException;
import gcs.webservices.client.responses.Response;

/**
 * Exception that should be thrown when the web services client fails to get a
 * valid response from the server.
 * 
 * @author Simon Turcotte-Langevin
 */
public class WebServiceClientException extends InternalException
{
    /** */
    private static final long serialVersionUID = -3578138443707303104L;
    
    /** */
    private Response responseEntity;

    /**
     * 
     * @param responseEntity
     * @param innerException
     */
    public WebServiceClientException(Response responseEntity, Throwable innerException)
    {
        super("exception_wsclient", innerException);
    }
    
    /**
     * 
     * @param responseEntity
     */
    public WebServiceClientException(Response responseEntity)
    {
        super("exception_wsclient");
    }
    
    /**
     * 
     * @param innerException
     */
    public WebServiceClientException(Throwable innerException)
    {
        super("exception_wsclient", innerException);
    }

    /**
     * @return whether the responseEntity is set
     */
    public boolean hasResponseEntity()
    {
        return responseEntity != null;
    }
    
    /**
     * @return the responseEntity
     */
    public Response getResponseEntity()
    {
        return responseEntity;
    }
}
