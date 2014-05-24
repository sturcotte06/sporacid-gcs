package gcs.webapp.utils.app.menus;

import gcs.webapp.utils.app.messages.IMessageLocalizer;
import gcs.webapp.utils.io.RegexFilenameFilter;
import gcs.webapp.utils.reflect.ReflectionUtils;
import gcs.webapp.utils.xml.XmlUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jdom2.Element;
import org.jdom2.JDOMException;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class MenuProvider implements IMenuProvider
{
	/** */
	private Map<String, Map<String, MainMenu>> availableMenus = new HashMap<String, Map<String, MainMenu>>();

	    /**
     * Constructor
     * 
     * @throws IOException
     * @throws JDOMException
     */
	public MenuProvider(String menusFolderRelativePath, IMessageLocalizer localizer) throws JDOMException, IOException
	{
		// Get the absolute path
		String messagesFolderAbsolutePath = Thread.currentThread().getContextClassLoader()
				.getResource(menusFolderRelativePath).getPath();
		// Load all the menus within memory
		Map<String, MainMenu> unlocalizedMenus = loadApplicationMenus(messagesFolderAbsolutePath);
		
		// Localize all the menus in all possible locales
		for (String locale : localizer.getAvailableLocales()) {
			Map<String, MainMenu> menuToLocalize = new HashMap<String, MainMenu>();
			for (Entry<String, MainMenu> entry : unlocalizedMenus.entrySet()) {
				MainMenu menuCopy = ReflectionUtils.deepCopy(entry.getValue());
				menuCopy.localize(localizer, locale);
				menuToLocalize.put(entry.getKey(), menuCopy);
			}
			
			availableMenus.put(locale, menuToLocalize);
		}
	}

	/**
	 * Provide a menu for a certain role in a certain locale
	 * @param locale	Locale of the menu
	 * @param forRole	Role of the user that will see this menu
	 * @return A main menu item
	 */
	public MainMenu provideMenu(String locale, String forRole)
	{
		// Default behaviour: return the key if no associed message were found
		MainMenu menu = null;
		Map<String, MainMenu> menusMap = availableMenus.get(locale);
		
		if (menusMap != null && menusMap.containsKey(forRole)) {
			menu = menusMap.get(forRole);
		}
		
		return menu;
	}
	
	    /**
     * @param messagesFolderAbsolutePath
     * @return
     * @throws IOException
     * @throws JDOMException
     */
	private Map<String, MainMenu> loadApplicationMenus(String messagesFolderAbsolutePath) throws JDOMException, IOException
	{
		Map<String, MainMenu> unlocalizedMenus = new HashMap<String, MainMenu>();
		final String appMessagesRegex = ".*\\.?menu\\..*\\.xml";
		Collection<Element> rootElements = 
				XmlUtils.loadXmlFiles(new RegexFilenameFilter(appMessagesRegex), messagesFolderAbsolutePath);
		
		for (Element root : rootElements) {
			String forRoleAttr = root.getAttributeValue("for");
			
			if (!availableMenus.containsKey(forRoleAttr)) {
				unlocalizedMenus.put(forRoleAttr, buildMenu(root));
			}
		}
		
		return unlocalizedMenus;
	}
	
	/**
	 * 
	 * @param menuElement
	 * @return
	 */
	private MainMenu buildMenu(Element menuElement)
	{
		MainMenu menu = new MainMenu();
		
		// Find all menu elements, parse them, and add them to the hash map
		List<Element> menuEntryElements = menuElement.getChildren("menu-entry");
		for (Element menuEntryElement : menuEntryElements) {
			MainMenuItem menuItem = new MainMenuItem();
			menuItem.setName(menuEntryElement.getAttributeValue("name"));
			menuItem.setPath(menuEntryElement.getAttributeValue("path"));
			
			Element submenuElement = menuEntryElement.getChild("sub-menu");
			if (submenuElement != null) {
				SubMenu submenu = new SubMenu();
				List<Element> submenuEntryElements = submenuElement.getChildren("sub-menu-entry");
				for (Element submenuEntryElement : submenuEntryElements) {
					SubMenuItem submenuItem = new SubMenuItem();
					submenuItem.setName(submenuEntryElement.getAttributeValue("name"));
					submenuItem.setPath(submenuEntryElement.getAttributeValue("path"));
					submenu.getItems().add(submenuItem);
				}
				
				menuItem.setSubmenu(submenu);
			}
			
			menu.getItems().add(menuItem);
		}
		
		return menu;
	}
}
