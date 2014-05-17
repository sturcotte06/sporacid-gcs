package gcs.webapp.utils.exceptions;

/**
 * Internal exception that should be thrown whenever a user is trying to access
 * private resources without being authenticated.
 * 
 * @author Simon Turcotte-Langevin
 */
public class NotAuthenticatedException extends InternalException
{
    /** Serialization unique identifier. */
    private static final long serialVersionUID = 1917929910851679797L;

    /**
     * Constructor.
     * 
     * @param username Username of the user that was not authenticated.
     */
    public NotAuthenticatedException(String username)
    {
        super("exception_notauthenticated_message", username);
    }
}
