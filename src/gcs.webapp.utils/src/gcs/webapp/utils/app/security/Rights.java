package gcs.webapp.utils.app.security;

import java.util.Collection;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class Rights 
{
   @Getter @Setter
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
}
