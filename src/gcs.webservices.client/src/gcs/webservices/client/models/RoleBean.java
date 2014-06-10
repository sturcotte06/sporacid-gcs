package gcs.webservices.client.models;

import javax.validation.constraints.NotNull;

public class RoleBean extends AbstractBean
{
    /** */
    private static final long serialVersionUID = 6723270847970781630L;

    @NotNull(message = "webservices_rolemodels_id_notnull")
    private int id;

    @NotNull(message = "webservices_rolemodels_nom_notnull")
    private String nom;

    @NotNull(message = "webservices_rolemodels_description_notnull")
    private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

   
}
