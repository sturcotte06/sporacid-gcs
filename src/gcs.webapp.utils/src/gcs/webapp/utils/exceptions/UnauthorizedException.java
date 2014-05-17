package gcs.webapp.utils.exceptions;

/**
 * Internal exception that should be thrown whenever a user is trying to access
 * private resources that he has no rights on.
 * 
 * @author Simon Turcotte-Langevin
 */
public class UnauthorizedException extends InternalException
{
    /** Serialization unique identifier. */
    private static final long serialVersionUID = 8469469509356946941L;

    /**
     * Constructor.
     * 
     * @param username Username of the user that was not authenticated.
     */
    public UnauthorizedException(String username)
    {
        super("exception_unauthorized_message", username);
    }
}
