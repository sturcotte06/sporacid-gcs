package gcs.webservices.client.exceptions;

import gcs.webapp.utils.exceptions.InternalException;
import gcs.webservices.client.responses.Response;

/**
 * @author Simon Turcotte-Langevin
 */
public class WebServiceClientException extends InternalException
{
    /** */
    private static final long serialVersionUID = 4456523273644620786L;

    /** The response entity deserialized from the http response. */
    private Response responseEntity;

    /**
     * Constructor.
     * 
     * @param responseEntity
     */
    public WebServiceClientException(Response responseEntity)
    {
        super("exception_wsclient");
        this.responseEntity = responseEntity;
    }
    
    /**
     * Constructor.
     * 
     * @param responseEntity
     */
    public WebServiceClientException(Response responseEntity, Throwable innerEx)
    {
        super("exception_wsclient", innerEx);
        this.responseEntity = responseEntity;
    }

    /**
     * Constructor.
     * 
     * @param innerEx
     */
    public WebServiceClientException(Throwable innerEx)
    {
        super("exception_wsclient", innerEx);
    }

    /**
     * @return Whether there's a response entity or not.
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
