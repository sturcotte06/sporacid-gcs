package gcs.webservices.models;

import gcs.webapp.utils.hibernate.AbstractModelObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


/**
 * 
 * @author Patrick Lavallee
 *
 * Entity object for a contact in case of an emergency
 */
@Entity
@Table(name = "Contacts_Urgence")
@SequenceGenerator(name = "contacts_urgence_id_seq", sequenceName = "contacts_urgence_id_seq", allocationSize = 1)
public class ContactUrgence extends AbstractModelObject 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contacts_urgence_id_seq")
	@Column(name = "id")
	private int id;
	
	@OneToOne
	@Fetch(FetchMode.JOIN)	
	@JoinColumn(name = "liens_parente_id", 
		referencedColumnName = "id",
		nullable = false)
	private LienParente lienParente;
	
	@Column(name="membres_id")
	private int membreId;
	
	@Column(name = "nom")
	private String nom;
	
	@Column(name = "prenom")
	private String prenom;
	
	@Column(name = "telephone")
	private String telephone;

	/**
	 * @return the id
	 */
	public int getId() 
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) 
	{
		this.id = id;
	}

	/**
	 * @return the lienParente
	 */
	public LienParente getLienParente() 
	{
		return lienParente;
	}

	/**
	 * @param lienParente the lienParente to set
	 */
	public void setLienParente(LienParente lienParente) 
	{
		this.lienParente = lienParente;
	}

	/**
	 * @return the nom
	 */
	public String getNom() 
	{
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) 
	{
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() 
	{
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) 
	{
		this.prenom = prenom;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() 
	{
		return telephone;
	}

	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) 
	{
		this.telephone = telephone;
	}
}
