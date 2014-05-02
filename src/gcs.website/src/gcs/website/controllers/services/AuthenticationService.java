package gcs.website.controllers.services;

import gcs.webapp.utils.HandledByHttpService;
import gcs.webapp.utils.HttpMethod;
import gcs.website.controllers.services.beans.requests.LoginRequest;
import gcs.website.controllers.services.beans.requests.LogoutRequest;
import gcs.website.controllers.services.beans.responses.LoginResponse;
import gcs.website.controllers.services.beans.responses.Response;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class AuthenticationService extends HttpService implements IAuthenticationService
{
	/**
	 * Validates the credentials of a user to create a web services' session
	 * @param ipAddress Ip address of the requester
	 * @param username The user's unique identifier
	 * @param password The user's password
	 * @return The login response from the web services
	 */
	@Override
	public LoginResponse login(String ipAddress, String username, String password) 
	{
	   final HandledByHttpService metadata = new HandledByHttpService("/authentication/login", HttpMethod.Post);
	   
		LoginRequest request = new LoginRequest();
		request.setIpAddress(ipAddress);
		request.setUsername(username);
		request.setPassword(password);
		LoginResponse response = this.getResponse(metadata, LoginResponse.class, request);
		
		return response;
	}
	
	/**
	 * Invalidates a web services' session
	 * @param ipAddress Ip address of the requester
	 * @param wsSessionKey The web service session key
	 * @return The logout response from the web services
	 */
	@Override
	public Response logout(String ipAddress, String wsSessionKey) 
	{
	   final HandledByHttpService metadata = new HandledByHttpService("/authentication/logout", HttpMethod.Delete);
	   
		LogoutRequest request = new LogoutRequest();
		request.setIpAddress(ipAddress);
		request.setSessionKey(wsSessionKey);
		Response response = this.getResponse(metadata, Response.class, request);
		
		return response;
	}

}
