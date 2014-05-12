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
@Table(name="evenements", schema="public")
public class Evenements implements Serializable {
	public Evenements() {
	}
	
	@Column(name="id", nullable=false, unique=true)	
	@Id	
	@GeneratedValue(generator="GCS_EVENEMENTS_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="GCS_EVENEMENTS_ID_GENERATOR", strategy="sequence", parameters={ @org.hibernate.annotations.Parameter(name="sequence", value="evenements_id_seq") })	
	private int id;
	
	@ManyToOne(targetEntity=gcs.Clubs.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="clubsid", referencedColumnName="id", nullable=false) })	
	private gcs.Clubs clubs;
	
	@Column(name="nom", nullable=false, length=50)	
	private String nom;
	
	@Column(name="description", nullable=false, length=10)	
	private int description;
	
	@Column(name="date_debut", nullable=false, length=7)	
	@Temporal(TemporalType.DATE)	
	private java.util.Date date_debut;
	
	@Column(name="date_fin", nullable=true, length=7)	
	@Temporal(TemporalType.DATE)	
	private java.util.Date date_fin;
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setNom(String value) {
		this.nom = value;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setDescription(int value) {
		this.description = value;
	}
	
	public int getDescription() {
		return description;
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
	
	public void setClubs(gcs.Clubs value) {
		this.clubs = value;
	}
	
	public gcs.Clubs getClubs() {
		return clubs;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
