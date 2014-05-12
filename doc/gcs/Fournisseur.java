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
@Table(name="fournisseur", schema="public")
public class Fournisseur implements Serializable {
	public Fournisseur() {
	}
	
	@Column(name="id", nullable=false, unique=true)	
	@Id	
	@GeneratedValue(generator="GCS_FOURNISSEUR_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="GCS_FOURNISSEUR_ID_GENERATOR", strategy="sequence", parameters={ @org.hibernate.annotations.Parameter(name="sequence", value="fournisseur_id_seq") })	
	private int id;
	
	@ManyToOne(targetEntity=gcs.Adresses.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="adressesid", referencedColumnName="id", nullable=false) })	
	private gcs.Adresses adresses;
	
	@Column(name="nom", nullable=false, length=255)	
	private String nom;
	
	@Column(name="contact", nullable=false, length=64)	
	private String contact;
	
	@Column(name="telephone", nullable=false, length=24)	
	private String telephone;
	
	@Column(name="fax", nullable=true, length=24)	
	private String fax;
	
	@Column(name="courriel", nullable=true, length=255)	
	private String courriel;
	
	@OneToOne(mappedBy="fournisseur", targetEntity=gcs.Transaction.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	private gcs.Transaction transaction;
	
	@OneToMany(mappedBy="fournisseur", targetEntity=gcs.Commandite.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)	
	private java.util.Set commandite = new java.util.HashSet();
	
	@OneToMany(mappedBy="fournisseur", targetEntity=gcs.Fournisseurs_items.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)	
	private java.util.Set fournisseurs_items = new java.util.HashSet();
	
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
	
	public void setContact(String value) {
		this.contact = value;
	}
	
	public String getContact() {
		return contact;
	}
	
	public void setTelephone(String value) {
		this.telephone = value;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public void setFax(String value) {
		this.fax = value;
	}
	
	public String getFax() {
		return fax;
	}
	
	public void setCourriel(String value) {
		this.courriel = value;
	}
	
	public String getCourriel() {
		return courriel;
	}
	
	public void setAdresses(gcs.Adresses value) {
		this.adresses = value;
	}
	
	public gcs.Adresses getAdresses() {
		return adresses;
	}
	
	public void setTransaction(gcs.Transaction value) {
		this.transaction = value;
	}
	
	public gcs.Transaction getTransaction() {
		return transaction;
	}
	
	public void setCommandite(java.util.Set value) {
		this.commandite = value;
	}
	
	public java.util.Set getCommandite() {
		return commandite;
	}
	
	
	public void setFournisseurs_items(java.util.Set value) {
		this.fournisseurs_items = value;
	}
	
	public java.util.Set getFournisseurs_items() {
		return fournisseurs_items;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
