package gcs.webservices.dao;

import gcs.webservices.models.Audit;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public interface IAuditDao
{
   /**
    * 
    * @param audit
    */
   public void addAudit(Audit audit);
}
