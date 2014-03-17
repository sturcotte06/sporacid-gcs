package gcs.webapp.utils.security;

public class EncryptionResult 
{
	private String encryptedString;
	private String key;
	
	public String getEncryptedString() 
	{
		return encryptedString;
	}
	
	public void setEncryptedString(String encryptedString) 
	{
		this.encryptedString = encryptedString;
	}
	
	public String getKey() 
	{
		return key;
	}
	
	public void setKey(String key) 
	{
		this.key = key;
	}
}
