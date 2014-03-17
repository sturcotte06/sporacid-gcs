package gcs.webapp.utils.app.menus;

import gcs.webapp.utils.app.messages.ILocalizable;
import gcs.webapp.utils.app.messages.IMessageLocalizer;

import java.util.ArrayList;
import java.util.Collection;

public class SubMenu implements ILocalizable
{
	private Collection<SubMenuItem> items = new ArrayList<SubMenuItem>();

	public Collection<SubMenuItem> getItems() 
	{
		return items;
	}

	public void setItems(Collection<SubMenuItem> items) 
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
		for (SubMenuItem item : items) {
			item.localize(localizer, locale);
		}
	}
}
