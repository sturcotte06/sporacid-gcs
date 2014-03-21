package gcs.webservices.authentication;

import gcs.webapp.utils.exceptions.InternalException;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public interface ILDAPAuthentication 
{
	/**
	 * Authenticate a user through a LDAP
	 * @param uid		User identification in the LDAP
	 * @param password	User password for authentication
	 * @return The authentication result which holds if it was successful or not
	 */
	public LDAPAuthenticationToken authenticate(String uid, String password) throws InternalException;
	/**
	 * Authenticate a user through a LDAP
	 * @param domain	Domain of the user in the ldap
	 * @param uid		User identification in the LDAP
	 * @param password	User password for authentication
	 * @return The authentication result which holds if it was successful or not
	 */
	public LDAPAuthenticationToken authenticate(String domain, String uid, String password) throws InternalException;
}
