package gcs.webservices.services.beans.responses;

import gcs.webapp.utils.Message;
import gcs.webapp.utils.MessageType;
import gcs.webapp.utils.app.messages.ILocalizable;
import gcs.webapp.utils.app.messages.IMessageLocalizer;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Server response message base structure
 * @author Simon Turcotte-Langevin
 */
@XmlRootElement
public class Response implements ILocalizable
{
	private boolean success;
	private List<Message> messages = new ArrayList<Message>();
	
	public List<Message> getMessages()
	{
		return messages;
	}

	public void addMessage(MessageType type, String messageContent)
	{
		messages.add(new Message(type, messageContent));
	}
	
	public boolean isSuccess()
	{
		return success;
	}

	public void setSuccess(boolean success)
	{
		this.success = success;
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
}
