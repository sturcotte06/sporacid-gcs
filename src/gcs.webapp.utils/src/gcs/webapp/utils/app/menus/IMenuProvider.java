package gcs.webapp.utils.app.menus;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public interface IMenuProvider 
{
	/**
	 * Provide a menu for a certain role in a certain locale
	 * @param locale	Locale of the menu
	 * @param forRole	Role of the user that will see this menu
	 * @return A main menu item
	 */
	public MainMenu provideMenu(String locale, String forRole);
}
