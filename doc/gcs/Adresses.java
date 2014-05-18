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
@Table(name="adresses", schema="public")
public class Adresses implements Serializable {
	public Adresses() {
	}
	
	@Column(name="id", nullable=false, unique=true)	
	@Id	
	@GeneratedValue(generator="GCS_ADRESSES_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="GCS_ADRESSES_ID_GENERATOR", strategy="sequence", parameters={ @org.hibernate.annotations.Parameter(name="sequence", value="adresses_id_seq") })	
	private int id;
	
	@Column(name="no_civique", nullable=false, length=10)	
	private int no_civique;
	
	@Column(name="rue", nullable=false, length=64)	
	private String rue;
	
	@Column(name="appartement", nullable=true, length=10)	
	private String appartement;
	
	@Column(name="ville", nullable=false, length=128)	
	private String ville;
	
	@Column(name="code_postal", nullable=false, length=16)	
	private String code_postal;
	
	@OneToMany(mappedBy="adresses", targetEntity=gcs.Fournisseur.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)	
	private java.util.Set fournisseur = new java.util.HashSet();
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setNo_civique(int value) {
		this.no_civique = value;
	}
	
	public int getNo_civique() {
		return no_civique;
	}
	
	public void setRue(String value) {
		this.rue = value;
	}
	
	public String getRue() {
		return rue;
	}
	
	public void setAppartement(String value) {
		this.appartement = value;
	}
	
	public String getAppartement() {
		return appartement;
	}
	
	public void setVille(String value) {
		this.ville = value;
	}
	
	public String getVille() {
		return ville;
	}
	
	public void setCode_postal(String value) {
		this.code_postal = value;
	}
	
	public String getCode_postal() {
		return code_postal;
	}
	
	public void setFournisseur(java.util.Set value) {
		this.fournisseur = value;
	}
	
	public java.util.Set getFournisseur() {
		return fournisseur;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
