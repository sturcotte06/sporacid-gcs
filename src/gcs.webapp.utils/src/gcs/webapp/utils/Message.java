package gcs.webapp.utils;

import org.codehaus.jackson.annotate.JsonIgnore;

import gcs.webapp.utils.app.messages.ILocalizable;
import gcs.webapp.utils.app.messages.IMessageLocalizer;

/**
 * Message structure for message transition in the application.
 * 
 * @author Simon Turcotte-Langevin
 */
public class Message implements ILocalizable
{
    private MessageType messageType;
    private String messageContent;
    
    @JsonIgnore
    private Object[] format;

    public Message()
    {
    }

    public Message(MessageType messageType, String messageContent, Object... format)
    {
        this.setMessageType(messageType);
        this.setMessageContent(messageContent);
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
        this.setMessageContent(localizer.localizeFormatString(locale, getMessageContent(), format));
    }

    /**
     * @return the messageType
     */
    public MessageType getMessageType()
    {
        return messageType;
    }

    /**
     * @param messageType the messageType to set
     */
    public void setMessageType(MessageType messageType)
    {
        this.messageType = messageType;
    }

    /**
     * @return the messageContent
     */
    public String getMessageContent()
    {
        return messageContent;
    }

    /**
     * @param messageContent the messageContent to set
     */
    public void setMessageContent(String messageContent)
    {
        this.messageContent = messageContent;
    }

    /**
     * @return the format
     */
    public Object[] getFormat()
    {
        return format;
    }

    /**
     * @param format the format to set
     */
    public void setFormat(Object[] format)
    {
        this.format = format;
    }
}
