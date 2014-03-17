package gcs.webapp.utils.tests.app.messages;

import static org.junit.Assert.*;
import gcs.webapp.utils.app.messages.ApplicationMessageLocalizer;

import org.junit.Test;

public class ApplicationMessageLocalizerTests 
{
	private final ApplicationMessageLocalizer localizer = new ApplicationMessageLocalizer("./resources");
	
	@Test
	public void test() 
	{
		final String key1 = "message1";
		final String localizedEnKey1 = "This is message 1.";
		final String localizedFrKey1 = "Ceci est le message 1.";
		final String unexistingkey = "message5";
		
		localizer.setDefaultLocale("en-ca");
		assertEquals("Error with the localization with default language (1).", localizedEnKey1, localizer.localizeString(key1));
		
		localizer.setDefaultLocale("fr-ca");
		assertEquals("Error with the localization with default language (2).", localizedFrKey1, localizer.localizeString(key1));
		
		assertEquals("Error with the localization with specified language (1).", localizedEnKey1, localizer.localizeString("en-ca", key1));
		assertEquals("Error with the localization with specified language (2).", localizedFrKey1, localizer.localizeString("fr-ca", key1));
		assertEquals("Error with the localization of an undefined key.", unexistingkey, localizer.localizeString(unexistingkey));
		assertEquals("Error with the localization of an undefined locale.", key1, localizer.localizeString("not-a-language", key1));
	}
}
