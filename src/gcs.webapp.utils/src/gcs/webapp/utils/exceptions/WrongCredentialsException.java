package gcs.webapp.utils.exceptions;

/**
 * @author Simon Turcotte-Langevin
 */
public class WrongCredentialsException extends InternalException
{
    /** */
    private static final long serialVersionUID = 2192159550342513475L;

    /**
     * Constructor
     * 
     * @param messageKey
     * @param innerException
     */
    public WrongCredentialsException(String messageKey, Throwable innerException)
    {
        super(messageKey, innerException);
    }
}
