package gcs.webservices.authentication;

import gcs.webapp.utils.Message;
import gcs.webapp.utils.MessageType;
import gcs.webservices.exceptions.InternalException;

import java.io.IOException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class ActiveDirectoryAuthentication implements ILDAPAuthentication 
{
	/**
	 * Url to the LDAP for accessing it
	 */
	private String ldapUrl;
	
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
	public LDAPAuthenticationResult authenticate(String uid, String password) throws InternalException 
	{
		return authenticate(defaultUserDomain, uid, password);
	}
	
	/**
	 * Authenticate a user through a LDAP.* 
	 * @param domain	Domain of the user in the ldap
	 * @param uid		User identification in the LDAP
	 * @param password	User password for authentication
	 * @return Whether the authentication was successful or not
	 * @throws InternalException 
	 */
	@Override
	public LDAPAuthenticationResult authenticate(String domain, String uid, String password) throws InternalException 
	{
		LDAPAuthenticationResult result = new LDAPAuthenticationResult();
//		DirContext context = null;
//		try
//	    {
//	        // Set up the environment for creating the initial context
//			// TODO: test
//	        Hashtable<String, String> environment = new Hashtable<String, String>();
//	        environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
//	        environment.put(Context.PROVIDER_URL, ldapUrl);
//	        environment.put(Context.SECURITY_AUTHENTICATION, "gssapi");
//	        environment.put(Context.SECURITY_PRINCIPAL,String.format("%s@%s", uid, domain));
//	        environment.put(Context.SECURITY_CREDENTIALS, password);
//	        
//	        // Create the initial context
//	        context = new InitialDirContext(environment);
//	        
//	        if(context != null) {
//		        result.setHasSucceeded(true);
//		        result.getMessages().add(new Message(MessageType.Information, "authentication_ldap_successful"));
//	        	context.close();
//	        }
//	    }
//	    catch (NamingException e)
//	    {
//	    	throw new InternalException(
//	    			"An exception has occured while authenticating the user " + uid, e);
//	    }
		
		try {
			LoginContext loginContext = new LoginContext("ActiveDirectoryAuthentication", 
					new AuthenticationCallbackHandler(uid, password));
			loginContext.login();
		} catch (LoginException e) {
	        result.setHasSucceeded(true);
	        result.getMessages().add(new Message(MessageType.Information, "authentication_ldap_unsuccessful"));
			throw new InternalException(
					"An exception has occured while authenticating the user " + uid, e);
		}
		
		return result;
	}
	
	/**
	 * Getter for the LDAP url
	 * @return The LDAP url
	 */
	public String getLdapUrl() 
	{
		return ldapUrl;
	}

	/**
	 * Setter for the LDAP url
	 * @param ldapUrl The new LDAP url
	 */
	public void setLdapUrl(String ldapUrl) 
	{
		this.ldapUrl = ldapUrl;
	}

	/**
	 * Getter for the default user domain
	 * @return The default user domain
	 */
	public String getDefaultUserDomain() 
	{
		return defaultUserDomain;
	}

	/**
	 * Setter for the default user domain
	 * @param defaultUserDomain The new default user domain
	 */
	public void setDefaultUserDomain(String defaultUserDomain) 
	{
		this.defaultUserDomain = defaultUserDomain;
	}
		
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
			for (int index = 0; index < callbacks.length; index++) {
				if (callbacks[index] instanceof NameCallback) {
					NameCallback ncb = (NameCallback) callbacks[index];
					ncb.setName(username);
				}
				if (callbacks[index] instanceof PasswordCallback) {
					PasswordCallback pcb = (PasswordCallback) callbacks[index];
					pcb.setPassword(password.toCharArray());
				}
			}
		}
	}
}
