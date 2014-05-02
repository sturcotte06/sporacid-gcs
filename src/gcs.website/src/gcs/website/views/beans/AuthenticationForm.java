package gcs.website.views.beans;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class AuthenticationForm 
{
	@Length(min = 1, max = 10, message = "validation_authentication_username_bad_length")
	@Getter @Setter
	private String username;
	
	@NotEmpty(message = "validation_authentication_password_empty")
	@Getter @Setter
	private String password;
}
