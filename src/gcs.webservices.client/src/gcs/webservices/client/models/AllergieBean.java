package gcs.webservices.client.models;

import javax.validation.constraints.NotNull;

public class AllergieBean extends AbstractBean
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "webservices_allergiebeanmodels_id_notnull")
	private int id;
	
	@NotNull(message = "webservices_allergiebeanmodels_description_notnull")
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
