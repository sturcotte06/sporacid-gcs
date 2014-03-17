package gcs.webapp.utils.security;

public class HashResult 
{
	private String hashedString;
	private String salt;
	
	public String getHashedString() 
	{
		return hashedString;
	}
	
	public void setHashedString(String hashedString) 
	{
		this.hashedString = hashedString;
	}
	
	public String getSalt() 
	{
		return salt;
	}
	
	public void setSalt(String salt) 
	{
		this.salt = salt;
	}
}
