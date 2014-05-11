package gcs.webservices.aop.services;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.sun.jersey.api.core.InjectParam;

import gcs.webservices.authentication.SessionCache;
import gcs.webservices.dao.IAuditDao;
import gcs.webservices.models.Audit;

/**
 * @author Simon Turcotte-Langevin
 */
@Component
public class AuditService implements IAuditService
{
    @InjectParam
    private IAuditDao auditDao;

    @InjectParam
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
