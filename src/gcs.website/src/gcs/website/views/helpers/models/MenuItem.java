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
   
   public String getLabel()
   {
      return label;
   }
   public void setLabel(String label)
   {
      this.label = label;
   }
   public String getImageUrl()
   {
      return imageUrl;
   }
   public void setImageUrl(String imageUrl)
   {
      this.imageUrl = imageUrl;
   }
   public Menu getSubMenu()
   {
      return subMenu;
   }
   public void setSubMenu(Menu subMenu)
   {
      this.subMenu = subMenu;
   }

   public String getHref()
   {
      return href;
   }

   public void setHref(String href)
   {
      this.href = href;
   }
}

