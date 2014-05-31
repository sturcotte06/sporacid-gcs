package gcs.webservices.dao;

import gcs.webservices.models.Audit;

/**
 * Interface for all audit data access objects.
 * 
 * @author Simon Turcotte-Langevin
 */
public interface IAuditDao
{
    /**
     * Adds an audit into the system.
     * 
     * @param audit An audit model object.
     */
    public void addAudit(Audit audit);
}
