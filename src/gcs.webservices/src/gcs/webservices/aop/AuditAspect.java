package gcs.webservices.aop;

import gcs.webapp.utils.app.security.SecureModule;
import gcs.webservices.aop.services.IAuditService;
import gcs.webservices.client.beans.SessionToken;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class AuditAspect
{
    /** Audit service that handles audits. */
    private IAuditService auditService;

    /**
     * @param joinPoint
     * @param request
     * @throws ClassNotFoundException
     */
    @Before("execution(@gcs.webservices.aop.Auditable * gcs.webservices.services..*(..)) && args(sessionToken)")
    public void auditPrivateCall(JoinPoint joinPoint, SessionToken sessionToken) throws ClassNotFoundException
    {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = ((MethodSignature) joinPoint.getSignature()).getMethod().getName();
        Class<?> cls = Class.forName(className);
        SecureModule secureModule = cls.getAnnotation(SecureModule.class);
        String secureModuleString = secureModule != null ? " in module " + secureModule.name() : "";

        auditService.audit(sessionToken.getIpv4Address(), sessionToken.getSessionKey(),
                String.format("Accessing [%s.%s]%s.", className, methodName, secureModuleString));
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
