package gcs.webservices.models;

import gcs.webapp.utils.hibernate.AbstractModelObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author Patrick Lavallee
 * Entity object for a member's alelrgy
 */
@Entity
@Table(name = "Allergies")
@SequenceGenerator(name = "allergies_id_seq", sequenceName = "allergies_id_seq", allocationSize = 1)
public class Allergie extends AbstractModelObject 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "allergies_id_seq")
	@Column(name = "id")
	private int id;
	
	@Column(name = "description")
	private String description;

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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
