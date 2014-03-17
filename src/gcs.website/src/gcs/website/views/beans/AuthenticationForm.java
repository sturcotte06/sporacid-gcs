package gcs.website.views.beans;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class AuthenticationForm 
{
	@Length(min = 1, max = 10, message = "validation_authentication_username_bad_length")
	private String username;
	@NotEmpty(message = "validation_authentication_password_empty")
	private String password;
	
	public String getUsername() 
	{
		return username;
	}
	
	public void setUsername(String username) 
	{
		this.username = username;
	}
	
	public String getPassword() 
	{
		return password;
	}
	
	public void setPassword(String password) 
	{
		this.password = password;
	}
}
