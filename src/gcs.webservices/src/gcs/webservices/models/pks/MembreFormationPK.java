/**
 * 
 */
package gcs.webservices.models.pks;

import gcs.webservices.models.Formation;
import gcs.webservices.models.Membre;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Patrick Lavallee
 * Embeddable primary key in model object
 */
@Embeddable
public class MembreFormationPK implements Serializable 
{
	private static final long serialVersionUID = 2263916047448841120L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="membres_id", referencedColumnName = "id", nullable = false)
	private Membre membre;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="formations_id", referencedColumnName = "id", nullable = false)
	private Formation formation;

	/**
	 * @return the membre
	 */
	public Membre getMembre() {
		return membre;
	}

	/**
	 * @param membre the membre to set
	 */
	public void setMembre(Membre membre) {
		this.membre = membre;
	}

	/**
	 * @return the formation
	 */
	public Formation getFormation() {
		return formation;
	}

	/**
	 * @param formation the formation to set
	 */
	public void setFormation(Formation formation) {
		this.formation = formation;
	}	
}
