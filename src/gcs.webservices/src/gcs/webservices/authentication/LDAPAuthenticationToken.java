package gcs.webservices.authentication;

import java.util.Date;

import javax.security.auth.login.LoginContext;

import lombok.Getter;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class LDAPAuthenticationToken 
{
   @Getter
	private Date emittedAt;
   
   @Getter
	private String emittedFor;
   
   @Getter
	private LoginContext loginContext;
	
	public LDAPAuthenticationToken(String emittedFor, LoginContext context)
	{
		this.loginContext = context;
		this.emittedFor = emittedFor;
		this.emittedAt = new Date();
	}
}
