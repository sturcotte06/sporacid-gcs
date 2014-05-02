package gcs.website.controllers.services.beans.responses;

import gcs.webapp.utils.Message;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

/**
 * Server response message base structure
 * @author Simon Turcotte-Langevin
 */
@XmlRootElement
public class Response
{
   @Getter @Setter
	private boolean success;
   
   @Getter
	private List<Message> messages = new ArrayList<Message>();
}
