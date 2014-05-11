package gcs.webservices.aop;

import javax.ws.rs.core.Response;

import gcs.webapp.utils.app.messages.ILocalizable;
import gcs.webapp.utils.app.messages.IMessageLocalizer;
import gcs.webapp.utils.exceptions.InternalException;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

// @Aspect
public class LocalizationAspect
{
    /** IMessageLocalizer instance to localize application messages. */
    private IMessageLocalizer messageLocalizer;

    /*@AfterThrowing(pointcut = "within(gcs.webservices..*) || " + 
                              "within(gcs.webservices.client..*)", throwing = "exception")
    public void localizeExceptionIfPossible(JoinPoint joinPoint, Throwable exception)
    {
        if (exception instanceof InternalException) {
            String key = ((InternalException)exception).getMessageKey();
            exception.
        }
    }
    
    @AfterReturning(pointcut = "within(gcs.webservices.services.*)", returning = "response")
    public void localizeHttpResponseEntity(JoinPoint joinPoint, Response response)
    {
        Object entity = response.getEntity();

        if (entity.getClass().isAssignableFrom(ILocalizable.class)) {
            ILocalizable localizableEntity = (ILocalizable) entity;
            localizableEntity.localize(messageLocalizer);
        }        
    }*/

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
