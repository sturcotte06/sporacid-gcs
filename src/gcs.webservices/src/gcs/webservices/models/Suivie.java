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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="suivies", schema="public")
@SequenceGenerator(name = "suivies_id_seq", sequenceName = "suivies_id_seq", allocationSize = 1)
public class Suivie implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3720905955780572417L;

	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "suivies_id_seq")
	@Column(name = "id")
	private int id;
	
	/*
	public boolean equals(Object aObj) {
		if (aObj == this)
			return true;
		if (!(aObj instanceof Suivie))
			return false;
		Suivie suivies = (Suivie)aObj;
		if (getCommandite() == null) {
			if (suivies.getCommandite() != null)
				return false;
		}
		else if (!getCommandite().equals(suivies.getCommandite()))
			return false;
		if (getMembre() == null) {
			if (suivies.getMembre() != null)
				return false;
		}
		else if (!getMembre().equals(suivies.getMembre()))
			return false;
		return true;
	}
	
	public int hashCode() {
		int hashcode = 0;
		if (getCommandite() != null) {
			hashcode = hashcode + (int) getCommandite().getORMID();
		}
		if (getMembre() != null) {
			hashcode = hashcode + (int) getMembre().getORMID();
		}
		return hashcode;
	}*/
		
	/*@PrimaryKeyJoinColumn	
	@ManyToOne(targetEntity=Membre.class, fetch=FetchType.LAZY)	
	@Cascade({CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="membresid", referencedColumnName="id", nullable=false) })	
	private Membre membres;*/
		
	@OneToOne(fetch=FetchType.LAZY)	
	@JoinColumn(name="commandites_id", referencedColumnName="id", nullable=false)
	private Commandite commandite;
	
	@ManyToOne(fetch=FetchType.LAZY)	
	@JoinColumn(name="suivie_statuts_id", referencedColumnName="id", nullable=false)
	private SuivieStatut suivieStatut;
	
	@Column(name="date_suivie", nullable=false, length=6)	
	private Date dateSuivie;
	
	@Column(name="commentaire", nullable=false, length=255)	
	private String commentaire;
	
	/*public String toString() {
		return String.valueOf(((getCommandite() == null) ? "" : String.valueOf(getCommandite().getORMID())) + " " + ((getMembre() == null) ? "" : String.valueOf(getMembre().getORMID())));
	}*/

	public Date getDateSuivie() {
		return dateSuivie;
	}

	public void setDateSuivie(Date dateSuivie) {
		this.dateSuivie = dateSuivie;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public SuivieStatut getSuivieStatut() {
		return suivieStatut;
	}

	public void setSuivieStatut(SuivieStatut suivieStatut) {
		this.suivieStatut = suivieStatut;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Commandite getCommandite() {
		return commandite;
	}

	public void setCommandite(Commandite commandite) {
		this.commandite = commandite;
	}

	
	
}
