package gcs.webservices.aspects;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import gcs.webapp.utils.exceptions.NotAuthenticatedException;
import gcs.webapp.utils.app.security.CrudOperator;
import gcs.webapp.utils.app.security.SecureModule;
import gcs.webapp.utils.app.security.IModuleSecurityProvider;
import gcs.webapp.utils.exceptions.UnauthorizedException;
import gcs.webservices.client.beans.SessionToken;
import gcs.webservices.models.Membre;
import gcs.webservices.models.Role;
import gcs.webservices.sessions.SessionCache;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author Simon Turcotte-Langevin
 */
@Aspect
public class AuthorizationAspect
{
    /** Log4j logger. */
    private static final Logger logger = Logger.getLogger(AuthorizationAspect.class);

    /** Module security provider to obtain role mappings for module. */
    private IModuleSecurityProvider moduleSecurityProvider;

    /** Session cache where user sessions are stored. */
    private SessionCache sessionCache;

    @Pointcut("within(@gcs.webapp.utils.app.security.SecureModule *)")
    public void secureModuleType()
    {}

    @Pointcut("execution(@gcs.webapp.utils.app.security.CrudOperator * *(..))")
    public void crudOperationMethod()
    {}

    @Pointcut("execution(* *(gcs.webservices.client.beans.SessionToken, ..))")
    public void hasSessionToken()
    {}

    @Pointcut("execution(public * *(..))")
    public void publicMethod()
    {}

    // @Before("secureModuleType() && crudOperationMethod() && publicMethod() && hasSessionToken()"
    // +
    // " && @annotation(secureModule) && @annotation(crudOperator) && args(sessionToken)")

    @Before("secureModuleType() && @annotation(crudOperator) && args(sessionToken, ..)")
    public void beforeAnnotatedMethods(JoinPoint joinPoint, CrudOperator crudOperator, SessionToken sessionToken)
    {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        SecureModule secureModule = null;
        try {
            secureModule = Class.forName(className).getAnnotation(SecureModule.class);
        } catch (ClassNotFoundException ex) {
            // Something's fishy.
            // Don't take any chances and deny access.
            throw new UnauthorizedException();
        }

        // Authorize the user.
        // This will throw if the user is not authenticated or not authorized.
        authorizeThenProceed(joinPoint, secureModule, crudOperator, sessionToken);
    }

    /**
     * @param joinPoint
     * @param secureModule
     * @param crudOperator
     * @param sessionToken
     */
    public void authorizeThenProceed(JoinPoint joinPoint, SecureModule secureModule, CrudOperator crudOperator,
            SessionToken sessionToken)
    {
        // Check if the user has a session
        if (!sessionCache.sessionExists(sessionToken)) {
            // User is not authenticated
            throw new NotAuthenticatedException();
        }

        // Get the session from the session cache
        sessionCache.withSession(sessionToken, (session) -> {
            // if (session == null) {
            // // User is not authenticated
            // throw new NotAuthenticatedException();
            // }

                // Get the membre object associated with the session
                Membre membre = session.getMembre();

                // TODO This must be pulled from the database
                // Role[] roles = membre.getRoles();
                String[] roleNames = { "membre" };

                boolean hasRight = false;
                for (String roleName : roleNames) {
                    // Logical "or" to know if one of the user's role has rights
                    // to this module's operation.
                    hasRight |= moduleSecurityProvider.hasRights(secureModule.name(), roleName, crudOperator.value());
                    if (hasRight) {
                        // He has rights, no need to check further.
                        break;
                    }
                }

                if (!hasRight) {
                    // User is not authorized
                    // TODO put the username
                    throw new UnauthorizedException("AJ50440");
                }
            });
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

    /**
     * @return the moduleSecurityProvider
     */
    public IModuleSecurityProvider getModuleSecurityProvider()
    {
        return moduleSecurityProvider;
    }

    /**
     * @param moduleSecurityProvider the moduleSecurityProvider to set
     */
    public void setModuleSecurityProvider(IModuleSecurityProvider moduleSecurityProvider)
    {
        this.moduleSecurityProvider = moduleSecurityProvider;
    }
}
