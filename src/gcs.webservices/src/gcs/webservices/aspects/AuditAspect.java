package gcs.webservices.aspects;

import gcs.webapp.utils.app.security.SecureModule;
import gcs.webservices.aspects.services.IAuditService;
import gcs.webservices.client.beans.SessionToken;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
@Aspect
public class AuditAspect
{
    /** Log4j logger. */
    private static final Logger logger = Logger.getLogger(AuditAspect.class);

    /** Audit service that handles audits. */
    private IAuditService auditService;

    @Pointcut("within(@gcs.webservices.aspects.Auditable *)")
    public void auditableType()
    {}

    @Pointcut("execution(@gcs.webservices.aspects.Auditable * *(gcs.webservices.client.beans.SessionToken, ..))")
    public void auditableMethod()
    {}

    @Pointcut("execution(public * *(..))")
    public void publicMethod()
    {}

    @Before("auditableMethod() && args(sessionToken, ..)")
    public void beforeAnnotatedMethods(JoinPoint joinPoint, SessionToken sessionToken)
    {
        auditThenProceed(joinPoint, sessionToken);
    }

    @Before("auditableType() && publicMethod() && args(sessionToken, ..)")
    public void beforeAnnotatedTypes(JoinPoint joinPoint, SessionToken sessionToken)
    {
        auditThenProceed(joinPoint, sessionToken);
    }

    /**
     * @param joinPoint
     * @param request
     * @throws ClassNotFoundException
     */
    private void auditThenProceed(JoinPoint joinPoint, SessionToken sessionToken)
    {
        // Get the class name so we can log class we were in
        String className = joinPoint.getSignature().getDeclaringTypeName();

        // Get the method name so we can log method we were in
        String methodName = ((MethodSignature) joinPoint.getSignature()).getMethod().getName();

        String secureModuleString = "";
        Class<?> classObj = null;
        try {
            // Try to get the class by name
            classObj = Class.forName(className);
        } catch (ClassNotFoundException ex) {
            logger.info("Error in audit aspect. Couldn't get class by name.");
        }

        if (classObj != null) {
            SecureModule secureModule = classObj.getAnnotation(SecureModule.class);
            if (secureModule != null) {
                // Secure module annotation was found.
                // We can specify a message suffix for the module we're in.
                secureModuleString = " in module " + secureModule.name();
            }
        }

        // Do the actual audit with all informations harvested.
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
