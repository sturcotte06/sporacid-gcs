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
@Table(name="roles", schema="public")
public class Roles implements Serializable {
	public Roles() {
	}
	
	@Column(name="id", nullable=false, unique=true)	
	@Id	
	@GeneratedValue(generator="GCS_ROLES_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="GCS_ROLES_ID_GENERATOR", strategy="sequence", parameters={ @org.hibernate.annotations.Parameter(name="sequence", value="roles_id_seq") })	
	private int id;
	
	@Column(name="description", nullable=false, length=255)	
	private String description;
	
	@Column(name="nom", nullable=false, length=100)	
	private String nom;
	
	@ManyToMany(mappedBy="roles", targetEntity=gcs.Membres_clubs.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)	
	private java.util.Set membres_clubs = new java.util.HashSet();
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setDescription(String value) {
		this.description = value;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setNom(String value) {
		this.nom = value;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setMembres_clubs(java.util.Set value) {
		this.membres_clubs = value;
	}
	
	public java.util.Set getMembres_clubs() {
		return membres_clubs;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
