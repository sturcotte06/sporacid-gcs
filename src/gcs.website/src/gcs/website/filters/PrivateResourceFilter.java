package gcs.website.filters;

import gcs.website.utils.SessionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Simon Turcotte-Langevin
 */
public class PrivateResourceFilter implements Filter
{
    private Collection<Predicate<String>> publicResourceUriPredicates;

    /**
     * Destroys the public resources uri patterns
     */
    @Override
    public void destroy()
    {
        publicResourceUriPredicates = null;
    }

    /**
     * Filters private resources requests for not-authenticated users
     * 
     * @param req
     * @param resp
     * @param chain
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String requestUri = request.getRequestURI();
        boolean isAuthenticated = SessionUtils.getWsSession(request.getSession()) != null;

        if (!isAuthenticated && !isPublicResource(requestUri)) {
            response.sendRedirect(request.getContextPath() + "/public/connexion");
        } else {
            chain.doFilter(req, resp);
        }
    }

    /**
     * Returns whether a resource uri is public or private
     * 
     * @param contextPath Context path because the uris we have do not have the
     *            context
     * @param resourceUri The resource uri to test
     * @return Whether it's a public resource or not
     */
    private boolean isPublicResource(String resourceUri)
    {
        for (Predicate<String> publicResourceUriPredicate : publicResourceUriPredicates) {
            // If one of the predicate returns true, then the resource
            // is public.
            if (publicResourceUriPredicate.test(resourceUri)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Initialize the public resources uri patterns from the init parameter
     * 
     * @param config
     */
    @Override
    public void init(FilterConfig config) throws ServletException
    {
        String contextPath = config.getServletContext().getContextPath();
        String publicResources = config.getInitParameter("publicResources");
        String[] splitPublicResources = publicResources.split(",");
        publicResourceUriPredicates = new ArrayList<Predicate<String>>();

        for (String publicResourceUrl : splitPublicResources) {
            // Generate the regex
            String publicResourceUrlRegex = "^" + (contextPath + publicResourceUrl).replace(".", "\\.").replace("*", ".*").replace("/", "\\/") + "$";

            // Compile the regex as a predicate
            publicResourceUriPredicates.add(Pattern.compile(publicResourceUrlRegex).asPredicate());
        }
    }
}
