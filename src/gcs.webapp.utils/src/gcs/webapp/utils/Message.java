package gcs.webapp.utils;

import org.codehaus.jackson.annotate.JsonIgnore;

import gcs.webapp.utils.app.messages.ILocalizable;
import gcs.webapp.utils.app.messages.IMessageLocalizer;

/**
 * Message structure for message transition in the application. 
 * @author Simon Turcotte-Langevin
 */
public class Message implements ILocalizable
{
	private MessageType messageType;
	private String message;
	
	@JsonIgnore
	private Object[] format;
	
	public Message()
	{ }
	
	public Message(MessageType messageType, String messageContent, Object... format)
	{
		this.messageType= messageType; 
		this.message = messageContent;
		this.format = format;
	}
	
	public MessageType getMessageType()
	{
		return messageType;
	}
	
	public void setMessageType(MessageType messageType)
	{
		this.messageType = messageType;
	}
	
	public String getMessageContent()
	{
		return message;
	}
	
	public void setMessageContent(String messageContent)
	{
		this.message = messageContent;
	}

	public Object[] getFormat() 
	{
		return format;
	}

	public void setFormat(Object[] format) 
	{
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
}
