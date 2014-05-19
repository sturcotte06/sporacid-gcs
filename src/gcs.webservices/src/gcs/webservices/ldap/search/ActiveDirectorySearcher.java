/**
 * The MIT License
 *
 * Copyright (c) 2010-2012 www.myjeeva.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE. 
 * 
 */
package gcs.webservices.ldap.search;

import gcs.webapp.utils.exceptions.ArgumentNullException;
import gcs.webapp.utils.exceptions.EntityNotFoundException;
import gcs.webapp.utils.exceptions.InternalException;

import java.io.Closeable;
import java.io.IOException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.apache.log4j.Logger;

/**
 * Query Active Directory using Java
 * 
 * @filename ActiveDirectorySearcher.java
 * @author <a href="mailto:jeeva@myjeeva.com">Jeevanandam Madanagopal</a>
 * @copyright &copy; 2010-2012 www.myjeeva.com
 */
public class ActiveDirectorySearcher implements ILdapSearcher, Closeable
{
    /** Log4j logger. */
    private static final Logger logger = Logger.getLogger(ActiveDirectorySearcher.class);
    /** The base filter where the user search begins. */
    private static final String cBaseFilter = "(&((&(objectCategory=Person)(objectClass=User)))";

    // required private variables
    private Properties properties;
    private DirContext dirContext;
    private SearchControls searchCtls;
    private String[] returnAttributes = { "sAMAccountName", // username
            "mail", // email
            "givenName", // first name
            "sn", // last name
    };
    private String domainBase;

    /**
     * constructor with parameter for initializing a LDAP context
     * 
     * @param username a {@link java.lang.String} object - username to establish
     *            a LDAP connection
     * @param password a {@link java.lang.String} object - password to establish
     *            a LDAP connection
     * @param domainController a {@link java.lang.String} object - domain
     *            controller name for LDAP connection
     */
    public ActiveDirectorySearcher(String username, String password, String domainController)
    {
        properties = new Properties();

        properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        properties.put(Context.PROVIDER_URL, "LDAP://" + domainController);
        properties.put(Context.SECURITY_PRINCIPAL, username + "@" + domainController);
        properties.put(Context.SECURITY_CREDENTIALS, password);

        // initializing active directory LDAP connection
        try {
            dirContext = new InitialDirContext(properties);
        } catch (NamingException ex) {
            throw new InternalException("ldap_search_general_error", ex);
        }

        // default domain base for search
        domainBase = getDomainBase(domainController);

        // initializing search controls
        searchCtls = new SearchControls();
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        searchCtls.setReturningAttributes(returnAttributes);
    }

    /**
     * search the Active directory by username/email id for given search base
     * 
     * @param searchValue a {@link java.lang.String} object - search value used
     *            for AD search for eg. username or email
     * @param searchBy a {@link java.lang.String} object - scope of search by
     *            username or by email id
     * @param searchBase a {@link java.lang.String} object - search base value
     *            for scope tree for eg. DC=myjeeva,DC=com
     * @return search result a {@link javax.naming.NamingEnumeration} object -
     *         active directory search result
     * @throws NamingException
     */
    private NamingEnumeration<SearchResult> searchUserInternal(String searchValue, SearchBy searchBy, String searchBase)
    {
        String filter = getFilter(searchValue, searchBy);
        String base = (null == searchBase)
                ? domainBase
                : getDomainBase(searchBase);

        try {
            return this.dirContext.search(base, filter, this.searchCtls);
        } catch (NamingException ex) {
            throw new InternalException("ldap_search_general_error");
        }
    }

    /**
     * Search the ldap for a user with the given searchValue for the searchBy
     * property. The search will be done in the searchBase domain controllers.
     * 
     * @param searchValue The value for the search (example: AJ12345).
     * @param searchBy To ldap what property the value should be mapped to.
     * @return The user with information filled from the ldap.
     */
    @Override
    public LdapUser searchUser(String searchValue, SearchBy searchBy)
    {
        LdapUser ldapUser = new LdapUser();
        boolean found = trySearchUser(searchValue, searchBy, ldapUser);
        
        if (!found) {
            // User was not found
            throw new EntityNotFoundException("active directory user", searchValue);
        }

        return ldapUser;

        /* NamingEnumeration<SearchResult> results =
         * searchUserInternal(searchValue, searchBy, null); try { if
         * (results.hasMore()) { SearchResult result = results.next();
         * Attributes attrs = result.getAttributes(); String username =
         * attrs.get(returnAttributes[0]).get().toString(); String email =
         * attrs.get(returnAttributes[1]).get().toString(); String firstName =
         * attrs.get(returnAttributes[2]).get().toString(); String lastName =
         * attrs.get(returnAttributes[3]).get().toString();
         * 
         * return new LdapUser(username, email, firstName, lastName); }
         * 
         * // User was not found throw new
         * EntityNotFoundException("active directory user", searchValue); }
         * catch (NamingException ex) { throw new
         * InternalException("ldap_search_general_error", ex); } */
    }

    /**
     * Search the ldap for a user with the given searchValue for the searchBy
     * property. The search will be done in the searchBase domain controllers.
     * This method will not throw if the user is not found.
     * 
     * @param searchValue The value for the search (example: AJ12345).
     * @param searchBy To what ldap property the value should be mapped to.
     * @param outUser The user reference thet will be filled from the ldap.
     * @return Whether the user was found or not.
     */
    @Override
    public boolean trySearchUser(String searchValue, SearchBy searchBy, LdapUser outUser)
    {
        if (searchValue == null || searchValue.isEmpty()) {
            throw new ArgumentNullException("searchValue");
        }

        if (outUser == null) {
            throw new ArgumentNullException("outUser");
        }

        NamingEnumeration<SearchResult> results = null;
        try {
            // Call the search user internal method to get an enumeration of
            // results
            results = searchUserInternal(searchValue, searchBy, null);

            // If there's at least one result
            if (results.hasMore()) {
                // Get the result
                SearchResult result = results.next();

                // Get its attributes
                Attributes attrs = result.getAttributes();

                // Fetch all useful informations from the attributes
                String username = attrs.get(returnAttributes[0]).get().toString();
                String email = attrs.get(returnAttributes[1]).get().toString();
                String firstName = attrs.get(returnAttributes[2]).get().toString();
                String lastName = attrs.get(returnAttributes[3]).get().toString();

                // Set all fetched properties to the ldap user
                outUser.set(username, email, firstName, lastName);

                // User was found
                return true;
            }

            // User was not found
            return false;
        } catch (NamingException ex) {
            // Wrap the exception in a more genric exception for the application
            throw new InternalException("ldap_search_general_error", ex);
        } finally {
            // Close the search results
            if (results != null) {
                try {
                    results.close();
                } catch (NamingException ex) {
                    // Nothing we can do except log
                    logger.error("Couldn't close the ldap results.", ex);
                }
            }
        }
    }

    /**
     * active directory filter string value
     * 
     * @param searchValue a {@link java.lang.String} object - search value of
     *            username/email id for active directory
     * @param searchBy a {@link java.lang.String} object - scope of search by
     *            username or email id
     * @return a {@link java.lang.String} object - filter string
     */
    private String getFilter(String searchValue, SearchBy searchBy)
    {
        String filter = cBaseFilter;
        switch (searchBy) {
            case Mail:
                filter += "(mail=" + searchValue + "))";
                break;
            case SamAccountName:
                filter += "(samaccountname=" + searchValue + "))";
                break;
            default:
                throw new UnsupportedOperationException();
        }

        return filter;
    }

    /**
     * creating a domain base value from domain controller name
     * 
     * @param base a {@link java.lang.String} object - name of the domain
     *            controller
     * @return a {@link java.lang.String} object - base name for eg.
     *         DC=myjeeva,DC=com
     */
    private static String getDomainBase(String base)
    {
        char[] namePair = base.toUpperCase().toCharArray();
        String dn = "DC=";
        for (int i = 0; i < namePair.length; i++) {
            if (namePair[i] == '.') {
                dn += ",DC=" + namePair[++i];
            } else {
                dn += namePair[i];
            }
        }
        return dn;
    }

    /**
     * Closes the LDAP connection.
     */
    @Override
    public void close() throws IOException
    {
        try {
            if (dirContext != null)
                dirContext.close();
            dirContext = null;
        } catch (NamingException ex) {
            // Nothing we can do except log
            logger.error("Couldn't close the ldap connection.", ex);
        }
    }
}
