package gcs.webservices.authentication;

import java.io.Serializable;

import org.apache.commons.lang.RandomStringUtils;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class PublicSessionKey implements Serializable
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1089128612851215775L;

	/**
	 * Number of character for a session key.
	 */
	private static final int cSessionKeyLength = 64;
	
	/**
	 * The key in a string format.
	 */
	private String key;
	
	/**
	 * Constructor,
	 * Use the static generate() method if you want to create
	 * a new session key.
	 */
	public PublicSessionKey(String key) 
	{ 
		if (key.length() != cSessionKeyLength) {
			throw new IllegalArgumentException(String.format("The key must be %i characters long.", cSessionKeyLength));
		}
		
		if (!key.matches("[a-zA-Z0-9]*")) {
			throw new IllegalArgumentException("The key must be composed of alphanumeric characters only.");
		}
		
		this.key = key;
	}
	
	/**
	 * Getter for the key.
	 * @return The key
	 */
	public String getKey() 
	{
		return key;
	}

	/**
	 * Generate a random session key.
	 * @return A new session key with a randomly generated value
	 */
	public static PublicSessionKey generate()
	{
		return new PublicSessionKey(RandomStringUtils.random(cSessionKeyLength, true, true));
	}
}
