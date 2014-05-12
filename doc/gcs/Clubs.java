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
@Table(name="clubs", schema="public")
public class Clubs implements Serializable {
	public Clubs() {
	}
	
	@Column(name="id", nullable=false, unique=true)	
	@Id	
	@GeneratedValue(generator="GCS_CLUBS_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="GCS_CLUBS_ID_GENERATOR", strategy="sequence", parameters={ @org.hibernate.annotations.Parameter(name="sequence", value="clubs_id_seq") })	
	private int id;
	
	@Column(name="nom", nullable=false, length=50)	
	private String nom;
	
	@Column(name="description", nullable=true, length=500)	
	private Integer description;
	
	@OneToMany(mappedBy="clubs", targetEntity=gcs.Commandite.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)	
	private java.util.Set commandite = new java.util.HashSet();
	
	@OneToMany(mappedBy="clubs", targetEntity=gcs.Evenements.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)	
	private java.util.Set evenements = new java.util.HashSet();
	
	@OneToMany(mappedBy="clubs", targetEntity=gcs.Membres_clubs.class)	
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
	
	public void setNom(String value) {
		this.nom = value;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setDescription(int value) {
		setDescription(new Integer(value));
	}
	
	public void setDescription(Integer value) {
		this.description = value;
	}
	
	public Integer getDescription() {
		return description;
	}
	
	public void setCommandite(java.util.Set value) {
		this.commandite = value;
	}
	
	public java.util.Set getCommandite() {
		return commandite;
	}
	
	
	public void setEvenements(java.util.Set value) {
		this.evenements = value;
	}
	
	public java.util.Set getEvenements() {
		return evenements;
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
