/**
 * 
 */
package gcs.webservices.client.models;

import javax.validation.constraints.NotNull;

import gcs.webapp.utils.hibernate.AbstractModelObject;

public class LienParenteBean extends AbstractBean
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "webservices_lienparentemodels_id_notnull")
	private int id;
	
	@NotNull(message = "webservices_lienparentemodels_description_notnull")
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
