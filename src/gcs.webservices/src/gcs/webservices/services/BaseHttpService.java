package gcs.webservices.services;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gcs.webapp.utils.app.messages.IMessageLocalizer;
import gcs.webapp.utils.aspects.logging.Loggable;
import gcs.webapp.utils.aspects.logging.LoggingLevel;
import gcs.webapp.utils.aspects.validation.Validatable;
import gcs.webservices.sessions.SessionCache;

/**
 * @author Simon Turcotte-Langevin
 */
@Component
@Validatable
@Loggable(exitLevel = LoggingLevel.Information)
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
        return Response.ok().entity(responseEntity).build();
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
