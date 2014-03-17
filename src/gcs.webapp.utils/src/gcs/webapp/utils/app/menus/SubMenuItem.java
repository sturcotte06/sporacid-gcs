package gcs.webapp.utils.app.menus;

import gcs.webapp.utils.app.messages.ILocalizable;
import gcs.webapp.utils.app.messages.IMessageLocalizer;

public class SubMenuItem implements ILocalizable
{
	private String name;
	private String path;
	
	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getPath() 
	{
		return path;
	}

	public void setPath(String path) 
	{
		this.path = path;
	}

	@Override
	public void localize(IMessageLocalizer localizer) 
	{
		localize(localizer, localizer.getDefaultLocale());
	}
	
	@Override
	public void localize(IMessageLocalizer localizer, String locale) 
	{
		name = localizer.localizeString(locale, name);
	}
}
