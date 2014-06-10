/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: 
 * License Type: Evaluation
 */
package gcs.webservices.client.models;

import gcs.webapp.utils.beans.AbstractBean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AdresseBean extends AbstractBean
{
    /**  */
    private static final long serialVersionUID = -2014045198601273676L;

    @NotNull(message = "webservices_adressebeanmodels_id_notnull")
    private int id;

    @Size(min = 1, max = 10, message = "webservices_commanditebeanmodels_nature_size")
    @NotNull(message = "webservices_adressebeanmodels_noCivique_notnull")
    private int noCivique;

    @Size(min = 1, max = 64, message = "webservices_commanditebeanmodels_nature_size")
    @NotNull(message = "webservices_adressebeanmodels_rue_notnull")
    private String rue;

    @Size(min = 1, max = 10, message = "webservices_commanditebeanmodels_nature_size")
    private String appartement;

    @Size(min = 1, max = 128, message = "webservices_commanditebeanmodels_nature_size")
    @NotNull(message = "webservices_adressebeanmodels_ville_notnull")
    private String ville;

    @Size(min = 1, max = 16, message = "webservices_commanditebeanmodels_nature_size")
    @NotNull(message = "webservices_adressebeanmodels_codePostal_notnull")
    private String codePostal;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNoCivique() {
		return noCivique;
	}

	public void setNoCivique(int noCivique) {
		this.noCivique = noCivique;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getAppartement() {
		return appartement;
	}

	public void setAppartement(String appartement) {
		this.appartement = appartement;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
}
