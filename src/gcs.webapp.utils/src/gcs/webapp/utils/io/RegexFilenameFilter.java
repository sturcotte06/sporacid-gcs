package gcs.webapp.utils.io;

import java.io.File;
import java.io.FilenameFilter;

public class RegexFilenameFilter implements FilenameFilter 
{
	/**
	 * 
	 */
	private String regex;
	
	/**
	 * 
	 * @param regex
	 */
	public RegexFilenameFilter(String regex)
	{
		this.regex = regex;
	}
	
	/**
	 * @param dir
	 * @param name
	 * @return
	 */
	@Override
	public boolean accept(File dir, String name) 
	{
		return name.matches(regex);
	}
}
