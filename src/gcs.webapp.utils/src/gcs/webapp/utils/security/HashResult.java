package gcs.webapp.utils.security;

public class HashResult
{
    private String hashedString;
    private String salt;

    public HashResult(String hashedString, String salt)
    {
        super();
        this.hashedString = hashedString;
        this.salt = salt;
    }

    /**
     * @return the hashedString
     */
    public String getHashedString()
    {
        return hashedString;
    }

    /**
     * @param hashedString the hashedString to set
     */
    public void setHashedString(String hashedString)
    {
        this.hashedString = hashedString;
    }

    /**
     * @return the salt
     */
    public String getSalt()
    {
        return salt;
    }

    /**
     * @param salt the salt to set
     */
    public void setSalt(String salt)
    {
        this.salt = salt;
    }
}
