package gcs.webservices.dao;

import gcs.webapp.utils.hibernate.HibernateUtils;
import gcs.webservices.models.Commandite;
import gcs.webservices.models.Fournisseur;

import java.util.Collection;

import org.hibernate.SessionFactory;

public class FournisseurDao implements IFournisseurDao
{
	private SessionFactory sessionFactory;
	
	public FournisseurDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<Fournisseur> getAllFournisseur() {
		 return HibernateUtils.getEntities(Fournisseur.class, sessionFactory);
	}

	@Override
	public Fournisseur getFournisseurById(int idFournisseur) {
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
