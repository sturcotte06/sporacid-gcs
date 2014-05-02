package gcs.website.controllers.services.beans.requests;

import javax.ws.rs.QueryParam;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class AuthenticatedRequest extends Request 
{
	@QueryParam(value = "sessionKey")
	@Getter @Setter
	private String sessionKey;
	
	@QueryParam(value = "ipAddress")
	@Getter @Setter
	private String ipAddress;
}
