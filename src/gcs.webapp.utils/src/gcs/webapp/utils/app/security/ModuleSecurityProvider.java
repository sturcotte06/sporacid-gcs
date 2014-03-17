package gcs.webapp.utils.app.security;

import gcs.webapp.utils.io.RegexFilenameFilter;
import gcs.webapp.utils.xml.XmlUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jdom2.Element;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class ModuleSecurityProvider implements IModuleSecurityProvider
{
	/**
	 * The hash map for all module's roles rights
	 * The first map sorts them by module;
	 * The second map sorts them by roles.
	 */
	private Map<String, Map<String, Rights>> moduleRights = new HashMap<String, Map<String,Rights>>();
	
	/**
	 * Constructor
	 * @param moduleSecurityFolderRelativePath
	 */
	public ModuleSecurityProvider(String moduleSecurityFolderRelativePath)
	{
		// Get the absolute path
		String moduleSecurityFolderAbsolutePath = Thread.currentThread().getContextClassLoader()
				.getResource(moduleSecurityFolderRelativePath).getPath();
		loadModuleSecurity(moduleSecurityFolderAbsolutePath);
	}

	/**
	 * Returns whether a certain role can or cannot do a certain
	 * operation on a certain module
	 * @param moduleName The module name to test
	 * @param roleName The role name to test
	 * @param operation The CRUD operation
	 * @return whether a certain role can or cannot do a certain operation on a certain module
	 */
	@Override
	public boolean hasRights(String moduleName, String roleName, CrudOperation operation) 
	{
		boolean hasRights = false;
		Map<String, Rights> roleRights = moduleRights.get(moduleName);
		
		if (roleRights != null) {
			Rights rights = roleRights.get(roleName);
			if (rights != null) {
				hasRights = rights.hasRight(operation);
			}
		}
		
		return hasRights;
	}

	/**
	 * Returns the rights of a certain role in a certain module 
	 * @param moduleName The module name
	 * @param roleName The role name
	 * @return The rights
	 */
	@Override
	public Rights getRights(String moduleName, String roleName) 
	{
		Rights rights = null;
		Map<String, Rights> roleRights = moduleRights.get(moduleName);
		
		if (roleRights != null) {
			rights = roleRights.get(roleName);
		}
		
		return rights;
	}
	
	/**
	 * 
	 * @param moduleSecurityFolderAbsolutePath
	 */
	private void loadModuleSecurity(String moduleSecurityFolderAbsolutePath)
	{
		final String appMessagesRegex = ".*\\.?application_security\\.xml";
		Collection<Element> rootElements = 
				XmlUtils.loadXmlFiles(new RegexFilenameFilter(appMessagesRegex), moduleSecurityFolderAbsolutePath);
		
		for (Element root : rootElements) {
			Collection<Element> moduleElements = root.getChildren("application-module");
			for (Element module : moduleElements) {
				String moduleName = module.getAttributeValue("name");
				
				Map<String, Rights> rightsMap;
				if (moduleRights.containsKey(moduleName)) {
					// Module already exists, append security rules to it
					rightsMap = moduleRights.get(moduleName);
				} else {
					// Locale doesn't exists; create a new map
					rightsMap = new HashMap<String, Rights>();
					moduleRights.put(moduleName, rightsMap);
				}
				
				Collection<Element> roleElements = module.getChildren("application-role");
				for (Element role : roleElements) {
					String roleName = role.getAttributeValue("name");

					if (!rightsMap.containsKey(roleName)) {
						Collection<Element> roleRights = role.getChildren("role-right");
						Collection<CrudOperation> rightsOp = new ArrayList<CrudOperation>();
						for (Element right : roleRights) {
							String opName = right.getAttributeValue("operation");
							rightsOp.add(CrudOperation.valueOf(opName));
						}
						
						Rights rights = new Rights();
						rights.setRights(rightsOp);
						rightsMap.put(roleName, rights);
					}
				}
			}
		}
	}
}
