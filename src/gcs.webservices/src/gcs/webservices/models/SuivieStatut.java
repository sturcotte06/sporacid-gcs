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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Parameter;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="suivie_statuts", schema="public")
@SequenceGenerator(name = "suivie_statuts_id_seq", sequenceName = "suivie_statuts_id_seq", allocationSize = 1)
public class SuivieStatut implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="id", nullable=false, unique=true)	
	@Id	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "suivie_statuts_id_seq")
	private int id;
	
	@Column(name="code", nullable=false, length=32)	
	private String code;
	
	@Column(name="description", nullable=false, length=128)	
	private String description;
	
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
		
	public String toString() {
		return String.valueOf(getId());
	}
	
}
