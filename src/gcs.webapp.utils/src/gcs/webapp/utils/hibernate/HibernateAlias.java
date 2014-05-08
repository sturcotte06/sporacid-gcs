package gcs.webapp.utils.hibernate;

/**
 * Structure for hibernate entity alias
 * @author Simon Turcotte-Langevin
 */
public class HibernateAlias
{
	private String propertyPath;
	private String alias;
	
	public HibernateAlias(String propertyPath, String alias)
	{
		this.propertyPath = propertyPath;
		this.alias = alias;
	}

    /**
     * @return the propertyPath
     */
    public String getPropertyPath()
    {
        return propertyPath;
    }

    /**
     * @param propertyPath the propertyPath to set
     */
    public void setPropertyPath(String propertyPath)
    {
        this.propertyPath = propertyPath;
    }

    /**
     * @return the alias
     */
    public String getAlias()
    {
        return alias;
    }

    /**
     * @param alias the alias to set
     */
    public void setAlias(String alias)
    {
        this.alias = alias;
    }
}
