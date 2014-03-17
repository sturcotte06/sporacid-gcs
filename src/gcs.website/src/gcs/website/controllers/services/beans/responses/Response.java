package gcs.website.controllers.services.beans.responses;

import gcs.webapp.utils.Message;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Server response message base structure
 * @author Simon Turcotte-Langevin
 */
@XmlRootElement
public class Response
{
	private boolean success;
	private List<Message> messages = new ArrayList<Message>();
	
	public List<Message> getMessages()
	{
		return messages;
	}

	public boolean isSuccess()
	{
		return success;
	}

	public void setSuccess(boolean success)
	{
		this.success = success;
	}
}
