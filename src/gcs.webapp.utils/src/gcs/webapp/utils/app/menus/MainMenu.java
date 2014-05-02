package gcs.webapp.utils.app.menus;

import gcs.webapp.utils.app.messages.ILocalizable;
import gcs.webapp.utils.app.messages.IMessageLocalizer;

import java.util.ArrayList;
import java.util.Collection;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class MainMenu implements ILocalizable
{
   @Getter @Setter
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
}
