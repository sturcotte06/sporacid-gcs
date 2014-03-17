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
	 * Number of character for a session key
	 */
	private static final int cSessionKeyLength = 64;
	
	/**
	 * The key in a string format
	 */
	private String key;
	
	/**
	 * Constructor
	 * Use the static generate() method instead if you want to create
	 * a new session key
	 */
	public PublicSessionKey() { }
	
	/**
	 * Getter for the key
	 * @return The key
	 */
	public String getKey() 
	{
		return key;
	}

	/**
	 * Setter for the key
	 * @param key The new key
	 */
	public void setKey(String key) 
	{
		this.key = key;
	}

	/**
	 * Generate a random session key
	 * @return A new session key with a randomly generated value
	 */
	public static PublicSessionKey generate()
	{
		PublicSessionKey sessionKey = new PublicSessionKey();
		sessionKey.setKey(RandomStringUtils.random(cSessionKeyLength, true, true));
		return sessionKey;
	}
}
