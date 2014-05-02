package gcs.webservices.services.beans.requests;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class LoginRequest extends Request 
{
   @Getter @Setter
	private String username;
   
   @Getter @Setter
	private String ipAddress;
   
   @Getter @Setter
	private String password;
}
