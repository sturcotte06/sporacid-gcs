package gcs.website.controllers.services.beans.responses;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class LoginResponse extends Response 
{
   @Getter @Setter
	private String sessionKey;
}
