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
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="membres_formations", schema="public")
@IdClass(Membres_formationsPK.class)
public class Membres_formations implements Serializable {
	public Membres_formations() {
	}
	
	public boolean equals(Object aObj) {
		if (aObj == this)
			return true;
		if (!(aObj instanceof Membres_formations))
			return false;
		Membres_formations membres_formations = (Membres_formations)aObj;
		if (getMembres() == null) {
			if (membres_formations.getMembres() != null)
				return false;
		}
		else if (!getMembres().equals(membres_formations.getMembres()))
			return false;
		if (getFormations() == null) {
			if (membres_formations.getFormations() != null)
				return false;
		}
		else if (!getFormations().equals(membres_formations.getFormations()))
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
	
	@PrimaryKeyJoinColumn	
	@ManyToOne(targetEntity=gcs.Membres.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="membresid", referencedColumnName="id", nullable=false) })	
	private gcs.Membres membres;
	
	@Column(name="membresid", nullable=false, insertable=false, updatable=false)	
	@Id	
	@GeneratedValue(generator="GCS_MEMBRES_FORMATIONS_MEMBRESID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="GCS_MEMBRES_FORMATIONS_MEMBRESID_GENERATOR", strategy="foreign", parameters=@org.hibernate.annotations.Parameter(name="property", value="Id"))	
	private int membresId;
	
	private void setMembresId(int value) {
		this.membresId = value;
	}
	
	public int getMembresId() {
		return membresId;
	}
	
	@PrimaryKeyJoinColumn	
	@ManyToOne(targetEntity=gcs.Formations.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="formationsid", referencedColumnName="id", nullable=false) })	
	private gcs.Formations formations;
	
	@Column(name="formationsid", nullable=false, insertable=false, updatable=false)	
	@Id	
	@GeneratedValue(generator="GCS_MEMBRES_FORMATIONS_FORMATIONSID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="GCS_MEMBRES_FORMATIONS_FORMATIONSID_GENERATOR", strategy="foreign", parameters=@org.hibernate.annotations.Parameter(name="property", value="Id"))	
	private int formationsId;
	
	private void setFormationsId(int value) {
		this.formationsId = value;
	}
	
	public int getFormationsId() {
		return formationsId;
	}
	
	@Column(name="date_suivie", nullable=false, length=7)	
	@Temporal(TemporalType.DATE)	
	private java.util.Date date_suivie;
	
	@Column(name="date_echeance", nullable=true, length=7)	
	@Temporal(TemporalType.DATE)	
	private java.util.Date date_echeance;
	
	public void setDate_suivie(java.util.Date value) {
		this.date_suivie = value;
	}
	
	public java.util.Date getDate_suivie() {
		return date_suivie;
	}
	
	public void setDate_echeance(java.util.Date value) {
		this.date_echeance = value;
	}
	
	public java.util.Date getDate_echeance() {
		return date_echeance;
	}
	
	public void setMembres(gcs.Membres value) {
		this.membres = value;
	}
	
	public gcs.Membres getMembres() {
		return membres;
	}
	
	public void setFormations(gcs.Formations value) {
		this.formations = value;
	}
	
	public gcs.Formations getFormations() {
		return formations;
	}
	
	public String toString() {
		return String.valueOf(((getMembres() == null) ? "" : String.valueOf(getMembres().getORMID())) + " " + ((getFormations() == null) ? "" : String.valueOf(getFormations().getORMID())));
	}
	
	@Transient	
	private boolean _saved = false;
	
	public void onSave() {
		_saved=true;
	}
	
	
	public void onLoad() {
		_saved=true;
	}
	
	
	public boolean isSaved() {
		return _saved;
	}
	
	
}
