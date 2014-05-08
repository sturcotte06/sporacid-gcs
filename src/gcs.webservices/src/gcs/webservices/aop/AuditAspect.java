package gcs.webservices.aop;

import gcs.webservices.aop.services.IAuditService;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AuditAspect
{
    /** Log4j logger. */
    private static final Logger logger = Logger.getLogger(AuditAspect.class);
    /** Audit service that handles audits. */
    private IAuditService auditService;

    /* @Before("args(gcs.webservices.services.beans.requests.AuthenticatedRequest)"
     * ) public void auditPrivateCall(AuthenticatedRequest request) {
     * auditService.audit(request.getSessionKey(), "Test"); } */

    @Before("@annotation(javax.ws.rs.Path)")
    public void auditPrivateCall()
    {
        auditService.audit("test", "Test");
    }

    /**
     * @return the auditService
     */
    public IAuditService getAuditService()
    {
        return auditService;
    }

    /**
     * @param auditService the auditService to set
     */
    public void setAuditService(IAuditService auditService)
    {
        this.auditService = auditService;
    }
}
