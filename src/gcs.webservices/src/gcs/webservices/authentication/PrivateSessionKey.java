package gcs.webservices.authentication;

import java.io.Serializable;

import gcs.webapp.utils.security.IHashProvider;

public class PrivateSessionKey implements Serializable
{
    private static final long serialVersionUID = 7414010532159015180L;
    private String hashedKey;

    public PrivateSessionKey(IHashProvider hashProvider, String key, String ipAddress)
    {
        setHashedKey(hashProvider.hash(key, ipAddress).getHashedString());
    }

    /**
     * @return the hashedKey
     */
    public String getHashedKey()
    {
        return hashedKey;
    }

    /**
     * @param hashedKey the hashedKey to set
     */
    public void setHashedKey(String hashedKey)
    {
        this.hashedKey = hashedKey;
    }
}
