package gcs.webservices.aspects.services;

import java.util.Date;

import gcs.webservices.dao.IAuditDao;
import gcs.webservices.models.Audit;
import gcs.webservices.sessions.SessionCache;

/**
 * @author Simon Turcotte-Langevin
 */
public class AuditService implements IAuditService
{
    /** */
    private IAuditDao auditDao;

    /** */
    private SessionCache sessionCache;

    @Override
    public void audit(String ipv4Address, String sessionKey, String message)
    {
        Audit audit = new Audit();

        if (!sessionCache.sessionExists(ipv4Address, sessionKey)) {
            message = String.format("Session for ip %s and key %s does not exist.", ipv4Address, sessionKey);
            audit.setUsername("anonymous");
        } else {
            sessionCache.withSession(ipv4Address, sessionKey, session -> {
                audit.setUsername(session.getAuthenticationToken().getEmittedFor());
            });
        }

        audit.setTimestamp(new Date());
        audit.setMessage(message);
        auditDao.addAudit(audit);
    }

    /**
     * @return the auditDao
     */
    public IAuditDao getAuditDao()
    {
        return auditDao;
    }

    /**
     * @param auditDao the auditDao to set
     */
    public void setAuditDao(IAuditDao auditDao)
    {
        this.auditDao = auditDao;
    }

    /**
     * @return the sessionCache
     */
    public SessionCache getSessionCache()
    {
        return sessionCache;
    }

    /**
     * @param sessionCache the sessionCache to set
     */
    public void setSessionCache(SessionCache sessionCache)
    {
        this.sessionCache = sessionCache;
    }
}
