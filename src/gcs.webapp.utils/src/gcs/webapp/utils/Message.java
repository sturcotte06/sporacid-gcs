package gcs.webapp.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import gcs.webapp.utils.app.messages.ILocalizable;
import gcs.webapp.utils.app.messages.IMessageLocalizer;

/**
 * Message structure for message transition in the application.
 * 
 * @author Simon Turcotte-Langevin
 */
public class Message implements ILocalizable
{
    @JsonIgnore
    private Object[] format;
    private MessageType type;
    private String key;
    private String content;

    public Message()
    {}

    public Message(MessageType messageType, String messageKey, Object... format)
    {
        this.type = messageType;
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

    /**
     * @return the type
     */
    public MessageType getType()
    {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(MessageType type)
    {
        this.type = type;
    }
}
