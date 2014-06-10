package gcs.webservices.client.models;

import gcs.webapp.utils.beans.AbstractBean;

import javax.validation.constraints.NotNull;

import org.apache.log4j.Logger;

public class MembreSuivieBean extends AbstractBean
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Log4j logger. */
    private static final Logger logger = Logger.getLogger(MembreSuivieBean.class);

    @NotNull(message = "webservices_membresuiviebeanmodels_id_notnull")
    private int id;

    @NotNull(message = "webservices_membresuiviebeanmodels_nom_notnull")
    private String nom;

    @NotNull(message = "webservices_membresuiviebeanmodels_prenom_notnull")
    private String prenom;

    @NotNull(message = "webservices_membresuiviebeanmodels_courriel_notnull")
    private String courriel;

    @NotNull(message = "webservices_membresuiviebeanmodels_codeUniversel_notnull")
    private String codeUniversel;

    @NotNull(message = "webservices_membresuiviebeanmodels_actif_notnull")
    private boolean actif;

    @NotNull(message = "webservices_membresuiviebeanmodels_telephone_notnull")
    private String telephone;

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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getCourriel() {
		return courriel;
	}

	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}

	public String getCodeUniversel() {
		return codeUniversel;
	}

	public void setCodeUniversel(String codeUniversel) {
		this.codeUniversel = codeUniversel;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public static Logger getLogger() {
		return logger;
	}

}
