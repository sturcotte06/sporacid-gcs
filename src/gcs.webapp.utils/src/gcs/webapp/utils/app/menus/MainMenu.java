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

    /**
     * @return the items
     */
    public Collection<MainMenuItem> getItems()
    {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(Collection<MainMenuItem> items)
    {
        this.items = items;
    }
}
