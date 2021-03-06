package gcs.website.views.helpers.models;

import java.util.ArrayList;
import java.util.Collection;

public class Menu
{
    private final Collection<MenuItem> items = new ArrayList<MenuItem>();

    public void addItem(MenuItem item)
    {
        items.add(item);
    }

    /**
     * @return the items
     */
    public Collection<MenuItem> getItems()
    {
        return items;
    }
}