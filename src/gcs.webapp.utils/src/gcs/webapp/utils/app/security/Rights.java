package gcs.webapp.utils.app.security;

import java.util.Collection;

/**
 * @author Simon Turcotte-Langevin
 */
public class Rights
{
    private Collection<CrudOperation> rights;

    public boolean hasRight(CrudOperation operation)
    {
        for (CrudOperation right : rights) {
            if (operation == right) {
                return true;
            }
        }

        return false;
    }

    /**
     * @return the rights
     */
    public Collection<CrudOperation> getRights()
    {
        return rights;
    }

    /**
     * @param rights the rights to set
     */
    public void setRights(Collection<CrudOperation> rights)
    {
        this.rights = rights;
    }
}
