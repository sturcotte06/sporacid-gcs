package gcs.webapp.utils.app.menus;

import gcs.webapp.utils.app.messages.ILocalizable;
import gcs.webapp.utils.app.messages.IMessageLocalizer;

public class MainMenuItem implements ILocalizable
{
	private String name;
	private String path;
	private SubMenu submenu;
	
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

	public SubMenu getSubmenu() 
	{
		return submenu;
	}

	public void setSubmenu(SubMenu submenu) 
	{
		this.submenu = submenu;
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
		if (submenu != null) {
			submenu.localize(localizer, locale);
		}
	}
}
