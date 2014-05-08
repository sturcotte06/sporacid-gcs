package gcs.webapp.utils.app.menus;

import gcs.webapp.utils.app.messages.ILocalizable;
import gcs.webapp.utils.app.messages.IMessageLocalizer;

public class MainMenuItem implements ILocalizable
{
    private String name;
    private String path;
    private SubMenu submenu;

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

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the path
     */
    public String getPath()
    {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path)
    {
        this.path = path;
    }

    /**
     * @return the submenu
     */
    public SubMenu getSubmenu()
    {
        return submenu;
    }

    /**
     * @param submenu the submenu to set
     */
    public void setSubmenu(SubMenu submenu)
    {
        this.submenu = submenu;
    }
}
