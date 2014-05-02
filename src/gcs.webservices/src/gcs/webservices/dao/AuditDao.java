package gcs.webservices.dao;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.SessionFactory;

import gcs.webapp.utils.hibernate.HibernateUtils;
import gcs.webservices.models.Audit;

public class AuditDao implements IAuditDao
{
   @Getter @Setter
   private SessionFactory sessionFactory;
   
   @Override
   public void addAudit(Audit audit)
   {
      HibernateUtils.addEntity(audit, sessionFactory);
   }
}
