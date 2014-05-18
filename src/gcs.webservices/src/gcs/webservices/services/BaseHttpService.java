package gcs.webservices.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gcs.webapp.utils.app.messages.IMessageLocalizer;
import gcs.webapp.utils.aspects.logging.Loggable;
import gcs.webapp.utils.aspects.validation.Validatable;
import gcs.webservices.sessions.SessionCache;

/**
 * Base class for all services. This class is :
 * <ul>
 * <li>{@link gcs.webapp.utils.aspects.validation.Validatable} so all public
 * methods of http services have their arguments validated.</li>
 * <li>{@link gcs.webapp.utils.aspects.logging.Loggable} so all public methods
 * of http services have logging when entering the method, when exiting the
 * method and on exception.</li>
 * </ul>
 * 
 * @author Simon Turcotte-Langevin
 */
@Component
@Validatable
@Loggable
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public abstract class BaseHttpService
{
    @Autowired
    protected SessionCache sessionCache;

    @Autowired
    protected IMessageLocalizer messageLocalizer;

    /**
     * Ends an http request by making last adjustements to the response and by
     * sending the http response.
     * 
     * @param responseEntity The response entity so far
     * @return The http response to the client
     */
    protected Response completeRequest(gcs.webservices.client.responses.Response responseEntity)
    {
        // Localize the response in the default application locale
        responseEntity.localize(messageLocalizer);
        
        return Response.ok(responseEntity).build();
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
