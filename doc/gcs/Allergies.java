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
@Table(name="allergies", schema="public")
public class Allergies implements Serializable {
	public Allergies() {
	}
	
	@Column(name="id", nullable=false, unique=true)	
	@Id	
	@GeneratedValue(generator="GCS_ALLERGIES_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="GCS_ALLERGIES_ID_GENERATOR", strategy="sequence", parameters={ @org.hibernate.annotations.Parameter(name="sequence", value="allergies_id_seq") })	
	private int id;
	
	@Column(name="description", nullable=false, length=128)	
	private String description;
	
	@ManyToMany(targetEntity=gcs.Membres.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinTable(name="membres_allergies", joinColumns={ @JoinColumn(name="allergiesid") }, inverseJoinColumns={ @JoinColumn(name="membresid") })	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)	
	private java.util.Set membres = new java.util.HashSet();
	
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
	
	public void setMembres(java.util.Set value) {
		this.membres = value;
	}
	
	public java.util.Set getMembres() {
		return membres;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
