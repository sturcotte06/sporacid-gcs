package gcs.webapp.utils.exceptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import gcs.webapp.utils.Message;
import gcs.webapp.utils.MessageType;
import gcs.webapp.utils.app.messages.ILocalizable;
import gcs.webapp.utils.app.messages.IMessageLocalizer;

public class ValidationException extends RuntimeException implements ILocalizable
{
    private static final long serialVersionUID = 417311887659656707L;
    private Message[] validationMessages;

    public ValidationException(Collection<String> validationMessageKeys)
    {
        super("Some arguments were invalid.");

        List<Message> validationMessages = new ArrayList<>();
        for (String validationMessageKey : validationMessageKeys) {
            validationMessages.add(new Message(MessageType.Validation, validationMessageKey));
        }

        this.validationMessages = validationMessages.<Message> toArray(this.getValidationMessages());
    }

    @Override
    public void localize(IMessageLocalizer localizer)
    {
        localize(localizer, localizer.getDefaultLocale());
    }

    @Override
    public void localize(IMessageLocalizer localizer, String locale)
    {
        for (Message validationMessage : validationMessages) {
            validationMessage.localize(localizer, locale);
        }
    }

    /**
     * @return the validationMessages
     */
    public Message[] getValidationMessages()
    {
        return validationMessages;
    }
}
