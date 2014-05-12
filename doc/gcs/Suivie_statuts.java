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
@Table(name="suivie_statuts", schema="public")
public class Suivie_statuts implements Serializable {
	public Suivie_statuts() {
	}
	
	@Column(name="id", nullable=false, unique=true)	
	@Id	
	@GeneratedValue(generator="GCS_SUIVIE_STATUTS_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="GCS_SUIVIE_STATUTS_ID_GENERATOR", strategy="sequence", parameters={ @org.hibernate.annotations.Parameter(name="sequence", value="suivie_statuts_id_seq") })	
	private int id;
	
	@Column(name="code", nullable=false, length=32)	
	private String code;
	
	@Column(name="description", nullable=false, length=128)	
	private String description;
	
	@OneToMany(mappedBy="suivie_statuts", targetEntity=gcs.Suivies.class)	
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
	
	public void setCode(String value) {
		this.code = value;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setDescription(String value) {
		this.description = value;
	}
	
	public String getDescription() {
		return description;
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
