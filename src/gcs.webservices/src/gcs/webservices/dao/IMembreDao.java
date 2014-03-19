package gcs.webservices.dao;

import gcs.webservices.exceptions.InternalException;
import gcs.webservices.models.Membre;

public interface IMembreDao 
{
	/**
	 * 
	 * @param username
	 * @return
	 */
	public Membre getMembre(String username) throws InternalException;
}
