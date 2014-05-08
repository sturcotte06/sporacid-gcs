package gcs.webapp.utils.exceptions;

/**
 * @author Simon Turcotte-Langevin
 */
public class InternalException extends Exception
{
    private static final long serialVersionUID = -4517218146505918640L;
    private String messageKey;

    public InternalException(String messageKey, String message)
    {
        super(message);
        this.messageKey = messageKey;
    }

    public InternalException(String messageKey, String message, Throwable innerException)
    {
        super(message, innerException);
        this.messageKey = messageKey;
    }

    /**
     * @return the messageKey
     */
    public String getMessageKey()
    {
        return messageKey;
    }
}
