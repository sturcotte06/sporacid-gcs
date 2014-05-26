package gcs.webservices.dao;

import gcs.webapp.utils.hibernate.HibernateUtils;
import gcs.webservices.models.Commandite;

import java.util.Collection;

import org.hibernate.SessionFactory;

public class CommanditeDao implements ICommanditeDao
{
    private SessionFactory sessionFactory;

    @Override
    public Collection<Commandite> getAllCommandite()
    {
        return HibernateUtils.getEntities(Commandite.class, sessionFactory);
    }

    @Override
    public Commandite getCommandite(int idCommandite)
    {
        // TODO Auto-generated method stub
        return null;
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
