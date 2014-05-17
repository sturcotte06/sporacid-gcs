package gcs.webservices.ldap.search;

/**
 * Interface for all ldap searchers.
 * @author Simon Turcotte-Langevin
 */
public interface ILdapSearcher
{
    /**
     * Search the ldap for a user with the given searchValue for the searchBy property.
     * The search will be done in the searchBase domain controllers.
     * @param searchValue The value for the search (example: AJ12345).
     * @param searchBy To ldap what property the value should be mapped to.
     * @return The user with information filled from the ldap.
     */
    public LdapUser searchUser(String searchValue, SearchBy searchBy);
}
