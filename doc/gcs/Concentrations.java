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
@Table(name="concentrations", schema="public")
public class Concentrations implements Serializable {
	public Concentrations() {
	}
	
	@Column(name="id", nullable=false, unique=true)	
	@Id	
	@GeneratedValue(generator="GCS_CONCENTRATIONS_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="GCS_CONCENTRATIONS_ID_GENERATOR", strategy="sequence", parameters={ @org.hibernate.annotations.Parameter(name="sequence", value="concentrations_id_seq") })	
	private int id;
	
	@Column(name="acronyme", nullable=false, length=10)	
	private String acronyme;
	
	@Column(name="description", nullable=true, length=128)	
	private String description;
	
	@OneToOne(mappedBy="concentrations", targetEntity=gcs.Membres.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	private gcs.Membres membres;
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setAcronyme(String value) {
		this.acronyme = value;
	}
	
	public String getAcronyme() {
		return acronyme;
	}
	
	public void setDescription(String value) {
		this.description = value;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setMembres(gcs.Membres value) {
		this.membres = value;
	}
	
	public gcs.Membres getMembres() {
		return membres;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
