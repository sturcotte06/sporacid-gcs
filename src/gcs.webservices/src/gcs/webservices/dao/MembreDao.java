package gcs.webservices.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import gcs.webapp.utils.exceptions.EntityNotFoundException;
import gcs.webapp.utils.exceptions.InternalException;
import gcs.webapp.utils.hibernate.HibernateAlias;
import gcs.webapp.utils.hibernate.HibernateUtils;
import gcs.webservices.models.Club;
import gcs.webservices.models.Concentration;
import gcs.webservices.models.Membre;
import gcs.webservices.models.MembreClub;

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
     * Gets all members by club name from the system.
     * 
     * @param clubName Name of the club.
     * @return A collection of members.
     * @throws InternalException
     */
    @Override
    public Collection<Membre> getMembresByClubName(String clubName) throws InternalException
    {
        Club club = null;

        Collection<HibernateAlias> aliases = new ArrayList<>();
        Collection<Criterion> criterions = new ArrayList<>();
        
        // Create a criterion on username
        criterions.add(Restrictions.eq("nom", clubName).ignoreCase());

        try {
            // Get the member
            club = HibernateUtils.getFirstEntity(Club.class, aliases, criterions, sessionFactory);

            if (club == null) {
                throw new EntityNotFoundException("club", clubName);
            }
        } catch (HibernateException ex) {
            // Couldn't get the membre
            throw new InternalException("membersdao_getmember_exception", ex);
        }

        return club.getMembres()
                .stream()
                .map(membreClub -> membreClub.getMembre())
                .collect(Collectors.toCollection(ArrayList::new));
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
        criterions.add(Restrictions.eq("codeUniversel", username).ignoreCase());

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

    /**
     * Get a concentration by name from the system.
     * 
     * @param acronyme Acronym of the concentration.
     * @return The concentration.
     * @throws EntityNotFoundException
     */
    @Override
    public Concentration getConcentrationByAcronyme(String acronyme) throws EntityNotFoundException
    {
        Concentration concentration = null;

        Collection<HibernateAlias> aliases = new ArrayList<>();
        Collection<Criterion> criterions = new ArrayList<>();

        // Create a criterion on the acronym
        criterions.add(Restrictions.eq("acronyme", acronyme).ignoreCase());

        try {
            // Get the member
            concentration = HibernateUtils.getFirstEntity(Concentration.class, aliases, criterions, sessionFactory);

            if (concentration == null) {
                throw new EntityNotFoundException("concentration", acronyme);
            }
        } catch (HibernateException ex) {
            // Couldn't get the membre
            throw new InternalException("membersdao_getconcentration_byname_exception", ex);
        }

        return concentration;
    }

    /**
     * Get all concentrations from the system.
     * 
     * @return A collection of concentrations.
     * @throws InternalException
     */
    @Override
    public Collection<Concentration> getConcentrations() throws InternalException
    {
        Collection<Concentration> concentrations = null;

        try {
            // Get all members
            concentrations = HibernateUtils.getEntities(Concentration.class, sessionFactory);
        } catch (HibernateException ex) {
            // Couldn't get the members
            throw new InternalException("membersdao_getconcentrations_exception", ex);
        }

        return concentrations;
    }
    
    /**
     * Get all clubs of the membre.
     * 
     * @param membre The membre.
     * @return A collection of clubs.
     * @throws InternalException
     */
    public Collection<Club> getClubsOfMembre(Membre membre)
    {
        Collection<Club> clubs = null;
        Collection<MembreClub> membreClubs = membre.getClubs();
        
        // Check if the orm config was eager.
        if (membreClubs != null) {
            // Eager: objects are pre-loaded
            clubs = new ArrayList<>();
            for (MembreClub membresClub : membreClubs) {
                clubs.add(membresClub.getClub());
            }
            
            return clubs;
        }
        
        // Lazy: load from database.
        Collection<HibernateAlias> aliases = new ArrayList<>();
        Collection<Criterion> criterions = new ArrayList<>();
        
        // Create an alias for 
        aliases.add(new HibernateAlias("membres.membre", "membre"));
        
        // Create a criterion on username
        criterions.add(Restrictions.eq("membre.id", membre.getId()).ignoreCase());
        
        try {
            // Get the clubs of the membre
            clubs = HibernateUtils.getEntities(Club.class, aliases, criterions, sessionFactory);
        } catch (HibernateException ex) {
            // Couldn't edit the membre
            throw new InternalException("membersdao_getclubsofmembre_exception", ex);
        }
        
        return clubs;
    }
    

    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    /**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }
}
