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
@Table(name="commandite", schema="public")
public class Commandite implements Serializable {
	public Commandite() {
	}
	
	@Column(name="id", nullable=false, unique=true)	
	@Id	
	@GeneratedValue(generator="GCS_COMMANDITE_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="GCS_COMMANDITE_ID_GENERATOR", strategy="sequence", parameters={ @org.hibernate.annotations.Parameter(name="sequence", value="commandite_id_seq") })	
	private int id;
	
	@ManyToOne(targetEntity=gcs.Fournisseur.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="fournisseurid", referencedColumnName="id", nullable=false) })	
	private gcs.Fournisseur fournisseur;
	
	@ManyToOne(targetEntity=gcs.Items.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="itemsid", referencedColumnName="id", nullable=false) })	
	private gcs.Items items;
	
	@ManyToOne(targetEntity=gcs.Clubs.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="clubsid", referencedColumnName="id", nullable=false) })	
	private gcs.Clubs clubs;
	
	@Column(name="valeur", nullable=false, precision=6, scale=2)	
	private java.math.BigDecimal valeur;
	
	@Column(name="nature", nullable=false, length=64)	
	private String nature;
	
	@OneToMany(mappedBy="commandite", targetEntity=gcs.Suivies.class)	
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
	
	public void setValeur(java.math.BigDecimal value) {
		this.valeur = value;
	}
	
	public java.math.BigDecimal getValeur() {
		return valeur;
	}
	
	public void setNature(String value) {
		this.nature = value;
	}
	
	public String getNature() {
		return nature;
	}
	
	public void setFournisseur(gcs.Fournisseur value) {
		this.fournisseur = value;
	}
	
	public gcs.Fournisseur getFournisseur() {
		return fournisseur;
	}
	
	public void setItems(gcs.Items value) {
		this.items = value;
	}
	
	public gcs.Items getItems() {
		return items;
	}
	
	public void setClubs(gcs.Clubs value) {
		this.clubs = value;
	}
	
	public gcs.Clubs getClubs() {
		return clubs;
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
