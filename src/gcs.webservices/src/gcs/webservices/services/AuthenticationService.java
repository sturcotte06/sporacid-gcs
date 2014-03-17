package gcs.webservices.services;

import gcs.webapp.utils.MessageType;
import gcs.webapp.utils.app.messages.IMessageLocalizer;
import gcs.webservices.authentication.ILDAPAuthentication;
import gcs.webservices.authentication.SessionCache;
import gcs.webservices.authentication.PublicSessionKey;
import gcs.webservices.exceptions.InternalException;
import gcs.webservices.services.beans.requests.LoginRequest;
import gcs.webservices.services.beans.requests.LogoutRequest;
import gcs.webservices.services.beans.responses.LoginResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.sun.jersey.api.core.InjectParam;

@Component
@Path("/authentication")
public class AuthenticationService
{
	@InjectParam
	private IMessageLocalizer messageLocalizer;
	
	@InjectParam
	private SessionCache sessionCache;
	
	@InjectParam
	private ILDAPAuthentication ldapAuthenticator;
	
	@GET @Path("sup")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response sup()
	{
		gcs.webservices.services.beans.responses.Response responseEntity = new gcs.webservices.services.beans.responses.Response();
		responseEntity.addMessage(MessageType.Information, "Sup?");
		return Response.ok().entity(responseEntity).build();
	}
	
	@POST @Path("/login")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response login(LoginRequest loginRequest)
	{
		LoginResponse responseEntity = new LoginResponse();
		
		if (loginRequest != null) {
			boolean authenticationSuccessful;
			try {
				// Try to authenticate the user
				authenticationSuccessful = ldapAuthenticator.authenticate(
						loginRequest.getUsername(), loginRequest.getPassword()).isHasSucceeded();
			} catch (InternalException e) {
				// Could not authenticate the user
				authenticationSuccessful = false;
				responseEntity.addMessage(MessageType.Error, e.getMessage());
				if (e.getCause() != null) {
					responseEntity.addMessage(MessageType.Error, "Caused by : " + e.getCause().getMessage());
				}
			}
			
			if (authenticationSuccessful) {
				// The Ldap verified and approved the credentials
				// Create a new session in the application
				PublicSessionKey sessionKey = sessionCache.createSessionFor(loginRequest.getIpAddress(), loginRequest.getUsername());
				
				if (sessionKey != null) {
					responseEntity.setSessionKey(sessionKey.getKey());
					responseEntity.addMessage(MessageType.Information, "authentication_login_success");
					
					// Set the success flag in the response
					responseEntity.setSuccess(true);
				} else {
					// TODO
				}
				
			} else {
				responseEntity.addMessage(MessageType.Error, "authentication_login_wrong_username");
			}
		} else {
			responseEntity.addMessage(MessageType.Error, "authentication_login_invalid_request");
		}
		
		// Localize the response in the default application locale
		responseEntity.localize(messageLocalizer);
		
		return Response.ok().entity(responseEntity).build();
	}
	
	@DELETE @Path("/logout")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response logout(LogoutRequest logoutRequest)
	{
		gcs.webservices.services.beans.responses.Response responseEntity = new gcs.webservices.services.beans.responses.Response();
		
		if (logoutRequest != null) {
			// Remove the session from the cache; Any action taken
			// from now on with the previous key will be refused
			sessionCache.removeSession(logoutRequest.getIpAddress(), logoutRequest.getSessionKey());
			responseEntity.addMessage(MessageType.Information, "authentication_logout_success");
			
			// Set the success flag in the response
			responseEntity.setSuccess(true);
		} else {
			responseEntity.addMessage(MessageType.Error, "authentication_logout_invalid_request");
		}
		
		// Localize the response in the default application locale
		responseEntity.localize(messageLocalizer);
		
		return Response.ok().entity(responseEntity).build();
	}
}
