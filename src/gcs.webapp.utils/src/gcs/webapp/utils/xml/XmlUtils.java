package gcs.webapp.utils.xml;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * @author Simon Turcotte-Langevin
 */
public final class XmlUtils
{
    /** An xml document reader. */
    private static final SAXBuilder documentBuilder;

    /**
     * Static constructor.
     */
    static {
        documentBuilder = new SAXBuilder();
        documentBuilder.setFeature("http://xml.org/sax/features/validation", false);
        documentBuilder.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
        documentBuilder.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
    }

    /**
     * Returns a collection of root elements for all found xml files that match
     * a file name filter within a folder
     * 
     * @param fileNameFilter A file name filter
     * @param messagesFolderAbsolutePath The absolute path the the folder
     * @return A list of xml root element
     * @throws IOException
     * @throws JDOMException
     */
    public static Collection<Element> loadXmlFiles(FilenameFilter fileNameFilter, String messagesFolderAbsolutePath)
            throws JDOMException, IOException
    {
        Collection<Element> rootElements = new ArrayList<Element>();
        File folder = new File(messagesFolderAbsolutePath);

        if (folder.exists() && folder.isDirectory()) {
            // Find all xml file which are application messages resource files
            File[] matchingFiles = folder.listFiles(fileNameFilter);
            for (File matchingFile : matchingFiles) {
                Document xmlDocument = null;

                // Try to load the xml document
                xmlDocument = documentBuilder.build(matchingFile);

                if (xmlDocument != null) {
                    rootElements.add(xmlDocument.getRootElement());
                }
            }
        }

        return rootElements;
    }

    /**
     * Returns the root element for the xml file, if that file was a valid xml
     * file
     * 
     * @param xmlResourcePath The path to the xml resource
     * @return The root element for the xml resource
     * @throws JDOMException
     * @throws IOException
     */
    public static Element loadXmlFromResource(String xmlResourcePath) throws JDOMException, IOException,
            URISyntaxException
    {
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoader.getResource(xmlResourcePath).openStream();

        if (stream == null) {
            throw new IllegalArgumentException("Xml resource path was invalid.");
        }

        Element rootElement = null;

        // Try to load the xml document
        Document xmlDocument = documentBuilder.build(stream);

        if (xmlDocument != null) {
            rootElement = xmlDocument.getRootElement();
        }

        return rootElement;
    }
}
