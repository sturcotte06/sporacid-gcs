package gcs.webapp.utils.xml;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public final class XmlUtils 
{
   private static final Logger logger = Logger.getLogger(XmlUtils.class);
   
	/**
	 * Returns a collection of root elements for all found xml files
	 * that match a file name filter within a folder
	 * @param fileNameFilter A file name filter
	 * @param messagesFolderAbsolutePath The absolute path the the folder
	 * @return A list of xml root element
	 */
	public static Collection<Element> loadXmlFiles(FilenameFilter fileNameFilter, String messagesFolderAbsolutePath)
	{
		Collection<Element> rootElements = new ArrayList<Element>();
		File folder = new File(messagesFolderAbsolutePath);

		if (folder.exists() && folder.isDirectory()) {
			// The supplied path to the application messages
			// is valid
			final SAXBuilder documentBuilder = new SAXBuilder();
			
			// Find all xml file which are application messages resource files
			File[] matchingFiles = folder.listFiles(fileNameFilter);
			for (File matchingFile : matchingFiles) {
				Document xmlDocument = null;
				try {
					// Try to load the xml document
					xmlDocument = documentBuilder.build(matchingFile);
					
					if (xmlDocument != null) {
						rootElements.add(xmlDocument.getRootElement());
					}
				} catch (JDOMException ex) {
				   logger.error("There was an error while parsing the xml file.", ex);
		      } catch (IOException ex) {
		         logger.error("There was an error while loading the xml file.", ex);
	         }
			}
		}
		
		return rootElements;
	}
}
