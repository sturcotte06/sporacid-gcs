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
    private IAuditDao auditDao;
    private SessionCache sessionCache;

    @Override
    public void audit(String ipAddress, String sessionKey, String message)
    {
        Audit audit = new Audit();
        audit.setTimestamp(new Date());

        if (!sessionCache.sessionExists(ipAddress, sessionKey)) {
            message = String.format("Session for ip %s and key %s does not exist.", ipAddress, sessionKey);
            audit.setMessage(String.format("Session for ip %s and key %s does not exist.", ipAddress, sessionKey));
        } else {
            sessionCache.withSession(ipAddress, sessionKey, session -> {
                audit.setUsername(session.getAuthenticationToken().getEmittedFor());
            });
        }

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
