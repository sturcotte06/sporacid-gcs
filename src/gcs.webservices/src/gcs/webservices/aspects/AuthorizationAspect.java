package gcs.webservices.aspects;

import java.util.ArrayList;
import java.util.Collection;

import gcs.webapp.utils.exceptions.NotAuthenticatedException;
import gcs.webapp.utils.app.security.CrudOperator;
import gcs.webapp.utils.app.security.SecureModule;
import gcs.webapp.utils.app.security.IModuleSecurityProvider;
import gcs.webapp.utils.exceptions.UnauthorizedException;
import gcs.webservices.client.beans.ContextualSessionToken;
import gcs.webservices.models.Membre;
import gcs.webservices.models.MembreClub;
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

    @Pointcut("execution(* *(gcs.webservices.client.beans.ContextualSessionToken, ..))")
    public void hasSessionToken()
    {}

    @Pointcut("execution(public * *(..))")
    public void publicMethod()
    {}

    // @Before("secureModuleType() && crudOperationMethod() && publicMethod() && hasSessionToken()"
    // +
    // " && @annotation(secureModule) && @annotation(crudOperator) && args(sessionToken)")

    @Before("secureModuleType() && @annotation(crudOperator) && args(sessionToken, ..)")
    public void beforeAnnotatedMethods(JoinPoint joinPoint, CrudOperator crudOperator,
            ContextualSessionToken sessionToken)
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
            ContextualSessionToken sessionToken)
    {
        // Check if the user has a session
        if (!sessionCache.sessionExists(sessionToken)) {
            // User is not authenticated
            throw new NotAuthenticatedException();
        }

        // Get the session from the session cache
        sessionCache.withSession(sessionToken, (session) -> {

            // Get the name of the requested context
            String contextName = sessionToken.getContext().getName();

            // Get the member object associated with the session
            Membre membre = session.getMembre();

            // Get all roles for the member
            Collection<Role> roles = new ArrayList<>();
            for (MembreClub membreClub : membre.getClubs()) {
                String clubName = membreClub.getClub().getNom();
                if (clubName.equalsIgnoreCase(contextName)) {
                    // Found the club requested by the client
                    roles = membreClub.getRoles();
                    break;
                }
            }

            if (roles == null) {
                // Can't get roles of user, log a warning because this is
                // possible an hibernate configuration bug.
                logger.warn(String.format("Couldn't load roles for the user %s. "
                        + "This is possibly a configuration bug.", membre.getCodeUniversel()));

                // Unauthorized by default
                throw new UnauthorizedException(membre.getCodeUniversel());
            }

            boolean hasRight = false;
            for (Role role : roles) {
                // Logical "or" to know if one of the user's role has rights
                // to this module's operation.
                hasRight |= moduleSecurityProvider.hasRights(secureModule.name(), role.getNom(),
                        crudOperator.value());
                if (hasRight) {
                    // He has rights, no need to check further.
                    break;
                }
            }

            if (!hasRight) {
                // User is not authorized
                throw new UnauthorizedException(membre.getCodeUniversel());
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
