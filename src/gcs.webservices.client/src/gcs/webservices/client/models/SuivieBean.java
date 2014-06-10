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

import gcs.webservices.client.models.MembreSuivieBean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SuivieBean extends AbstractBean
{
    /** */
    private static final long serialVersionUID = 3720905955780572417L;

    @NotNull(message = "webservices_suiviebeanmodels_id_notnull")
    private int id;
    
    @NotNull(message = "webservices_suiviebeanmodels_commanditeId_notnull")
    private int commanditeId;

    @NotNull(message = "webservices_suiviebeanmodels_membreSuivieBean_notnull")
    private MembreSuivieBean membreSuivieBean;

    @NotNull(message = "webservices_suiviebeanmodels_suivieStatut_notnull")
    private SuivieStatutBean suivieStatut;

    @NotNull(message = "webservices_suiviebeanmodels_dateSuivie_notnull")
    private Date dateSuivie;

    @NotNull(message = "webservices_suiviebeanmodels_commentaire_notnull")
    @Size(min = 1, max = 255, message = "webservices_suiviebeanmodels_commentaire_size")
    private String commentaire;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCommanditeId() {
		return commanditeId;
	}

	public void setCommanditeId(int commanditeId) {
		this.commanditeId = commanditeId;
	}

	public MembreSuivieBean getMembreSuivieBean() {
		return membreSuivieBean;
	}

	public void setMembreSuivieBean(MembreSuivieBean membreSuivieBean) {
		this.membreSuivieBean = membreSuivieBean;
	}

	public SuivieStatutBean getSuivieStatut() {
		return suivieStatut;
	}

	public void setSuivieStatut(SuivieStatutBean suivieStatut) {
		this.suivieStatut = suivieStatut;
	}

	public Date getDateSuivie() {
		return dateSuivie;
	}

	public void setDateSuivie(Date dateSuivie) {
		this.dateSuivie = dateSuivie;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

}


