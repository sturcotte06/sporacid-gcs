package gcs.webapp.utils.app.security;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public interface IModuleSecurityProvider 
{
	/**
	 * Returns whether a certain role can or cannot do a certain
	 * operation on a certain module
	 * @param moduleName The module name to test
	 * @param roleName The role name to test
	 * @param operation The CRUD operation
	 * @return whether a certain role can or cannot do a certain operation on a certain module
	 */
	public boolean hasRights(String moduleName, String roleName, CrudOperation operation);
	/**
	 * Returns the rights of a certain role in a certain module 
	 * @param moduleName The module name
	 * @param roleName The role name
	 * @return The rights
	 */
	public Rights getRights(String moduleName, String roleName);
}
