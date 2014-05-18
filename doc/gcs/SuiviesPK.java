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
public class SuiviesPK implements Serializable {
	public boolean equals(Object aObj) {
		if (aObj == this)
			return true;
		if (!(aObj instanceof SuiviesPK))
			return false;
		SuiviesPK suiviespk = (SuiviesPK)aObj;
		if (getCommandite() == null) {
			if (suiviespk.getCommandite() != null)
				return false;
		}
		else if (!getCommandite().equals(suiviespk.getCommandite()))
			return false;
		if (getMembres() == null) {
			if (suiviespk.getMembres() != null)
				return false;
		}
		else if (!getMembres().equals(suiviespk.getMembres()))
			return false;
		return true;
	}
	
	public int hashCode() {
		int hashcode = 0;
		if (getCommandite() != null) {
			hashcode = hashcode + (int) getCommandite().getORMID();
		}
		if (getMembres() != null) {
			hashcode = hashcode + (int) getMembres().getORMID();
		}
		return hashcode;
	}
	
	@ManyToOne(targetEntity=gcs.Commandite.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="commanditeid", referencedColumnName="id", nullable=false) })	
	@org.hibernate.annotations.Index(name="suivies_commanditeid_key")	
	private gcs.Commandite commandite;
	
	public void setCommandite(GCS.Commandite value)  {
		this.commandite =  value;
	}
	
	public GCS.Commandite getCommandite()  {
		return this.commandite;
	}
	
	@ManyToOne(targetEntity=gcs.Membres.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="membresid", referencedColumnName="id", nullable=false) })	
	@org.hibernate.annotations.Index(name="suivies_membresid_key")	
	private gcs.Membres membres;
	
	public void setMembres(GCS.Membres value)  {
		this.membres =  value;
	}
	
	public GCS.Membres getMembres()  {
		return this.membres;
	}
	
}
