package gcs.webapp.utils.exceptions;

import gcs.webapp.utils.app.messages.ILocalizable;
import gcs.webapp.utils.app.messages.IMessageLocalizer;

/**
 * @author Simon Turcotte-Langevin
 */
public class InternalException extends RuntimeException implements ILocalizable
{
    /** Serialization unique identifier. */
    private static final long serialVersionUID = -4517218146505918640L;

    /** The exception's message. */
    private String message;
    /** The exception's message key. */
    private String messageKey;
    /** Format object for the message. */
    private Object[] format;
    
    /**
     * Constructor
     * 
     * @param messageKey The message key that allows localization. The message
     *            key will be stored in the exception's message field.
     */
    public InternalException(String messageKey, Object... format)
    {
        this(messageKey, null, format);
    }
 
    /**
     * Constructor
     * 
     * @param messageKey The message key that allows localization. The message
     *            key will be stored in the exception's message field.
     * @param innerException The exception's cause.
     */
    public InternalException(String messageKey, Throwable innerException, Object... format)
    {
        super((String) null, innerException);
        
        this.message = messageKey;
        this.messageKey = messageKey;
        this.format = format;
    }
    @Override
    public void localize(IMessageLocalizer localizer)
    {
        localize(localizer, localizer.getDefaultLocale());
    }

    @Override
    public void localize(IMessageLocalizer localizer, String locale)
    {
        // Localize the exception's message
        message = localizer.localizeFormatString(locale, messageKey, format);
    }

    /**
     * @return the message
     */
    @Override
    public String getMessage()
    {
        return message;
    }

    /**
     * @return the messageKey
     */
    public String getMessageKey()
    {
        return messageKey;
    }
}