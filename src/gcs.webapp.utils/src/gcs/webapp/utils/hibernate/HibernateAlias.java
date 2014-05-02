package gcs.webapp.utils.hibernate;

import lombok.Getter;
import lombok.Setter;

/**
 * Structure for hibernate entity alias
 * @author Simon Turcotte-Langevin
 */
public class HibernateAlias
{
   @Getter @Setter
	private String propertyPath;
   
   @Getter @Setter
	private String alias;
	
	public HibernateAlias(String propertyPath, String alias)
	{
		this.propertyPath = propertyPath;
		this.alias = alias;
	}
}
