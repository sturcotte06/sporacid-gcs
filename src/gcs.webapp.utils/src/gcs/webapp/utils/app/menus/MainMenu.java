package gcs.webapp.utils.app.menus;

import gcs.webapp.utils.app.messages.ILocalizable;
import gcs.webapp.utils.app.messages.IMessageLocalizer;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class MainMenu implements ILocalizable
{
	private Collection<MainMenuItem> items = new ArrayList<MainMenuItem>();

	public Collection<MainMenuItem> getItems() 
	{
		return items;
	}

	public void setItems(Collection<MainMenuItem> items) 
	{
		this.items = items;
	}

	@Override
	public void localize(IMessageLocalizer localizer) 
	{
		localize(localizer, localizer.getDefaultLocale());
	}

	@Override
	public void localize(IMessageLocalizer localizer, String locale) 
	{
		for (MainMenuItem item : items) {
			item.localize(localizer, locale);
		}
	}
}
