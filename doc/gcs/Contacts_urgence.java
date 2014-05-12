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
@Table(name="contacts_urgence", schema="public")
public class Contacts_urgence implements Serializable {
	public Contacts_urgence() {
	}
	
	@Column(name="id", nullable=false, unique=true)	
	@Id	
	@GeneratedValue(generator="GCS_CONTACTS_URGENCE_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="GCS_CONTACTS_URGENCE_ID_GENERATOR", strategy="sequence", parameters={ @org.hibernate.annotations.Parameter(name="sequence", value="contacts_urgence_id_seq") })	
	private int id;
	
	@Column(name="nom", nullable=true, length=64)	
	private String nom;
	
	@Column(name="prenom", nullable=false, length=64)	
	private int prenom;
	
	@Column(name="telephone", nullable=false, length=16)	
	private String telephone;
	
	@ManyToMany(mappedBy="contacts_urgence", targetEntity=gcs.Membres.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)	
	private java.util.Set membres = new java.util.HashSet();
	
	@OneToOne(mappedBy="contatcts_urgence", targetEntity=gcs.Liens_parente.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	private gcs.Liens_parente liens_parente;
	
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
	
	public void setPrenom(int value) {
		this.prenom = value;
	}
	
	public int getPrenom() {
		return prenom;
	}
	
	public void setTelephone(String value) {
		this.telephone = value;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public void setMembres(java.util.Set value) {
		this.membres = value;
	}
	
	public java.util.Set getMembres() {
		return membres;
	}
	
	
	public void setLiens_parente(gcs.Liens_parente value) {
		this.liens_parente = value;
	}
	
	public gcs.Liens_parente getLiens_parente() {
		return liens_parente;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
