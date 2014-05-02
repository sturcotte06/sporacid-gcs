package gcs.website.views.beans;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class WsSession 
{
   @Getter @Setter
	private String username;
   
   @Getter @Setter
	private String sessionKey;
   
   @Getter @Setter
	private Date expirationDate;
}
