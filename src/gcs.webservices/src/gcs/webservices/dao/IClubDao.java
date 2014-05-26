package gcs.webservices.dao;

import gcs.webapp.utils.exceptions.EntityNotFoundException;
import gcs.webapp.utils.exceptions.InternalException;
import gcs.webservices.models.Club;

import java.util.Collection;

/**
 * Interface for all club data access object.
 * 
 * @author Patrick Lavallée, Simon Turcotte-Langevin
 */
public interface IClubDao
{
    /**
     * Gets all clubs from the system.
     * 
     * @return A collection of club.
     * @throws EntityNotFoundException
     */
    public Collection<Club> getClubs() throws EntityNotFoundException;

    /**
     * Gets a club from the system.
     * 
     * @param clubId The id of the club.
     * @return The club.
     * @throws EntityNotFoundException
     */
    public Club getClub(int clubId) throws EntityNotFoundException;

    /**
     * Adds a club in the system.
     * 
     * @param club The club to add.
     * @return The id of the club.
     * @throws InternalException
     */
    public Integer addClub(Club club) throws InternalException;
    
    /**
     * 
     * @param membreId
     * @param clubId
     * @return
     */
    public Integer addMembreToClub(int membreId, int clubId);
    
    public Integer bindRoleToMembre(int membreId, int roleId);
}
