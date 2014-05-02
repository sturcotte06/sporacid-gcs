package gcs.webapp.utils;

import lombok.Getter;
import lombok.Setter;

import org.codehaus.jackson.annotate.JsonIgnore;

import gcs.webapp.utils.app.messages.ILocalizable;
import gcs.webapp.utils.app.messages.IMessageLocalizer;

/**
 * Message structure for message transition in the application. 
 * @author Simon Turcotte-Langevin
 */
public class Message implements ILocalizable
{
   @Getter @Setter
	private MessageType messageType;
   
   @Getter @Setter
	private String messageContent;
	
	@JsonIgnore
	@Getter @Setter
	private Object[] format;
	
	public Message()
	{ }
	
	public Message(MessageType messageType, String messageContent, Object... format)
	{
		this.messageType= messageType; 
		this.messageContent = messageContent;
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
