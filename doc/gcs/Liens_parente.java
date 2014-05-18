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
@Table(name="liens_parente", schema="public")
public class Liens_parente implements Serializable {
	public Liens_parente() {
	}
	
	@PrimaryKeyJoinColumn	
	@OneToOne(targetEntity=gcs.Contacts_urgence.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="contatcts_urgenceid", unique=true, nullable=false) })	
	private gcs.Contacts_urgence contatcts_urgence;
	
	@Column(name="contatcts_urgenceid", nullable=false, insertable=false, updatable=false, unique=true)	
	@Id	
	@GeneratedValue(generator="GCS_LIENS_PARENTE_CONTATCTS_URGENCEID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="GCS_LIENS_PARENTE_CONTATCTS_URGENCEID_GENERATOR", strategy="foreign", parameters=@org.hibernate.annotations.Parameter(name="property", value="Id"))	
	private int contatcts_urgenceId;
	
	private void setContatcts_urgenceId(int value) {
		this.contatcts_urgenceId = value;
	}
	
	public int getContatcts_urgenceId() {
		return contatcts_urgenceId;
	}
	
	@Column(name="description", nullable=false, length=32)	
	private String description;
	
	public void setDescription(String value) {
		this.description = value;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setContatcts_urgence(gcs.Contacts_urgence value) {
		this.contatcts_urgence = value;
	}
	
	public gcs.Contacts_urgence getContatcts_urgence() {
		return contatcts_urgence;
	}
	
	public gcs.Contacts_urgence getORMID() {
		return getContatcts_urgence();
	}
	
	public String toString() {
		return String.valueOf(((getContatcts_urgence() == null) ? "" : String.valueOf(getContatcts_urgence().getORMID())));
	}
	
}
