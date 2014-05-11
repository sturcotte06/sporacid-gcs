package gcs.webservices.client;

import gcs.webapp.utils.HandledByHttpService;
import gcs.webapp.utils.HttpMethod;
import gcs.webapp.utils.exceptions.ValidationException;
import gcs.webapp.utils.reflect.ReflectionUtils;
import gcs.webservices.client.requests.Request;
import gcs.webservices.client.responses.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.springframework.validation.Errors;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author Simon Turcotte-Langevin
 */
public abstract class HttpServiceClient
{
    /** Base url for the http service. */
    private String serviceUrl;
    /** Client for the web service consommation. */
    private Client jerseyClient;
    /** Cache for the responses. */
    private HttpServiceCache cache;
    /** Default validator from javax to validate annotated classes. */
    private Validator validator;

    /**
     * @param request
     */
    protected <TRequest extends Request> void validateRequest(TRequest request)
    {
        final String objectName = "request";
        Errors errors = new MapBindingResult(new HashMap<Object, Object>(), objectName);
        validator.validate(request, errors);
        if (!errors.hasErrors()) {
            return;
        }

        List<ObjectError> objErrors = errors.getAllErrors();
        List<String> validationMessageKeys = new ArrayList<>(objErrors.size());
        for (ObjectError objError : errors.getAllErrors()) {
            validationMessageKeys.add(objError.getDefaultMessage());
        }

        throw new ValidationException(validationMessageKeys);
    }

    /**
     * @param metadata
     * @return
     */
    protected <TResponse extends Response> TResponse getResponse(HandledByHttpService metadata,
            Class<TResponse> classObj)
    {
        // Return a get response with a null request
        return getResponse(metadata, classObj, null);
    }

    /**
     * @param metadata
     * @param classObj
     * @param request
     * @return
     */
    protected <TResponse extends Response> TResponse getResponse(HandledByHttpService metadata,
            Class<TResponse> classObj, Request request)
    {
        TResponse response = null;

        // Get the jersey client for this service
        Client client = getJerseyClient();

        // Build the url from the metadata
        String url = this.getServiceUrl() + metadata.getPath();

        // Build the web response
        WebResource resource = client.resource(url);

        // If it's an http get, we cannot attach an entity. Parameters must be
        // sent as query strings
        if (metadata.getMethod() == HttpMethod.Get) {
            MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
            Map<String, Object> objectProperties = null;
            try {
                objectProperties = ReflectionUtils.getObjectProperties(request, request.getClass());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            if (objectProperties != null) {
                // Put each property values as string into the query params
                for (Entry<String, Object> entry : objectProperties.entrySet()) {
                    queryParams.add(entry.getKey(), entry.getValue().toString());
                }
            }

            resource = resource.queryParams(queryParams);
        }

        Builder responseBuilder = resource.accept(MediaType.APPLICATION_JSON);

        // If it's not http get, attach the entity to the request as json
        if (metadata.getMethod() != HttpMethod.Get) {
            responseBuilder
            // Produces only json
                    .type(MediaType.APPLICATION_JSON)
                    // Set the request object
                    .entity(request);
        }

        // Execute the http service request with the metadata method
        response = responseBuilder.method(metadata.getMethod().name().toUpperCase(), ClientResponse.class).getEntity(
                classObj);

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

    /**
     * @return the validator
     */
    public Validator getValidator()
    {
        return validator;
    }

    /**
     * @param validator the validator to set
     */
    public void setValidator(Validator validator)
    {
        this.validator = validator;
    }
}
