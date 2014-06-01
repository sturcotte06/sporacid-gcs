package gcs.webservices.dao;

import gcs.webapp.utils.hibernate.HibernateUtils;
import gcs.webservices.models.Commandite;
import gcs.webservices.models.Item;

import java.util.Collection;

import org.hibernate.SessionFactory;

public class ItemDao implements IItemDao 
{

	private SessionFactory sessionFactory;
	
	public ItemDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<Item> getAllItem() {
		return HibernateUtils.getEntities(Item.class, sessionFactory);
	}

	@Override
	public Item getItemById(int idItem) {
		// TODO Auto-generated method stub
		return null;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
