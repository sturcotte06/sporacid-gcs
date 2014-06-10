package gcs.webservices.client.models;

import gcs.webapp.utils.beans.AbstractBean;

import javax.validation.constraints.NotNull;

public class ClubBean extends AbstractBean
{
	/** */
    private static final long serialVersionUID = -3844469323637992610L;

    @NotNull(message = "webservices_clubbeanmodels_id_notnull")
	private int id;
	
    @NotNull(message = "webservices_clubbeanmodels_nom_notnull")
	private String nom;
	
    @NotNull(message = "webservices_clubbeanmodels_description_notnull")
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
