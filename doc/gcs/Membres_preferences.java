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
@Table(name="membres_preferences", schema="public")
public class Membres_preferences implements Serializable {
	public Membres_preferences() {
	}
	
	@Column(name="id", nullable=false, unique=true)	
	@Id	
	@GeneratedValue(generator="GCS_MEMBRES_PREFERENCES_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="GCS_MEMBRES_PREFERENCES_ID_GENERATOR", strategy="sequence", parameters={ @org.hibernate.annotations.Parameter(name="sequence", value="membres_preferences_id_seq") })	
	private int id;
	
	@Column(name="largeur_menu_principal", nullable=true, length=255)	
	private String largeur_menu_principal;
	
	@OneToOne(mappedBy="membres_preferences", targetEntity=gcs.Membres.class, fetch=FetchType.LAZY)	
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
	
	public void setLargeur_menu_principal(String value) {
		this.largeur_menu_principal = value;
	}
	
	public String getLargeur_menu_principal() {
		return largeur_menu_principal;
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
