package gcs.webservices.dao;

import gcs.webservices.models.Item;

import java.util.Collection;

public interface IItemDao {
	public Collection<Item> getAllItem();
	public Item getItemById(int idItem);
}
