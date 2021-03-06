package gcs.webservices;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author Simon Turcotte-Langevin
 */
public class WebApplication extends ResourceConfig
{
    /**
     * Constructor.
     */
    public WebApplication()
    {
        // Scan everything in those packages
        packages("gcs.webservices.services");

        // Register exception mappers.
        // Most general mapper should always be the last.
        register(gcs.webservices.providers.InternalExceptionMapper.class);
        register(gcs.webservices.providers.WebApplicationExceptionMapper.class);
        register(gcs.webservices.providers.ThrowableMapper.class);
    }
}
