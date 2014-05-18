package gcs.webapp.utils.security;

import gcs.webapp.utils.aspects.logging.Loggable;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
@Loggable
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
