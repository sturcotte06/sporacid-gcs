package gcs.webservices.aop;

import javax.ws.rs.core.Response;

import gcs.webapp.utils.app.messages.ILocalizable;
import gcs.webapp.utils.app.messages.IMessageLocalizer;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LocalizationAspect
{
    private static final Logger logger = Logger.getLogger(LocalizationAspect.class);
    private IMessageLocalizer messageLocalizer;

    // @AfterReturning(pointcut =
    // "call(public javax.ws.rs.core.Response gcs.webservices.services.*.*(..))",
    // returning = "response")
    // @AfterReturning(pointcut =
    // "within(gcs.webservices.services.AuthenticationService)", returning =
    // "response")
    public void localizeHttpResponseEntity(JoinPoint joinPoint, Response response)
    {
        logger.error("dagfasgag");

        Object entity = response.getEntity();
        if (entity.getClass().isAssignableFrom(ILocalizable.class)) {
            ILocalizable localizableEntity = (ILocalizable) entity;
            localizableEntity.localize(messageLocalizer);
        }
    }

    /**
     * @return the messageLocalizer
     */
    public IMessageLocalizer getMessageLocalizer()
    {
        return messageLocalizer;
    }

    /**
     * @param messageLocalizer the messageLocalizer to set
     */
    public void setMessageLocalizer(IMessageLocalizer messageLocalizer)
    {
        this.messageLocalizer = messageLocalizer;
    }
}
