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
    private String key;
    private String content;

    @JsonIgnore
    private Object[] format;

    public Message()
    {
    }

    public Message(MessageType messageType, String messageKey, Object... format)
    {
        this.messageType = messageType;
        this.key = messageKey;
        this.content = messageKey;
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
        this.content = localizer.localizeFormatString(locale, content, format);
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
     * @return the key
     */
    public String getKey()
    {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key)
    {
        this.key = key;
    }

    /**
     * @return the content
     */
    public String getContent()
    {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content)
    {
        this.content = content;
    }
}
