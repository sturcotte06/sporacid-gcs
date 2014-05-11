package gcs.webservices.client.responses;

import gcs.webapp.utils.Message;
import gcs.webapp.utils.MessageType;
import gcs.webapp.utils.app.messages.ILocalizable;
import gcs.webapp.utils.app.messages.IMessageLocalizer;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Server response message base structure
 * 
 * @author Simon Turcotte-Langevin
 */
@XmlRootElement
public class Response implements ILocalizable
{
    private boolean success;
    private List<Message> messages = new ArrayList<>();

    public void addMessage(MessageType type, String messageContent, Object... format)
    {
        messages.add(new Message(type, messageContent, format));
    }

    @Override
    public void localize(IMessageLocalizer localizer)
    {
        localize(localizer, localizer.getDefaultLocale());
    }

    @Override
    public void localize(IMessageLocalizer localizer, String locale)
    {
        for (Message message : messages) {
            message.localize(localizer, locale);
        }
    }

    /**
     * @return the success
     */
    public boolean isSuccess()
    {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    /**
     * @return the messages
     */
    public List<Message> getMessages()
    {
        return messages;
    }
}
