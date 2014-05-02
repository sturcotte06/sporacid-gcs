package gcs.webservices.services;

import gcs.webapp.utils.app.security.CrudOperator;
import gcs.webapp.utils.app.security.IModuleSecurityProvider;
import gcs.webapp.utils.app.security.SecureModule;
import gcs.webapp.utils.reflect.ReflectionUtils;

import java.lang.reflect.Method;

import org.springframework.stereotype.Component;

import com.sun.jersey.api.core.InjectParam;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
@Component
public abstract class SecureHttpService extends BaseHttpService
{
	/**
	 * A security provider to test rights
	 */
	@InjectParam
	private IModuleSecurityProvider moduleSecurityProvider;

	/**
	 * Resolves the current method CrudOperator annotation.
	 * @param classObj 	Class object on which to perform reflection
	 * @return The current method crud operator metadata, if it exists.				
	 */
	protected CrudOperator resolveOperatorMetadata(Class<? extends SecureHttpService> classObj)
	{
		CrudOperator operator = null;	
		
		// Theres no best way in java to identify the right method.
		// This should work in 99% of cases since overloads probably will share
		// the same web service metadata
		Method firstMatchingMethod = ReflectionUtils.getFirstCurrentMethodMetadata(classObj);
		operator = firstMatchingMethod.getAnnotation(CrudOperator.class);
		
		return operator;
	}
	
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
	protected boolean hasRights(String roleName, CrudOperator operator, Class<? extends SecureHttpService> classObj)
	{
		SecureModule module = resolveSecureModuleMetadata(classObj);
		return moduleSecurityProvider.hasRights(module.name(), roleName, operator.operation());
	}
}
