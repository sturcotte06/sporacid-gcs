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
@Table(name="membres", schema="public")
public class Membres implements Serializable {
	public Membres() {
	}
	
	@Column(name="id", nullable=false, unique=true)	
	@Id	
	@GeneratedValue(generator="GCS_MEMBRES_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="GCS_MEMBRES_ID_GENERATOR", strategy="sequence", parameters={ @org.hibernate.annotations.Parameter(name="sequence", value="membres_id_seq") })	
	private int id;
	
	@OneToOne(targetEntity=gcs.Concentrations.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="concentrationsid", nullable=false) })	
	private gcs.Concentrations concentrations;
	
	@OneToOne(targetEntity=gcs.Membres_preferences.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="membres_preferencesid") })	
	private gcs.Membres_preferences membres_preferences;
	
	@Column(name="nom", nullable=false, length=64)	
	private String nom;
	
	@Column(name="prenom", nullable=false, length=64)	
	private String prenom;
	
	@Column(name="courriel", nullable=false, length=255)	
	private String courriel;
	
	@Column(name="code_permanent", nullable=false, length=15)	
	private String code_permanent;
	
	@Column(name="actif", nullable=false, length=1)	
	private int actif;
	
	@Column(name="telephone", nullable=true, length=16)	
	private String telephone;
	
	@ManyToMany(targetEntity=gcs.Contacts_urgence.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinTable(name="contacts_urgence_membres", joinColumns={ @JoinColumn(name="membresid") }, inverseJoinColumns={ @JoinColumn(name="contacts_urgenceid") })	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)	
	private java.util.Set contacts_urgence = new java.util.HashSet();
	
	@ManyToMany(mappedBy="membres", targetEntity=gcs.Allergies.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)	
	private java.util.Set allergies = new java.util.HashSet();
	
	@OneToMany(mappedBy="membres", targetEntity=gcs.Membres_clubs.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)	
	private java.util.Set membres_clubs = new java.util.HashSet();
	
	@OneToMany(mappedBy="membres", targetEntity=gcs.Membres_formations.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)	
	private java.util.Set membres_formations = new java.util.HashSet();
	
	@OneToMany(mappedBy="membres", targetEntity=gcs.Suivies.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)	
	private java.util.Set suivies = new java.util.HashSet();
	
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
	
	public void setPrenom(String value) {
		this.prenom = value;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setCourriel(String value) {
		this.courriel = value;
	}
	
	public String getCourriel() {
		return courriel;
	}
	
	public void setCode_permanent(String value) {
		this.code_permanent = value;
	}
	
	public String getCode_permanent() {
		return code_permanent;
	}
	
	public void setActif(int value) {
		this.actif = value;
	}
	
	public int getActif() {
		return actif;
	}
	
	public void setTelephone(String value) {
		this.telephone = value;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public void setContacts_urgence(java.util.Set value) {
		this.contacts_urgence = value;
	}
	
	public java.util.Set getContacts_urgence() {
		return contacts_urgence;
	}
	
	
	public void setConcentrations(gcs.Concentrations value) {
		this.concentrations = value;
	}
	
	public gcs.Concentrations getConcentrations() {
		return concentrations;
	}
	
	public void setMembres_preferences(gcs.Membres_preferences value) {
		this.membres_preferences = value;
	}
	
	public gcs.Membres_preferences getMembres_preferences() {
		return membres_preferences;
	}
	
	public void setAllergies(java.util.Set value) {
		this.allergies = value;
	}
	
	public java.util.Set getAllergies() {
		return allergies;
	}
	
	
	public void setMembres_clubs(java.util.Set value) {
		this.membres_clubs = value;
	}
	
	public java.util.Set getMembres_clubs() {
		return membres_clubs;
	}
	
	
	public void setMembres_formations(java.util.Set value) {
		this.membres_formations = value;
	}
	
	public java.util.Set getMembres_formations() {
		return membres_formations;
	}
	
	
	public void setSuivies(java.util.Set value) {
		this.suivies = value;
	}
	
	public java.util.Set getSuivies() {
		return suivies;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
