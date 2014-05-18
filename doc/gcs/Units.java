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
@Table(name="units", schema="public")
public class Units implements Serializable {
	public Units() {
	}
	
	@Column(name="id", nullable=false, unique=true)	
	@Id	
	@GeneratedValue(generator="GCS_UNITS_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="GCS_UNITS_ID_GENERATOR", strategy="sequence", parameters={ @org.hibernate.annotations.Parameter(name="sequence", value="units_id_seq") })	
	private int id;
	
	@Column(name="unit_code", nullable=false, length=8)	
	private String unit_code;
	
	@Column(name="systeme", nullable=false, length=12)	
	private String systeme;
	
	@OneToOne(mappedBy="units", targetEntity=gcs.Items.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	private gcs.Items items;
	
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
	
	public void setItems(gcs.Items value) {
		this.items = value;
	}
	
	public gcs.Items getItems() {
		return items;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
