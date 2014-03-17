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
	
	public String getPropertyPath()
	{
		return propertyPath;
	}
	
	public void setPropertyPath(String propertyPath)
	{
		this.propertyPath = propertyPath;
	}
	
	public String getAlias()
	{
		return alias;
	}
	
	public void setAlias(String alias)
	{
		this.alias = alias;
	}
}
