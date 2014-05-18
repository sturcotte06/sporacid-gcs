package gcs.webservices.client;

import java.util.Map;

import gcs.webapp.utils.HttpServiceRoute;
import gcs.webapp.utils.aspects.logging.Loggable;
import gcs.webapp.utils.aspects.validation.Validatable;
import gcs.webservices.client.requests.Request;
import gcs.webservices.client.responses.Response;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;

/**
 * @author Simon Turcotte-Langevin
 */
@Loggable
public abstract class HttpServiceClient
{
    /** Base url for the http service. */
    private String serviceUrl;
    /** Client for the web service consommation. */
    private Client jerseyClient;
    /** Cache for the responses. */
    private HttpServiceCache cache;

    /**
     * @param route
     * @param classObj
     * @return
     */
    protected <TResponse extends Response> TResponse getResponse(HttpServiceRoute route, Class<TResponse> classObj)
    {
        // Return get response with a null request and null options
        return getResponse(route, classObj, null, null);
    }

    /**
     * @param route
     * @param classObj
     * @param options
     * @return
     */
    protected <TResponse extends Response> TResponse getResponse(HttpServiceRoute route, Class<TResponse> classObj,
            Map<String, Object> options)
    {
        // Return get response with a null request
        return getResponse(route, classObj, null, options);
    }

    /**
     * @param route
     * @param classObj
     * @param options
     * @return
     */
    protected <TResponse extends Response> TResponse getResponse(HttpServiceRoute route, Class<TResponse> classObj,
            Request request)
    {
        // Return a get response with null options
        return getResponse(route, classObj, request, null);
    }

    /**
     * @param route
     * @param classObj
     * @param request
     * @param options
     * @return
     */
    protected <TResponse extends Response> TResponse getResponse(HttpServiceRoute route, Class<TResponse> classObj,
            Request request, Map<String, Object> options)
    {
        TResponse response = null;

        // Set the base url of the services
        Invocation.Builder builder = jerseyClient.target(serviceUrl + route.getPath()).request(
                MediaType.APPLICATION_JSON);

        if (options != null) {
            // Add all options as query string
            for (Map.Entry<String, Object> option : options.entrySet()) {
                builder.property(option.getKey(), option.getValue());
            }
        }

        // Get the response from the web services
        response = request != null
                ? builder.method(route.getMethod().name().toUpperCase(), Entity.json(request), classObj)
                : builder.method(route.getMethod().name().toUpperCase(), classObj);

        return response;
    }

    /**
     * @return the serviceUrl
     */
    public String getServiceUrl()
    {
        return serviceUrl;
    }

    /**
     * @param serviceUrl the serviceUrl to set
     */
    public void setServiceUrl(String serviceUrl)
    {
        this.serviceUrl = serviceUrl;
    }

    /**
     * @return the jerseyClient
     */
    public Client getJerseyClient()
    {
        return jerseyClient;
    }

    /**
     * @param jerseyClient the jerseyClient to set
     */
    public void setJerseyClient(Client jerseyClient)
    {
        this.jerseyClient = jerseyClient;
    }

    /**
     * @return the cache
     */
    public HttpServiceCache getCache()
    {
        return cache;
    }

    /**
     * @param cache the cache to set
     */
    public void setCache(HttpServiceCache cache)
    {
        this.cache = cache;
    }
}
