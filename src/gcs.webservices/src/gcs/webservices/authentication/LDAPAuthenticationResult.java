package gcs.webservices.authentication;

import gcs.webapp.utils.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class LDAPAuthenticationResult 
{
	private boolean hasSucceeded;
	private List<Message> messages = new ArrayList<Message>();
	
	public boolean isHasSucceeded() 
	{
		return hasSucceeded;
	}
	
	public void setHasSucceeded(boolean hasSucceeded) 
	{
		this.hasSucceeded = hasSucceeded;
	}
	
	public List<Message> getMessages() 
	{
		return messages;
	}
	
	public void setMessages(List<Message> messages) 
	{
		this.messages = messages;
	}
}
