package gcs.webapp.utils.exceptions;

public class ArgumentNullException extends IllegalArgumentException 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1983992417757941538L;

	public ArgumentNullException(String name)
	{
		super(name + " cannot be null.");
	}
}
