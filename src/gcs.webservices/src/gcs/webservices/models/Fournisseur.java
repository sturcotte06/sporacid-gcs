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
package gcs.webservices.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
//@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="fournisseurs", schema="public")
public class Fournisseur implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7322592090709689156L;

	public Fournisseur() {
	}
	
	@Column(name="id", nullable=false, unique=true)	
	@Id	
	@GeneratedValue(generator="fournisseurs_id_seq")	
	@GenericGenerator(name="fournisseurs_id_seq", strategy="sequence", parameters={ @Parameter(name="sequence", value="fournisseurs_id_seq") })	
	private int id;
	
	@ManyToOne(targetEntity=Adresse.class, fetch=FetchType.EAGER)	
	@Cascade({CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="adresses_id", referencedColumnName="id", nullable=false) })	
	private Adresse adresse;
	
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
	
	@ManyToMany(fetch=FetchType.EAGER)
	@Cascade({CascadeType.ALL})
	@JoinTable(name = "fournisseurs_items", 
				joinColumns = { @JoinColumn(name = "fournisseurs_id") }, inverseJoinColumns = { @JoinColumn(name = "items_id") })
	private Set<Item> items = new HashSet<Item>();
	
	public int getORMID() {
		return getId();
	}
	
	public String toString() {
		return String.valueOf(getId());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCourriel() {
		return courriel;
	}

	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> item) {
		this.items = item;
	}
		
}
