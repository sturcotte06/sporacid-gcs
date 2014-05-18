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
package gcs;

import java.io.Serializable;
import javax.persistence.*;
@Embeddable
public class Membres_formationsPK implements Serializable {
	public boolean equals(Object aObj) {
		if (aObj == this)
			return true;
		if (!(aObj instanceof Membres_formationsPK))
			return false;
		Membres_formationsPK membres_formationspk = (Membres_formationsPK)aObj;
		if (getMembres() == null) {
			if (membres_formationspk.getMembres() != null)
				return false;
		}
		else if (!getMembres().equals(membres_formationspk.getMembres()))
			return false;
		if (getFormations() == null) {
			if (membres_formationspk.getFormations() != null)
				return false;
		}
		else if (!getFormations().equals(membres_formationspk.getFormations()))
			return false;
		return true;
	}
	
	public int hashCode() {
		int hashcode = 0;
		if (getMembres() != null) {
			hashcode = hashcode + (int) getMembres().getORMID();
		}
		if (getFormations() != null) {
			hashcode = hashcode + (int) getFormations().getORMID();
		}
		return hashcode;
	}
	
	@ManyToOne(targetEntity=gcs.Membres.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="membresid", referencedColumnName="id", nullable=false) })	
	private gcs.Membres membres;
	
	public void setMembres(GCS.Membres value)  {
		this.membres =  value;
	}
	
	public GCS.Membres getMembres()  {
		return this.membres;
	}
	
	@ManyToOne(targetEntity=gcs.Formations.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="formationsid", referencedColumnName="id", nullable=false) })	
	private gcs.Formations formations;
	
	public void setFormations(GCS.Formations value)  {
		this.formations =  value;
	}
	
	public GCS.Formations getFormations()  {
		return this.formations;
	}
	
}
