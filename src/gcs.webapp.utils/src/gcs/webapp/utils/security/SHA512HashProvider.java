package gcs.webapp.utils.security;

import gcs.webapp.utils.exceptions.InternalException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang.RandomStringUtils;

/**
 * @author Simon Turcotte-Langevin
 */
public class SHA512HashProvider implements IHashProvider
{
    /** Algorithm name for the SHA512 hash provider. */
    private static final String cAlgorithmName = "SHA-512";
    
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
            MessageDigest digest = MessageDigest.getInstance(cAlgorithmName);

            byte[] hashedStringBytes;
            byte[] toHashBytes = (toHash + salt).getBytes();

            // Compute the hash
            hashedStringBytes = digest.digest(toHashBytes);

            // Build the hash result
            hashResult = new HashResult(new String(hashedStringBytes), salt);

        } catch (NoSuchAlgorithmException ex) {
            throw new InternalException("hashing_no_algorithm_found", ex, cAlgorithmName);
        }

        return hashResult;
    }
}
