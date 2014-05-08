package gcs.website.controllers.services.beans.responses;

import gcs.webapp.utils.Message;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Server response message base structure
 * 
 * @author Simon Turcotte-Langevin
 */
@XmlRootElement
public class Response
{
    private boolean success;
    private List<Message> messages = new ArrayList<Message>();

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
