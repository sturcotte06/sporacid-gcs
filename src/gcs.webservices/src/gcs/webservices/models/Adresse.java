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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
@Entity
//@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="adresses", schema="public")
public class Adresse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2014045198601273676L;

	public Adresse() {
	}
	
	@Column(name="id", nullable=false, unique=true)	
	@Id	
	@GeneratedValue(generator="adresses_id_seq")	
	@GenericGenerator(name="adresses_id_seq", strategy="sequence", parameters={ @org.hibernate.annotations.Parameter(name="sequence", value="adresses_id_seq") })	
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

	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return id;
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
	
	/*public void setFournisseur(Set value) {
		this.fournisseur = value;
	}
	
	public java.util.Set getFournisseur() {
		return fournisseur;
	}*/
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
