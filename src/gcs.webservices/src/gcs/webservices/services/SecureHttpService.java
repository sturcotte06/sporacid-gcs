package gcs.webservices.services;

import gcs.webapp.utils.app.security.CrudOperation;
import gcs.webapp.utils.app.security.IModuleSecurityProvider;
import gcs.webapp.utils.app.security.SecureModule;

import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

import com.sun.jersey.api.core.InjectParam;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
@Component
@Path("/session/{ipv4Address}/{sessionKey}")
public class SecureHttpService extends BaseHttpService
{
	/** A security provider to test user rights. */
	@InjectParam
    private IModuleSecurityProvider moduleSecurityProvider;

	/**
	 * Resolves the current class SecureModule annotation.
	 * @param classObj 	Class object on which to perform reflection
	 * @return The current class secure module metadata, if it exists.				
	 */
	protected SecureModule resolveSecureModuleMetadata(Class<? extends SecureHttpService> classObj)
	{
		SecureModule module = null;	
		module = classObj.getAnnotation(SecureModule.class);
		return module;
	}
	
	/**
	 * Returns whether a certain role has rights or not within a 
	 * secure http service operation
	 * @param roleName			The name of the role
	 * @param operator			The operation the user which to do
	 * @param classObj			Class object on which to perform reflection
	 * @return Whether the user role has rights top operate or not
	 */
	protected boolean hasRights(String roleName, CrudOperation operation, Class<? extends SecureHttpService> classObj)
	{
		SecureModule module = resolveSecureModuleMetadata(classObj);
		return moduleSecurityProvider.hasRights(module.name(), roleName, operation);
	}
}
