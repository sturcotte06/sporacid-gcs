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
@Table(name="suivies", schema="public")
@IdClass(SuiviesPK.class)
public class Suivies implements Serializable {
	public Suivies() {
	}
	
	public boolean equals(Object aObj) {
		if (aObj == this)
			return true;
		if (!(aObj instanceof Suivies))
			return false;
		Suivies suivies = (Suivies)aObj;
		if (getCommandite() == null) {
			if (suivies.getCommandite() != null)
				return false;
		}
		else if (!getCommandite().equals(suivies.getCommandite()))
			return false;
		if (getMembres() == null) {
			if (suivies.getMembres() != null)
				return false;
		}
		else if (!getMembres().equals(suivies.getMembres()))
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
	
	@PrimaryKeyJoinColumn	
	@ManyToOne(targetEntity=gcs.Commandite.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="commanditeid", referencedColumnName="id", nullable=false) })	
	private gcs.Commandite commandite;
	
	@Column(name="commanditeid", nullable=false, insertable=false, updatable=false)	
	@Id	
	@GeneratedValue(generator="GCS_SUIVIES_COMMANDITEID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="GCS_SUIVIES_COMMANDITEID_GENERATOR", strategy="foreign", parameters=@org.hibernate.annotations.Parameter(name="property", value="Id"))	
	private int commanditeId;
	
	private void setCommanditeId(int value) {
		this.commanditeId = value;
	}
	
	public int getCommanditeId() {
		return commanditeId;
	}
	
	@PrimaryKeyJoinColumn	
	@ManyToOne(targetEntity=gcs.Membres.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="membresid", referencedColumnName="id", nullable=false) })	
	private gcs.Membres membres;
	
	@Column(name="membresid", nullable=false, insertable=false, updatable=false)	
	@Id	
	@GeneratedValue(generator="GCS_SUIVIES_MEMBRESID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="GCS_SUIVIES_MEMBRESID_GENERATOR", strategy="foreign", parameters=@org.hibernate.annotations.Parameter(name="property", value="Id"))	
	private int membresId;
	
	private void setMembresId(int value) {
		this.membresId = value;
	}
	
	public int getMembresId() {
		return membresId;
	}
	
	@ManyToOne(targetEntity=gcs.Suivie_statuts.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="suivie_statutsid", referencedColumnName="id", nullable=false) })	
	private gcs.Suivie_statuts suivie_statuts;
	
	@Column(name="date_suivie", nullable=false, length=6)	
	private java.sql.Time date_suivie;
	
	@Column(name="commentaire", nullable=false, length=255)	
	private String commentaire;
	
	public void setDate_suivie(java.sql.Time value) {
		this.date_suivie = value;
	}
	
	public java.sql.Time getDate_suivie() {
		return date_suivie;
	}
	
	public void setCommentaire(String value) {
		this.commentaire = value;
	}
	
	public String getCommentaire() {
		return commentaire;
	}
	
	public void setCommandite(gcs.Commandite value) {
		this.commandite = value;
	}
	
	public gcs.Commandite getCommandite() {
		return commandite;
	}
	
	public void setMembres(gcs.Membres value) {
		this.membres = value;
	}
	
	public gcs.Membres getMembres() {
		return membres;
	}
	
	public void setSuivie_statuts(gcs.Suivie_statuts value) {
		this.suivie_statuts = value;
	}
	
	public gcs.Suivie_statuts getSuivie_statuts() {
		return suivie_statuts;
	}
	
	public String toString() {
		return String.valueOf(((getCommandite() == null) ? "" : String.valueOf(getCommandite().getORMID())) + " " + ((getMembres() == null) ? "" : String.valueOf(getMembres().getORMID())));
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
