package gcs.webservices.authentication;

import gcs.webapp.utils.exceptions.InternalException;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class ActiveDirectoryAuthenticator implements ILDAPAuthenticator 
{	
	/**
	 * 
	 */
	private String defaultUserDomain;
	
	/**
	 * Authenticate a user through a LDAP.
	 * @param uid		User identification in the LDAP
	 * @param password	User password for authentication
	 * @return Whether the authentication was successful or not
	 */
	@Override
	public LDAPAuthenticationToken authenticate(String uid, String password) throws InternalException 
	{
		return authenticate(defaultUserDomain, uid, password);
	}
	
	/**
	 * Authenticate a user through a LDAP.
	 * @param domain	Domain of the user in the ldap
	 * @param uid		User identification in the LDAP
	 * @param password	User password for authentication
	 * @return Whether the authentication was successful or not
	 * @throws InternalException 
	 */
	@Override
	public LDAPAuthenticationToken authenticate(String domain, String uid, String password) throws InternalException 
	{
		LDAPAuthenticationToken token = null;

		try {
			LoginContext loginContext = new LoginContext(System.getProperty("gcs.login.context.id"), 
					new AuthenticationCallbackHandler(uid, password));
			loginContext.login();
			
			// Emit a new token
			token = new LDAPAuthenticationToken(uid, loginContext);
		} catch (LoginException ex) {
			throw new InternalException(
					"ldap_authentication_fail", 
					"An exception has occured while authenticating the user " + uid, ex);
		}
		
		return token;
	}
	
	/**
	 * Getter for the default user domain.
	 * @return The default user domain
	 */
	public String getDefaultUserDomain() 
	{
		return defaultUserDomain;
	}

	/**
	 * Setter for the default user domain.
	 * @param defaultUserDomain The new default user domain
	 */
	public void setDefaultUserDomain(String defaultUserDomain) 
	{
		this.defaultUserDomain = defaultUserDomain;
	}
		
	/**
	 * 
	 * @author Simon Turcotte-Langevin
	 */
	class AuthenticationCallbackHandler implements CallbackHandler
	{
		private String username;
		private String password;

		public AuthenticationCallbackHandler(final String username, final String password) 
		{
			super();
			this.username = username;
			this.password = password;
		}

		public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException 
		{
			for (int i = 0; i < callbacks.length; i++) {
				if (callbacks[i] instanceof NameCallback) {
					NameCallback ncb = (NameCallback) callbacks[i];
					ncb.setName(username);
				}
				if (callbacks[i] instanceof PasswordCallback) {
					PasswordCallback pcb = (PasswordCallback) callbacks[i];
					pcb.setPassword(password.toCharArray());
				}
			}
		}
	}
}
