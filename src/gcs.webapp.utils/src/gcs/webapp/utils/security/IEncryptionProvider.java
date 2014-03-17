package gcs.webapp.utils.security;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public interface IEncryptionProvider 
{
	/**
	 * 
	 * @param toEncrypt
	 * @return
	 */
	public EncryptionResult encrypt(String toEncrypt);
	/**
	 * 
	 * @param toDecrypt
	 * @param key
	 * @return
	 */
	public String decrypt(String toDecrypt, String key);
	/**
	 * 
	 * @param EncryptionResult result
	 * @return
	 */
	public String decrypt(EncryptionResult result);
}
