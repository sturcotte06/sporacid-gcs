package gcs.webservices.dao;

import gcs.webservices.models.Commandite;

import java.util.Collection;

public interface ICommanditeDao {
	public Collection<Commandite> getAllCommandite();
	public Commandite getCommandite(int idCommandite);
	
}
