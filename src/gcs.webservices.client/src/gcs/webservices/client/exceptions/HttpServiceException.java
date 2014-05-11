package gcs.webservices.client.exceptions;

import java.io.IOException;

import org.springframework.http.HttpStatus;

/**
 * @author Simon Turcotte-Langevin
 */
public class HttpServiceException extends IOException
{
    /** Auto-generated serialization version. */
    private static final long serialVersionUID = 2606538018669950563L;
    /** Status returned by the http service. */
    private HttpStatus httpStatus;

    /**
     * Constructor
     * @param httpStatus
     * @param message
     */
    public HttpServiceException(HttpStatus httpStatus, String message)
    {
        super(message);
        this.httpStatus = httpStatus;
    }

    /**
     * Constructor
     * @param httpStatus
     * @param message
     * @param innerException
     */
    public HttpServiceException(HttpStatus httpStatus, String message, Throwable innerException)
    {
        super(message, innerException);
        this.httpStatus = httpStatus;
    }

    /**
     * @return the httpStatus
     */
    public HttpStatus getHttpStatus()
    {
        return httpStatus;
    }
}
