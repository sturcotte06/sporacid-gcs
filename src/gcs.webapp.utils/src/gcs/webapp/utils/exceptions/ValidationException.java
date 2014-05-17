package gcs.webapp.utils.exceptions;

import java.util.Collection;

import gcs.webapp.utils.Message;
import gcs.webapp.utils.MessageType;
import gcs.webapp.utils.app.messages.IMessageLocalizer;

/**
 * Internal exception that should be thrown whenever one or many objects are
 * invalid.
 * 
 * @author Simon Turcotte-Langevin
 */
public class ValidationException extends InternalException
{
    /** Serialization unique identifier. */
    private static final long serialVersionUID = 417311887659656707L;

    /** Array of validation message for the object that was invalid. */
    private Message[] validationMessages;

    /**
     * @param objectName The invalid object name.
     * @param validationMessageKeys The validation message keys.
     */
    public ValidationException(Collection<String> validationMessageKeys)
    {
        super("exception_validation_message");

        int iMessage = 0;
        Message[] validationMessages = new Message[validationMessageKeys.size()];
        for (String validationMessageKey : validationMessageKeys) {
            validationMessages[iMessage++] = new Message(MessageType.Validation, validationMessageKey);
        }

        this.validationMessages = validationMessages;
    }

    /**
     * @return the validationMessages
     */
    public Message[] getValidationMessages()
    {
        return validationMessages;
    }

    @Override
    public void localize(IMessageLocalizer localizer, String locale)
    {
        super.localize(localizer, locale);

        // Localize every validation messages
        for (Message validationMessage : validationMessages) {
            validationMessage.localize(localizer, locale);
        }
    }
}
