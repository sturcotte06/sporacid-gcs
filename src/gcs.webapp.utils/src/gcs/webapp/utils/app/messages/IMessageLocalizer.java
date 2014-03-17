package gcs.webapp.utils.app.messages;

/**
 * Interface for all message localizers
 * @author Simon Turcotte-Langevin
 */
public interface IMessageLocalizer 
{
	/**
	 * Localizes a message associated with a 
	 * message key into the default locale
	 * @param messageKey The message key to find 
	 *                   the corresponding string
	 * @return The localized message if found; 
	 * 		   the message key if not
	 */
	public String localizeString(String messageKey);
	/**
	 * Localizes a message associated with a 
	 * message key into a given locale
	 * @param locale Locale in which to localize
	 * @param messageKey The message key to find 
	 *                   the corresponding string
	 * @return The localized message if found; 
	 *         the message key if not
	 */
	public String localizeString(String locale, String messageKey);
	/**
	 * Getter for the default locale
	 * @return The default locale
	 */
	public String getDefaultLocale();
	/**
	 * Returns all the available locale 
	 * currently in cache
	 * @return All available locale
	 */
	public String[] getAvailableLocales();
}
