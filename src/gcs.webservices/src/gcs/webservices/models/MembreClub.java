package gcs.webservices.models;

import gcs.webapp.utils.hibernate.AbstractModelObject;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "Membres_Clubs")
@SequenceGenerator(name = "membres_clubs_id_seq", sequenceName = "membres_clubs_id_seq", allocationSize = 1)
public class MembreClub extends AbstractModelObject 
{
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "membres_clubs_id_seq")
    @Column(name = "id")
	private int id;
	
	@OneToMany
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "clubs_id", 
		referencedColumnName = "id",
		nullable = false)
	private Collection<Club> clubs;
	
	@OneToMany
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "membres_id", 
		referencedColumnName = "id",
		nullable = false)
	private Collection<Membre> membres;
	
	@Column(name="date_debut")
	private Date date_debut;
	
	@Column(name="date_fin")
	private Date date_fin;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the date_debut
	 */
	public Date getDate_debut() {
		return date_debut;
	}

	/**
	 * @param date_debut the date_debut to set
	 */
	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}

	/**
	 * @return the date_fin
	 */
	public Date getDate_fin() {
		return date_fin;
	}

	/**
	 * @param date_fin the date_fin to set
	 */
	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}

	/**
	 * @return the clubs
	 */
	public Collection<Club> getClubs() {
		return clubs;
	}

	/**
	 * @param clubs the clubs to set
	 */
	public void setClubs(Collection<Club> clubs) {
		this.clubs = clubs;
	}

	/**
	 * @return the membres
	 */
	public Collection<Membre> getMembres() {
		return membres;
	}

	/**
	 * @param membres the membres to set
	 */
	public void setMembres(Collection<Membre> membres) {
		this.membres = membres;
	}
}
