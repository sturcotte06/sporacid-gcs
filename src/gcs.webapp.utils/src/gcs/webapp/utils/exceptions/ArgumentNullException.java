package gcs.webapp.utils.exceptions;

/**
 * Exception that should be thrown whenever a supplied argument
 * that should not be null, is null.
 * 
 * @author Simon Turcotte-Langevin
 */
public class ArgumentNullException extends IllegalArgumentException 
{
    /** Serialization unique identifier. */
	private static final long serialVersionUID = -1983992417757941538L;

	/**
	 * Constructor.
	 * @param name Name of the null argument.
	 */
	public ArgumentNullException(String name)
	{
		super(name + " cannot be null.");
	}
}
