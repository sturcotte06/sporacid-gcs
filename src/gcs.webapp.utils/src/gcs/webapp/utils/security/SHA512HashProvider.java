package gcs.webapp.utils.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang.RandomStringUtils;

/**
 * @author Simon Turcotte-Langevin
 */
public class SHA512HashProvider implements IHashProvider
{
    /**
     * A TESTER
     * 
     * @param toHash
     * @return
     */
    @Override
    public HashResult hash(String toHash)
    {
        // Generate a random salt for better security
        return hash(toHash, RandomStringUtils.random(32, 0, 255, true, true));
    }

    /**
     * @param toHash
     * @param salt
     * @return
     */
    @Override
    public HashResult hash(String toHash, String salt)
    {
        HashResult hashResult = null;

        try {
            // Get the object from java security to hash in SHA512
            MessageDigest digest = MessageDigest.getInstance("SHA-512");

            byte[] hashedStringBytes;
            byte[] toHashBytes = (toHash + salt).getBytes();

            // Compute the hash
            hashedStringBytes = digest.digest(toHashBytes);

            // Build the hash result
            hashResult = new HashResult(new String(hashedStringBytes), salt);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hashResult;
    }
}
