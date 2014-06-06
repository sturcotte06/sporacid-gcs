package gcs.webapp.utils.exceptions;

import java.io.Serializable;

/**
 * Internal exception that should be thrown whenever something bad happen while
 * reflecting the application.
 * 
 * @author Simon Turcotte-Langevin
 */
public class ReflectionException extends InternalException implements Serializable
{
    /** */
    private static final long serialVersionUID = 6717797210968807152L;

    /**
     * Constructor.
     */
    public ReflectionException()
    {
        super("exception_reflection_message");
    }

    /**
     * Constructor
     * 
     * @param innerException The exception's cause.
     */
    public ReflectionException(Throwable innerException)
    {
        super("exception_reflection_message", innerException);
    }
}
