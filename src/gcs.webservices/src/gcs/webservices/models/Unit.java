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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="units", schema="public")
public class Unit implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3227719600945663302L;

	public Unit() {
	}
	
	@Column(name="id", nullable=false, unique=true)	
	@Id	
	@GeneratedValue(generator="units_id_seq")	
	@GenericGenerator(name="units_id_seq", strategy="sequence", parameters={ @org.hibernate.annotations.Parameter(name="sequence", value="units_id_seq") })	
	private int id;
	
	@Column(name="unit_code", nullable=false, length=8)	
	private String unit_code;
	
	@Column(name="systeme", nullable=false, length=12)	
	private String systeme;
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setUnit_code(String value) {
		this.unit_code = value;
	}
	
	public String getUnit_code() {
		return unit_code;
	}
	
	public void setSysteme(String value) {
		this.systeme = value;
	}
	
	public String getSysteme() {
		return systeme;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
