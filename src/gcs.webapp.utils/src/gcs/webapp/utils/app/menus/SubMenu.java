package gcs.webapp.utils.app.menus;

import gcs.webapp.utils.app.messages.ILocalizable;
import gcs.webapp.utils.app.messages.IMessageLocalizer;

import java.util.ArrayList;
import java.util.Collection;

import lombok.Getter;
import lombok.Setter;

public class SubMenu implements ILocalizable
{
   @Getter @Setter
	private Collection<SubMenuItem> items = new ArrayList<SubMenuItem>();

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
