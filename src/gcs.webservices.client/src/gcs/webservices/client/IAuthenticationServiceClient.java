package gcs.webservices.client;

import gcs.webservices.client.responses.Response;
import gcs.webservices.client.responses.authentication.LoginResponse;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public interface IAuthenticationServiceClient 
{
	/**
	 * Validates the credentials of a user to create a web services' session
	 * @param ipAddress Ip address of the requester
	 * @param username The user's unique identifier
	 * @param password The user's password
	 * @return The login response from the web services
	 */
	public LoginResponse login(String ipAddress, String username, String password);
	/**
	 * Invalidates a web services' session
	 * @param ipAddress Ip address of the requester
	 * @param wsSessionKey The web service session key
	 * @return The logout response from the web services
	 */
	public Response logout(String ipAddress, String wsSessionKey);
}