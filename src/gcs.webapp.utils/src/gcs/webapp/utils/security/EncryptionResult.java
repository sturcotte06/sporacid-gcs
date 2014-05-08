package gcs.webapp.utils.security;

public class EncryptionResult 
{
	private String encryptedString;
	private String key;
		
    public EncryptionResult(String encryptedString, String key)
    {
        super();
        this.encryptedString = encryptedString;
        this.key = key;
    }

    /**
     * @return the encryptedString
     */
    public String getEncryptedString()
    {
        return encryptedString;
    }
    /**
     * @param encryptedString the encryptedString to set
     */
    public void setEncryptedString(String encryptedString)
    {
        this.encryptedString = encryptedString;
    }
    /**
     * @return the key
     */
    public String getKey()
    {
        return key;
    }
    /**
     * @param key the key to set
     */
    public void setKey(String key)
    {
        this.key = key;
    }
}
