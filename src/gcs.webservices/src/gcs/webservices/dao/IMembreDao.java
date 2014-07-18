package gcs.webservices.dao;

import java.util.Collection;

import gcs.webapp.utils.exceptions.EntityNotFoundException;
import gcs.webapp.utils.exceptions.InternalException;
import gcs.webservices.models.Club;
import gcs.webservices.models.Concentration;
import gcs.webservices.models.Membre;

/**
 * Interface for all member data access objects.
 * 
 * @author Simon Turcotte-Langevin
 */
public interface IMembreDao
{
    /**
     * Gets all members from the system.
     * 
     * @return A collection of members.
     * @throws InternalException
     */
    public Collection<Membre> getMembres() throws InternalException;

    /**
     * Gets all members by club name from the system.
     * 
     * @param clubName Name of the club.
     * @return A collection of members.
     * @throws InternalException
     */
    public Collection<Membre> getMembresByClubName(String clubName) throws InternalException;

    /**
     * Gets a member from the system.
     * 
     * @param username The unique username of the member.
     * @return The member.
     * @throws EntityNotFoundException
     */
    public Membre getMembre(String username) throws EntityNotFoundException;

    /**
     * Gets a member from the system.
     * 
     * @param membreId The id of the member.
     * @return The member.
     * @throws EntityNotFoundException
     */
    public Membre getMembre(int membreId) throws EntityNotFoundException;

    /**
     * Adds a member in the system.
     * 
     * @param membre The member to add.
     * @return The id of the member.
     * @throws InternalException
     */
    public Integer addMembre(Membre membre) throws InternalException;

    /**
     * Edit a member in the system.
     * 
     * @param membre The member to add.
     * @throws InternalException
     */
    public void editMembre(Membre membre) throws InternalException;

    /**
     * Get a concentration by name from the system.
     * 
     * @param acronyme Acronym of the concentration.
     * @return The concentration.
     * @throws EntityNotFoundException
     */
    public Concentration getConcentrationByAcronyme(String acronyme) throws EntityNotFoundException;

    /**
     * Get all concentrations from the system.
     * 
     * @return A collection of concentrations.
     * @throws InternalException
     */
    public Collection<Concentration> getConcentrations() throws InternalException;
    
    /**
     * Get all clubs of the membre.
     * 
     * @param membre The membre.
     * @return A collection of clubs.
     * @throws InternalException
     */
    public Collection<Club> getClubsOfMembre(Membre membre) throws InternalException;
}
