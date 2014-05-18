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
@Table(name="formations", schema="public")
public class Formations implements Serializable {
	public Formations() {
	}
	
	@Column(name="id", nullable=false, unique=true)	
	@Id	
	@GeneratedValue(generator="GCS_FORMATIONS_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="GCS_FORMATIONS_ID_GENERATOR", strategy="sequence", parameters={ @org.hibernate.annotations.Parameter(name="sequence", value="formations_id_seq") })	
	private int id;
	
	@Column(name="titre", nullable=true, length=50)	
	private String titre;
	
	@Column(name="description", nullable=true, length=150)	
	private String description;
	
	@OneToMany(mappedBy="formations", targetEntity=gcs.Membres_formations.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)	
	private java.util.Set membres_formations = new java.util.HashSet();
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setTitre(String value) {
		this.titre = value;
	}
	
	public String getTitre() {
		return titre;
	}
	
	public void setDescription(String value) {
		this.description = value;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setMembres_formations(java.util.Set value) {
		this.membres_formations = value;
	}
	
	public java.util.Set getMembres_formations() {
		return membres_formations;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
