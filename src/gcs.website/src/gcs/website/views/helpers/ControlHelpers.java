package gcs.website.views.helpers;

import gcs.webapp.utils.reflect.ReflectionUtils;

import java.util.Map;

public final class ControlHelpers
{
	/**
	 * 
	 * @param toEncode
	 * @return
	 */
	public static String htmlEncode(String toEncode)
	{
		StringBuffer out = new StringBuffer();
	    for(int i = 0; i < toEncode.length(); i++)
	    {
	        char c = toEncode.charAt(i);
	        if(c > 127 || c=='"' || c=='<' || c=='>') {
	           out.append("&#"+(int)c+";");
	        }
	        else {
	            out.append(c);
	        }
	    }
	    return out.toString();
	}

	/**
	 * Serialize an object into hidden field inputs.
	 * @param object		An object to serialize
	 * @param classObj		The class of the object
	 * @param propertyName	Name of the property
	 * @return				An html string with hidden inputs for every properties of the object				
	 */
	public static <E> String serializeToHiddenFields(E object, Class<E> classObj, String propertyName)
	{
		StringBuffer out = new StringBuffer();
		
		try {
			// Get the object properties through reflection
			Map<String, Object> objProperties = ReflectionUtils.getObjectProperties(object, classObj);
			
			// For each object properties
			for (Map.Entry<String, Object> entry : objProperties.entrySet()) {
				// Modify the key with the property name in parameter
				String modifiedKey = propertyName + "." + entry.getKey();
				// We want the value as a string
				String strValue = entry.getValue().toString();
				out.append("<input type=\"hidden\" name=\"" + modifiedKey + "\" value=\"" + strValue + "\" />\n");
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return out.toString();
	}
}
