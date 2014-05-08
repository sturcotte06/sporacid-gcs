package gcs.website.views.helpers.models;

public class MenuItem
{
    private String label;
    private String imageUrl;
    private String href;
    private Menu subMenu;

    public MenuItem(String label, String imageUrl, String href)
    {
        this.label = label;
        this.imageUrl = imageUrl;
        this.href = href;
    }

    /**
     * @return the label
     */
    public String getLabel()
    {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label)
    {
        this.label = label;
    }

    /**
     * @return the imageUrl
     */
    public String getImageUrl()
    {
        return imageUrl;
    }

    /**
     * @param imageUrl the imageUrl to set
     */
    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    /**
     * @return the href
     */
    public String getHref()
    {
        return href;
    }

    /**
     * @param href the href to set
     */
    public void setHref(String href)
    {
        this.href = href;
    }

    /**
     * @return the subMenu
     */
    public Menu getSubMenu()
    {
        return subMenu;
    }

    /**
     * @param subMenu the subMenu to set
     */
    public void setSubMenu(Menu subMenu)
    {
        this.subMenu = subMenu;
    }
}
