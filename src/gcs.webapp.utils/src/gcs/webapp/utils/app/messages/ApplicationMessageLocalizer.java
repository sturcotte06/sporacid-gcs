package gcs.webapp.utils.app.messages;

import gcs.webapp.utils.io.RegexFilenameFilter;
import gcs.webapp.utils.xml.XmlUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Element;

/**
 * A string localizer to resolve application messages in different locale.
 * Based on Réal Forté's implementation in C#
 * @author Simon Turcotte-Langevin
 */
public class ApplicationMessageLocalizer implements IMessageLocalizer
{
	/**
	 * Initial capacity for hash maps
	 * We want O(1) most of the times, so set this to a relatively
	 * high number because we'll have lots of messages
	 */
	private final int cHashMapInitialCapacity = 256;
	
	/**
	 * The hash map for all application's messages
	 * The first map sorts them by locale;
	 * The second map sorts them by message keys.
	 */
	private final Map<String, Map<String, String>> applicationMessages = new HashMap<String, Map<String, String>>();
	
	/**
	 * The default locale if we try to get a message without locale.
	 */
	private String defaultLocale;

	/**
	 * Constructor
	 * @param messagesFolderRelativePath
	 */
	public ApplicationMessageLocalizer(String messagesFolderRelativePath)
	{
		// Get the absolute path
		String messagesFolderAbsolutePath = Thread.currentThread().getContextClassLoader()
				.getResource(messagesFolderRelativePath).getPath();
		// Load all the messages within memory
		loadApplicationMessages(messagesFolderAbsolutePath);
	}

	/**
	 * Localizes a message associated with a message key into the default locale
	 * @param messageKey	The message key to find the corresponding string
	 * @return The localized message if found; the message key if not
	 */
	@Override
	public String localizeString(String messageKey)
	{
		return localizeString(this.defaultLocale, messageKey);
	}
	
	/**
	 * Localizes a message associated with a message key into a given locale
	 * @param locale		Locale in which to localize
	 * @param messageKey	The message key to find the corresponding string
	 * @return The localized message if found; the message key if not
	 */
	@Override
	public String localizeString(String locale, String messageKey)
	{
		// Default behaviour: return the key if no associed message were found
		String localizedMessage = messageKey;
		Map<String, String> messagesMap = applicationMessages.get(locale);
		
		if (messagesMap != null && messagesMap.containsKey(messageKey)) {
			localizedMessage = messagesMap.get(messageKey);
		}
		
		return localizedMessage;
	}
	
	/**
	 * Getter for the default locale
	 * @return The default locale
	 */
	@Override
	public String getDefaultLocale() 
	{
		return defaultLocale;
	}

	/**
	 * Returns all the available locale currently in cache
	 * @return All available locale
	 */
	@Override
	public String[] getAvailableLocales() 
	{
		String[] availableLocales = new String[applicationMessages.size()];
		int i = 0;
		for (String locale : applicationMessages.keySet()) {
			availableLocales[i++] = locale;
		}
		return availableLocales;
	}
	
	/**
	 * Initializes the Hash map of all application messages from a resource
	 * folder within the application. The method will load the found Xml files
	 * within that folder and parse its messages by locale and message key.
	 * @param messagesFolderAbsolutePath The absolute path to the resource folder
	 */
	private void loadApplicationMessages(String messagesFolderAbsolutePath)
	{
		final String appMessagesRegex = ".*\\.?application_messages\\..{2}-.{2}\\.xml";
		Collection<Element> rootElements = 
				XmlUtils.loadXmlFiles(new RegexFilenameFilter(appMessagesRegex), messagesFolderAbsolutePath);
		
		for (Element root : rootElements) {
			String locale = root.getAttributeValue("locale");
			// Find the correct map where to add messages
			Map<String, String> messagesMap;
			if (applicationMessages.containsKey(locale)) {
				// Locale already exists, append new message to it
				messagesMap = applicationMessages.get(locale);
			} else {
				// Locale doesn't exists; create a new map
				messagesMap = new HashMap<String, String>(cHashMapInitialCapacity);
				applicationMessages.put(locale, messagesMap);
			}
			
			// Find all messages, parse them, and add them to the hash map
			List<Element> messageElements = root.getChildren("message");
			for (Element messageElement : messageElements) {
				String key = messageElement.getAttributeValue("key");
				if (!messagesMap.containsKey(key)) {
					String message = messageElement.getAttributeValue("message");
					messagesMap.put(key, message);
				}
			}
		}
	}

	/**
	 * Setter for the default locale
	 * @param defaultLocale The new default locale
	 */
	public void setDefaultLocale(String defaultLocale) 
	{
		this.defaultLocale = defaultLocale;
	}
}
