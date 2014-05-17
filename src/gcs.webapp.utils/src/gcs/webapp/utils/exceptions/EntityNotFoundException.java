package gcs.webapp.utils.exceptions;

/**
 * Internal exception that should be thrown whenever a non-existant
 * entity is trying to be accessed.
 * 
 * @author Simon Turcotte-Langevin
 */
public class EntityNotFoundException extends InternalException
{
    /** Serialization unique identifier. */
    private static final long serialVersionUID = 56544205026241858L;

    /**
     * Constructor.
     * @param entityName Name of the entity that was not found.
     */
    public EntityNotFoundException(String entityName, Object entityId)
    {
        super("exception_entitynotfound_message", entityName, entityId);
    }
}
