package gcs.webservices.exceptions;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class InternalException extends Exception 
{
	private static final long serialVersionUID = -4517218146505918640L;
	
	public InternalException(String message)
	{
		super(message);
	}
	
	public InternalException(String message, Throwable innerException)
	{
		super(message, innerException);
	}
}
