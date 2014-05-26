package gcs.webservices.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import gcs.webapp.utils.exceptions.EntityNotFoundException;
import gcs.webapp.utils.exceptions.InternalException;
import gcs.webapp.utils.hibernate.HibernateAlias;
import gcs.webapp.utils.hibernate.HibernateUtils;
import gcs.webservices.models.Membre;

/**
 * @author Simon Turcotte-Langevin
 */
public class MembreDao implements IMembreDao
{
    /** Hibernate session factory. */
    private SessionFactory sessionFactory;

    /**
     * Gets all members from the system.
     * 
     * @return A collection of members.
     * @throws InternalException
     */
    @Override
    public Collection<Membre> getMembres() throws InternalException
    {
        Collection<Membre> membres = null;

        try {
            // Get all members
            membres = HibernateUtils.getEntities(Membre.class, sessionFactory);
        } catch (HibernateException ex) {
            // Couldn't get the members
            throw new InternalException("membersdao_getmembers_exception", ex);
        }

        return membres;
    }

    /**
     * Gets a member from the system.
     * 
     * @param username The unique username of the member.
     * @return The member.
     * @throws EntityNotFoundException
     */
    @Override
    public Membre getMembre(String username) throws EntityNotFoundException
    {
        Membre membre = null;

        Collection<HibernateAlias> aliases = new ArrayList<>();
        Collection<Criterion> criterions = new ArrayList<>();

        // Create a criterion on username
        criterions.add(Restrictions.eq("codePermanent", username));

        try {
            // Get the member
            membre = HibernateUtils.getFirstEntity(Membre.class, aliases, criterions, sessionFactory);

            if (membre == null) {
                throw new EntityNotFoundException("membre", username);
            }
        } catch (HibernateException ex) {
            // Couldn't get the membre
            throw new InternalException("membersdao_getmember_exception", ex);
        }

        return membre;
    }

    /**
     * Gets a member from the system.
     * 
     * @param membreId The id of the member.
     * @return The member.
     * @throws EntityNotFoundException
     */
    @Override
    public Membre getMembre(int membreId) throws EntityNotFoundException
    {
        Membre membre = null;

        try {
            // Get the member
            membre = HibernateUtils.getEntity(membreId, Membre.class, sessionFactory);

            if (membre == null) {
                throw new EntityNotFoundException("membre", membreId);
            }
        } catch (HibernateException ex) {
            // Couldn't get the membre
            throw new InternalException("membersdao_getmember_exception", ex);
        }

        return membre;
    }

    /**
     * Adds a member in the system.
     * 
     * @param membre The member to add.
     * @throws InternalException
     */
    @Override
    public Integer addMembre(Membre membre) throws InternalException
    {
        Integer membreId = null;

        try {
            // Add the member
            membreId = HibernateUtils.addEntity(membre, sessionFactory);
        } catch (HibernateException ex) {
            // Couldn't add the membre
            throw new InternalException("membersdao_addmember_exception", ex);
        }

        return membreId;
    }

    /**
     * Edit a member in the system.
     * 
     * @param membre The member to add.
     * @throws InternalException
     */
    @Override
    public void editMembre(Membre membre) throws InternalException
    {
        try {
            // Edit the member
            HibernateUtils.editEntity(membre, sessionFactory);
        } catch (HibernateException ex) {
            // Couldn't edit the membre
            throw new InternalException("membersdao_editmember_exception", ex);
        }
    }
}
