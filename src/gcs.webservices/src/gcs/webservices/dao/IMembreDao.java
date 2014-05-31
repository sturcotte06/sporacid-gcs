package gcs.webservices.dao;

import java.util.Collection;

import gcs.webapp.utils.exceptions.EntityNotFoundException;
import gcs.webapp.utils.exceptions.InternalException;
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
}
