package gcs.webapp.utils.hibernate;

import gcs.webapp.utils.aspects.logging.Loggable;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 * @author Simon Turcotte-Langevin, Fran�ois Gagn�
 */
@Loggable
public final class HibernateUtils
{
    /**
     * Public utility method to add an entity to the database using hibernate
     * 
     * @param entityToAdd The entity to add
     * @param sessionFactory Reference to the SessionFactory implementation of
     *            the application context bean configuration
     * @return The id of the entity if the addition happened or null.
     */
    public static <E extends AbstractModelObject> Integer addEntity(E entityToAdd, SessionFactory sessionFactory)
    {
        Integer entityId = null;

        // If the entity is a non-null object
        if (entityToAdd != null) {
            // Get a session from the session factory
            Session session = sessionFactory.openSession();

            // Transaction object to wrap the database save operation
            Transaction tx = null;

            try {
                // Try to begin a transaction
                tx = session.beginTransaction();

                // Save the entity to the database
                entityId = (Integer) session.save(entityToAdd);

                // Commit the transaction
                tx.commit();
            } catch (HibernateException e) {
                // An error occured; rollback the transaction
                tx.rollback();
                throw e;
            } finally {
                // Always close the session
                session.close();
            }
        }

        // Return the id of the saved transaction or null
        return entityId;
    }

    /**
     * Public utility method to update an entity in the database using hibernate
     * 
     * @param entityToEdit The entity to edit
     * @param sessionFactory Reference to the SessionFactory implementation of
     *            the application context bean configuration
     */
    public static <E extends AbstractModelObject> void editEntity(E entityToEdit, SessionFactory sessionFactory)
    {
        // If the entity is a non-null object
        if (entityToEdit != null) {
            // Get a session from the session factory
            Session session = sessionFactory.openSession();

            // Transaction object to wrap the database save operation
            Transaction tx = null;

            try {
                // Try to begin a transaction
                tx = session.beginTransaction();

                // Save the entity to the database
                session.update(entityToEdit);

                // Commit the transaction
                tx.commit();
            } catch (HibernateException e) {
                // An error occured; rollback the transaction
                tx.rollback();
                throw e;
            } finally {
                // Always close the session
                session.close();
            }
        }
    }

    /**
     * Public utility method to delete an entity from the database using
     * hibernate
     * 
     * @param entityToDelete The entity to delete
     * @param sessionFactory Reference to the SessionFactory implementation of
     *            the application context bean configuration
     */
    public static <E extends AbstractModelObject> void deleteEntity(E entityToDelete, SessionFactory sessionFactory)
    {
        // If the entity is a non-null object
        if (entityToDelete != null) {
            // Get a session from the session factory
            Session session = sessionFactory.openSession();

            // Transaction object to wrap the database save operation
            Transaction tx = null;

            try {
                // Try to begin a transaction
                tx = session.beginTransaction();

                // Save the entity to the database
                session.delete(entityToDelete);

                // Commit the transaction
                tx.commit();
            } catch (HibernateException e) {
                // An error occured; rollback the transaction
                tx.rollback();
                throw e;
            } finally {
                // Always close the session
                session.close();
            }
        }
    }

    /**
     * Public utility method to get a list of entities of a specific type from
     * the database using hibernate
     * 
     * @param classObj Class object for the generic type
     * @param sessionFactory Reference to the SessionFactory implementation of
     *            the application context bean configuration
     * @return The list of entities
     */
    public static <E extends AbstractModelObject> Collection<E> getEntities(Class<E> classObj,
            SessionFactory sessionFactory)
    {
        // Get all entities, without aliases or restrictions, and with no
        // pagination
        return getEntities(classObj, new ArrayList<HibernateAlias>(), new ArrayList<Criterion>(), 0,
                getEntityCount(classObj, sessionFactory), sessionFactory);
    }

    /**
     * @param sessionFactory
     * @param classObj
     * @param aliases
     * @param criterions
     * @return
     */
    public static <E extends AbstractModelObject> Collection<E> getEntities(Class<E> classObj,
            Collection<HibernateAlias> aliases, Collection<Criterion> criterions, SessionFactory sessionFactory)
    {
        // Get the entities with no pagination
        return getEntities(classObj, aliases, criterions, 0, getEntityCount(classObj, sessionFactory), sessionFactory);
    }

    /**
     * @param sessionFactory
     * @param classObj
     * @param skip
     * @param take
     * @return
     */
    public static <E extends AbstractModelObject> Collection<E> getEntities(Class<E> classObj, int skip, int take,
            SessionFactory sessionFactory)
    {
        // Get entities, with aliases and restrictions, but with no pagination
        return getEntities(classObj, new ArrayList<HibernateAlias>(), new ArrayList<Criterion>(), 0,
                getEntityCount(classObj, sessionFactory), sessionFactory);
    }

    /**
     * Public utility method to get a list of entities of a specific type from
     * the database using hibernate
     * 
     * @param sessionFactory Reference to the SessionFactory implementation of
     *            the application context bean configuration
     * @param classObj Class object for the generic type
     * @param aliases A collection of entity properties aliases
     * @param criterions A collection of all filtering criterions
     * @return The list of entities
     */
    @SuppressWarnings("unchecked")
    public static <E extends AbstractModelObject> Collection<E> getEntities(Class<E> classObj,
            Collection<HibernateAlias> aliases, Collection<Criterion> criterions, int skip, int take,
            SessionFactory sessionFactory)
    {
        Collection<E> entitiesList = null;

        // Get a session from the session factory
        Session session = sessionFactory.openSession();

        try {
            Criteria criteria = session.createCriteria(classObj);

            // Add each aliases to the criteria
            for (HibernateAlias alias : aliases) {
                criteria.createAlias(alias.getPropertyPath(), alias.getAlias());
            }

            // Add each criterion to the criteria
            for (Criterion criterion : criterions) {
                criteria.add(criterion);
            }

            // Add paging parameters
            criteria.setFirstResult(skip);
            criteria.setMaxResults(take);

            // Set dictinct root entity to remove duplicates of joins
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

            // Resolve the entity list
            entitiesList = (Collection<E>) criteria.list();
        } finally {
            // Always close the session
            session.close();
        }

        // Return the list of entities
        return entitiesList;
    }

    /**
     * Public utility method to get the first entity of a specific type that
     * matches a certain number of criterion using hibernate.
     * 
     * @param sessionFactory Reference to the SessionFactory implementation of
     *            the application context bean configuration
     * @param classObj Class object for the generic type
     * @param aliases A collection of entity properties aliases
     * @param criterions A collection of all filtering criterions
     * @return The first of entities
     */
    @SuppressWarnings("unchecked")
    public static <E extends AbstractModelObject> E getFirstEntity(Class<E> classObj,
            Collection<HibernateAlias> aliases, Collection<Criterion> criterions, SessionFactory sessionFactory)
    {
        E entity = null;

        // Get a session from the session factory
        Session session = sessionFactory.openSession();

        try {
            Criteria criteria = session.createCriteria(classObj);

            // Add each aliases to the criteria
            for (HibernateAlias alias : aliases) {
                criteria.createAlias(alias.getPropertyPath(), alias.getAlias());
            }

            // Add each criterion to the criteria
            for (Criterion criterion : criterions) {
                criteria.add(criterion);
            }

            // Set dictinct root entity to remove duplicates of joins
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

            // Resolve the first entity
            entity = (E) criteria.uniqueResult();
        } finally {
            // Always close the session
            session.close();
        }

        // Return the list of entities
        return entity;
    }

    /**
     * Public utility method to get an entity of a specific type and id from the
     * database using hibernate
     * 
     * @param entityId Id of the entity
     * @param sessionFactory Reference to the SessionFactory implementation of
     *            the application context bean configuration
     * @param classObj Class object for the generic type
     * @return The entity with the specified id
     */
    @SuppressWarnings("unchecked")
    public static <E extends AbstractModelObject> E getEntity(int entityId, Class<E> classObj,
            SessionFactory sessionFactory)
    {
        E entity = null;

        // Get a session from the session factory
        Session session = sessionFactory.openSession();

        try {
            Criteria criteria = session.createCriteria(classObj);
            criteria.add(Restrictions.idEq(entityId));

            // Put the query result into the list
            entity = (E) criteria.uniqueResult();
        } finally {
            // Always close the session
            session.close();
        }

        // Return the entity
        return entity;
    }

    /**
     * @param sessionFactory
     * @param classObj
     * @return
     */
    public static <E extends AbstractModelObject> int getEntityCount(Class<E> classObj, SessionFactory sessionFactory)
    {
        return getEntityCount(classObj, new ArrayList<HibernateAlias>(), new ArrayList<Criterion>(), sessionFactory);
    }

    /**
     * @param sessionFactory
     * @param classObj
     * @param aliases
     * @param criterions
     * @return
     */
    public static <E extends AbstractModelObject> int getEntityCount(Class<E> classObj,
            Collection<HibernateAlias> aliases, Collection<Criterion> criterions, SessionFactory sessionFactory)
    {
        int entityCount = 0;

        // Get a session from the session factory
        Session session = sessionFactory.openSession();

        try {
            Criteria criteria = session.createCriteria(classObj);

            // Add each aliases to the criteria
            for (HibernateAlias alias : aliases) {
                criteria.createAlias(alias.getPropertyPath(), alias.getAlias());
            }

            // Add each criterion to the criteria
            for (Criterion criterion : criterions) {
                criteria.add(criterion);
            }

            // Set the row count aggreggation
            criteria.setProjection(Projections.rowCount());

            // Resolve the entity list
            entityCount = ((Long) criteria.uniqueResult()).intValue();
        } finally {
            // Always close the session
            session.close();
        }

        return entityCount;
    }
}
