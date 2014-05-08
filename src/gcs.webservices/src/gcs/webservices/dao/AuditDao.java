package gcs.webservices.dao;

import org.hibernate.SessionFactory;

import gcs.webapp.utils.hibernate.HibernateUtils;
import gcs.webservices.models.Audit;

/**
 * 
 * @author Simon Turcotte-Langevin
 *
 */
public class AuditDao implements IAuditDao
{
    private SessionFactory sessionFactory;

    @Override
    public void addAudit(Audit audit)
    {
        HibernateUtils.addEntity(audit, sessionFactory);
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
