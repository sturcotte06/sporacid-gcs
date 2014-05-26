package gcs.webapp.utils.app.messages;

import gcs.webapp.utils.aspects.logging.Loggable;
import gcs.webapp.utils.xml.XmlUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jdom2.Element;
import org.jdom2.JDOMException;

/**
 * A string localizer to resolve application messages in different locale. Based
 * on Réal Forté's implementation in C#
 * 
 * @author Simon Turcotte-Langevin
 */
public class MessageLocalizer implements IMessageLocalizer
{
    /** Log4j logger. */
    private final static Logger logger = Logger.getLogger(MessageLocalizer.class);

    /**
     * Initial capacity for hash maps We want O(1) most of the times, so set
     * this to a relatively high number because we'll have lots of messages
     */
    private final int cHashMapInitialCapacity = 256;

    /**
     * The hash map for all application's messages. The first map sorts them by
     * locale; The second map sorts them by message keys.
     */
    private final Map<String, Map<String, String>> applicationMessages = new HashMap<String, Map<String, String>>();

    /**
     * The default locale if we try to get a message without locale.
     */
    private String defaultLocale;

    /**
     * Constructor
     * 
     * @param messagesFolderRelativePath
     * @throws IOException
     * @throws JDOMException
     * @throws URISyntaxException
     */
    public MessageLocalizer(String[] messageResourcePaths) throws JDOMException, IOException, URISyntaxException
    {
        // Load all the messages within memory
        loadApplicationMessages(messageResourcePaths);
    }

    /**
     * Localizes a message associated with a message key into the default locale
     * 
     * @param messageKey The message key to find the corresponding string
     * @return The localized message if found; the message key if not
     */
    @Override
    public String localizeString(String messageKey)
    {
        return localizeString(this.defaultLocale, messageKey);
    }

    /**
     * Localizes a message associated with a message key into a given locale.
     * 
     * @param locale Locale in which to localize
     * @param messageKey The message key to find the corresponding string
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
     * Localizes and format a message associated with a message key into a given
     * locale.
     * 
     * @param locale Locale in which to localize
     * @param messageKey The message key to find the corresponding string
     * @param formats The format objects
     * @return The formatted localized message if found; the message key if not
     */
    public String localizeFormatString(String locale, String messageKey, Object... formats)
    {
        String unformatted = localizeString(locale, messageKey);
        return String.format(unformatted, formats);
    }

    /**
     * Getter for the default locale
     * 
     * @return The default locale
     */
    @Override
    public String getDefaultLocale()
    {
        return defaultLocale;
    }

    /**
     * Returns all the available locale currently in cache
     * 
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
     * 
     * @param messagesFolderAbsolutePath The absolute path to the resource
     *            folder
     * @throws IOException
     * @throws JDOMException
     */

    /**
     * Loads application messages from application messages xml files
     * 
     * @param messageResourcePaths Paths to the message definition xml resources
     * @throws URISyntaxException
     * @throws IOException
     * @throws JDOMException
     */
    @Loggable
    private void loadApplicationMessages(String[] messageResourcePaths) throws JDOMException, IOException,
            URISyntaxException
    {
        for (String messageResourcePath : messageResourcePaths) {
            Element root;

            // Get the root element for the xml file
            root = XmlUtils.loadXmlFromResource(messageResourcePath);

            String language = root.getAttributeValue("language");
            // Find the correct map where to add messages
            Map<String, String> messagesMap;
            if (applicationMessages.containsKey(language)) {
                // Locale already exists, append new message to it
                messagesMap = applicationMessages.get(language);
            } else {
                // Locale doesn't exists; create a new map
                messagesMap = new HashMap<>(cHashMapInitialCapacity);
                applicationMessages.put(language, messagesMap);
            }

            // Find all messages, parse them, and add them to the hash map
            Collection<Element> messageElements = root.getChildren("message");
            for (Element messageElement : messageElements) {
                String key = messageElement.getAttributeValue("key");
                if (!messagesMap.containsKey(key)) {
                    String message = messageElement.getAttributeValue("message");
                    messagesMap.put(key, message);
                }
            }
        }
    }

    // private void loadApplicationMessages(String messagesFolderAbsolutePath)
    // throws JDOMException, IOException
    // {
    // final String appMessagesRegex = ".*\\.?messages\\..{2}\\.xml";
    // Collection<Element> rootElements =
    // XmlUtils.loadXmlFiles(new RegexFilenameFilter(appMessagesRegex),
    // messagesFolderAbsolutePath);
    //
    // for (Element root : rootElements) {
    // String locale = root.getAttributeValue("language");
    // // Find the correct map where to add messages
    // Map<String, String> messagesMap;
    // if (applicationMessages.containsKey(locale)) {
    // // Locale already exists, append new message to it
    // messagesMap = applicationMessages.get(locale);
    // } else {
    // // Locale doesn't exists; create a new map
    // messagesMap = new HashMap<String, String>(cHashMapInitialCapacity);
    // applicationMessages.put(locale, messagesMap);
    // }
    //
    // // Find all messages, parse them, and add them to the hash map
    // List<Element> messageElements = root.getChildren("message");
    // for (Element messageElement : messageElements) {
    // String key = messageElement.getAttributeValue("key");
    // if (!messagesMap.containsKey(key)) {
    // String message = messageElement.getAttributeValue("message");
    // messagesMap.put(key, message);
    // }
    // }
    // }
    // }

    /**
     * Setter for the default locale
     * 
     * @param defaultLocale The new default locale
     */
    public void setDefaultLocale(String defaultLocale)
    {
        this.defaultLocale = defaultLocale;
    }
}
