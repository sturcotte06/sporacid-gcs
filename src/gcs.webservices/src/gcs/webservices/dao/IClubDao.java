package gcs.webservices.dao;

import gcs.webapp.utils.exceptions.InternalException;
import gcs.webservices.models.Club;

import java.util.Collection;

public interface IClubDao 
{
	/**
	 * 
	 * @param username
	 * @return
	 */
	public Collection<Club> getClubs() throws InternalException;
	
	public Club getClub(int id) throws InternalException;
}
