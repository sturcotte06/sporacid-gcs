package gcs.webapp.utils.format;

import java.text.Format;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Repository for formatters throughout the application.
 * @author Simon Turcotte-Langevin
 */
public final class FormatterRepository
{
	/**
	 * All supported formatter
	 */
	private static Map<SupportedFormatter, Format> formatters = new HashMap<SupportedFormatter, Format>();
	
	/**
	 * 
	 * @param formatterType
	 * @return
	 */
	public static Format getFormatter(SupportedFormatter formatterType)
	{
		
		Format formatter = null;
		if (formatters.containsKey(formatterType)) {
			formatter = formatters.get(formatterType);
		}
		
		return formatter;
	}
	
	/**
	 * 
	 * @param formatterType
	 * @return
	 */
	public static Format getFormatter(String formatterType)
	{
		Format formatter = null;
		SupportedFormatter formatterTypeEnum = SupportedFormatter.valueOf(formatterType);
		if (formatterTypeEnum != null) {
			formatter = getFormatter(formatterTypeEnum);
		}
		
		return formatter;
	}

	/**
	 * 
	 * @param formatterType
	 * @param format
	 */
	public static void registerFormatter(SupportedFormatter formatterType, Format format)
	{
		if (!formatters.containsKey(formatterType)) {
			formatters.put(formatterType, format);
		}
	}
	
	/**
	 * 
	 * @param formatters
	 */
	public static void registerFormatters(Map<SupportedFormatter, Format> formatters)
	{
		for (Entry<SupportedFormatter, Format> entry : formatters.entrySet()) {
			registerFormatter(entry.getKey(), entry.getValue());
		}
	}
}
