package gcs.website.controllers.services;

import gcs.webapp.utils.HandledByHttpService;
import gcs.webapp.utils.HttpMethod;
import gcs.webapp.utils.reflect.ReflectionUtils;
import gcs.website.controllers.services.beans.requests.Request;
import gcs.website.controllers.services.beans.responses.Response;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public abstract class HttpService
{
	/**
	 * Base url for the http service
	 */
	private String serviceUrl;
	
	/**
	 * Client for the web service consommation
	 */
	private Client jerseyClient;
	
	/**
	 * Cache for the responses
	 */
	private HttpServiceCache cache;

	/**
	 * Resolves the current method HandledByHttpService annotation.
	 * @param classObj 	Class object on which to perform reflection
	 * @return			The current method http service metadata, if it exists.				
	 */
	protected HandledByHttpService resolveHttpServiceMetadata(Class<? extends HttpService> classObj)
	{
		HandledByHttpService metadata = null;	
		
		// Theres no best way in java to identify the right method.
		// This should work in 99% of cases since overloads probably will share
		// the same web service metadata
		Method firstMatchingMethod = ReflectionUtils.getFirstCurrentMethodMetadata(classObj);
		metadata = firstMatchingMethod.getAnnotation(HandledByHttpService.class);
		
		return metadata;
	}

	/**
	 * 
	 * @param metadata
	 * @return
	 */
	protected <T extends Response> T getResponse(HandledByHttpService metadata, Class<T> classObj)
	{
		// Return a get response with a null request
		return getResponse(metadata, classObj, null);
	}
	
	/**
	 * 
	 * @param metadata
	 * @param classObj
	 * @param request
	 * @return
	 */
	protected <T extends Response> T getResponse(
			HandledByHttpService metadata, Class<T> classObj, Request request)
	{
		T response = null;
		
		// Get the jersey client for this service
		Client client = getJerseyClient();
		
		// Build the url from the metadata
		String url = this.getServiceUrl() + metadata.path();
		
		// Build the web response
		WebResource resource = client.resource(url);

		// If it's an http get, we cannot attach an entity. Parameters must be sent as query strings 
		if (metadata.method() == HttpMethod.Get) {
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
		if (metadata.method() != HttpMethod.Get) {
			responseBuilder
				// Produces only json
				.type(MediaType.APPLICATION_JSON)
				// Set the request object
				.entity(request);
		}
		
		// Execute the http service request with the metadata method
		response = responseBuilder
			.method(metadata.method().name().toUpperCase(), ClientResponse.class)
			.getEntity(classObj);
		
		return response;
	}
	
	/**
	 * Getter for the jersey client
	 * @return The jersey client
	 */
	public Client getJerseyClient()
	{
		return jerseyClient;
	}

	/**
	 * Setter for the jersey client
	 * @param jerseyClient The new jersey client
	 */
	public void setJerseyClient(Client jerseyClient)
	{
		this.jerseyClient = jerseyClient;
	}

	/**
	 * Getter for the http service cache
	 * @return The http service cache
	 */
	public HttpServiceCache getCache()
	{
		return cache;
	}

	/**
	 * Setter for the http service cache
	 * @param cache The new http service cache
	 */
	public void setCache(HttpServiceCache cache)
	{
		this.cache = cache;
	}
	
	/**
	 * Getter for the service url
	 * @return the server url 
	 */
	public String getServiceUrl()
	{
		return serviceUrl;
	}

	/**
	 * Setter for the service url
	 * @param serviceUrl the new server url
	 */
	public void setServiceUrl(String serviceUrl)
	{
		this.serviceUrl = serviceUrl;
	}
}
