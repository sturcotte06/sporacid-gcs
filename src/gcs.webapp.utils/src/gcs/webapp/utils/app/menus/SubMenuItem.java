package gcs.webapp.utils.app.menus;

import lombok.Getter;
import lombok.Setter;
import gcs.webapp.utils.app.messages.ILocalizable;
import gcs.webapp.utils.app.messages.IMessageLocalizer;

public class SubMenuItem implements ILocalizable
{
   @Getter @Setter
	private String name;
   
   @Getter @Setter
	private String path;
	
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
