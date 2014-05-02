package gcs.webservices.dao;

import org.hibernate.SessionFactory;

import gcs.webapp.utils.hibernate.HibernateUtils;
import gcs.webservices.models.Audit;

public class AuditDao implements IAuditDao
{
   private SessionFactory sessionFactory;
   
   @Override
   public void addAudit(Audit audit)
   {
      HibernateUtils.addEntity(audit, sessionFactory);
   }
}
