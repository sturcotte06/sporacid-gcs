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
@Table(name="membres_clubs", schema="public")
public class Membres_clubs implements Serializable {
	public Membres_clubs() {
	}
	
	@Column(name="id", nullable=false, unique=true)	
	@Id	
	@GeneratedValue(generator="GCS_MEMBRES_CLUBS_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="GCS_MEMBRES_CLUBS_ID_GENERATOR", strategy="sequence", parameters={ @org.hibernate.annotations.Parameter(name="sequence", value="membres_clubs_id_seq") })	
	private int id;
	
	@ManyToOne(targetEntity=gcs.Clubs.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="clubsid", referencedColumnName="id", nullable=false) })	
	private gcs.Clubs clubs;
	
	@ManyToOne(targetEntity=gcs.Membres.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="membresid", referencedColumnName="id", nullable=false) })	
	private gcs.Membres membres;
	
	@Column(name="date_debut", nullable=false, length=7)	
	@Temporal(TemporalType.DATE)	
	private java.util.Date date_debut;
	
	@Column(name="date_fin", nullable=true, length=7)	
	@Temporal(TemporalType.DATE)	
	private java.util.Date date_fin;
	
	@ManyToMany(targetEntity=gcs.Roles.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinTable(name="membres_clubs_roles", joinColumns={ @JoinColumn(name="membres_clubsid") }, inverseJoinColumns={ @JoinColumn(name="rolesid") })	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)	
	private java.util.Set roles = new java.util.HashSet();
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setDate_debut(java.util.Date value) {
		this.date_debut = value;
	}
	
	public java.util.Date getDate_debut() {
		return date_debut;
	}
	
	public void setDate_fin(java.util.Date value) {
		this.date_fin = value;
	}
	
	public java.util.Date getDate_fin() {
		return date_fin;
	}
	
	public void setRoles(java.util.Set value) {
		this.roles = value;
	}
	
	public java.util.Set getRoles() {
		return roles;
	}
	
	
	public void setClubs(gcs.Clubs value) {
		this.clubs = value;
	}
	
	public gcs.Clubs getClubs() {
		return clubs;
	}
	
	public void setMembres(gcs.Membres value) {
		this.membres = value;
	}
	
	public gcs.Membres getMembres() {
		return membres;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
