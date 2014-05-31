package gcs.webservices.models.pks;

import gcs.webservices.models.ContactUrgence;
import gcs.webservices.models.Membre;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ContactUrgenceMembrePK implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="membres_id", referencedColumnName = "id", nullable = false)
	private Membre membre;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "contacts_urgence_id", referencedColumnName = "id", nullable = false)
	private ContactUrgence contactUrgence;

	/**
	 * @return the membre
	 */
	public Membre getMembre() 
	{
		return membre;
	}

	/**
	 * @param membre the membre to set
	 */
	public void setMembre(Membre membre) 
	{
		this.membre = membre;
	}

	/**
	 * @return the contactUrgence
	 */
	public ContactUrgence getContactUrgence() 
	{
		return contactUrgence;
	}

	/**
	 * @param contactUrgence the contactUrgence to set
	 */
	public void setContactUrgence(ContactUrgence contactUrgence) 
	{
		this.contactUrgence = contactUrgence;
	}
}
