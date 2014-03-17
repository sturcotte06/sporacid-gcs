package gcs.webapp.utils.security;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public interface IHashProvider 
{
	/**
	 * 
	 * @param toHash
	 * @return
	 */
	public HashResult hash(String toHash);
	/**
	 * 
	 * @param toHash
	 * @param salt
	 * @return
	 */
	public HashResult hash(String toHash, String salt);
}
