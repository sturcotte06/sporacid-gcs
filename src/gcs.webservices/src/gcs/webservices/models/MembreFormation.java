/**
 * 
 */
package gcs.webservices.models;

import gcs.webapp.utils.hibernate.AbstractModelObject;
import gcs.webservices.models.pks.MembreFormationPK;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * @author Patrick Lavallee
 *
 * entity representing the link between a member and a formation
 */
@Entity
@Table(name="membres_formations")
@IdClass(MembreFormationPK.class)
public class MembreFormation extends AbstractModelObject 
{
	
	private int membreId;
	
	private int formationId;
	
	@Column(name = "date_suivie")
	private Date dateSuivie;
	
	@Column(name = "date_echeance")
	private Date dateEcheance;

	/**
	 * @return the membreId
	 */
	public int getMembreId() {
		return membreId;
	}

	/**
	 * @param membreId the membreId to set
	 */
	public void setMembreId(int membreId) {
		this.membreId = membreId;
	}

	/**
	 * @return the formationId
	 */
	public int getFormationId() {
		return formationId;
	}

	/**
	 * @param formationId the formationId to set
	 */
	public void setFormationId(int formationId) {
		this.formationId = formationId;
	}

	/**
	 * @return the dateSuivie
	 */
	public Date getDateSuivie() {
		return dateSuivie;
	}

	/**
	 * @param dateSuivie the dateSuivie to set
	 */
	public void setDateSuivie(Date dateSuivie) {
		this.dateSuivie = dateSuivie;
	}

	/**
	 * @return the dateEcheance
	 */
	public Date getDateEcheance() {
		return dateEcheance;
	}

	/**
	 * @param dateEcheance the dateEcheance to set
	 */
	public void setDateEcheance(Date dateEcheance) {
		this.dateEcheance = dateEcheance;
	}

}
