package gcs.website.filters;

import gcs.website.utils.SessionUtils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class PrivateResourceFilter implements Filter 
{
	/**
	 * 
	 */
	private String[] publicResourceUriPatterns;
	
	/**
	 * Destroys the public resources uri patterns
	 */
	@Override
	public void destroy() 
	{
		publicResourceUriPatterns = null;
	}

	/**
	 * Filters private resources requests for not-authenticated users
	 * @param req
	 * @param resp
	 * @param chain
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) 
			throws IOException, ServletException 
	{
		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        boolean isAuthenticated = SessionUtils.getWsSession(request.getSession()) != null;
		
		if (!isAuthenticated && !isPublicResource(contextPath, requestUri)) {
			response.sendRedirect(contextPath + "/public/connexion");
		} else {
			chain.doFilter(req, resp);
		}
	}

	/**
	 * Returns whether a resource uri is public or private
	 * @param contextPath Context path because the uris we have do not have the context
	 * @param resourceUri The resource uri to test
	 * @return Whether it's a public resource or not
	 */
	private boolean isPublicResource(String contextPath, String resourceUri) 
	{
		for (String publicResourceUrl : publicResourceUriPatterns) {
			String publicResourceUrlRegex = 
					"^" + (contextPath + publicResourceUrl)
					.replace(".", "\\.").replace("*", ".*").replace("/", "\\/") + "$";
			if (resourceUri.matches(publicResourceUrlRegex)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Initialize the public resources uri patterns from the init parameter
	 * @param config
	 */
	@Override
	public void init(FilterConfig config) throws ServletException 
	{
		String publicResources = config.getInitParameter("publicResources");
		publicResourceUriPatterns = publicResources.split(",");
	}
}
