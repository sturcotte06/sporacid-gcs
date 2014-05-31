/**
 * 
 */
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
 * @author Patrick Lavallée
 *
 * Model object representing a family link between a member and the person
 */
@Entity
@Table(name = "Liens_Parente")
@SequenceGenerator(name = "liens_parente_id_seq", sequenceName = "liens_parente_id_seq", allocationSize = 1)
public class LienParente extends AbstractModelObject 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "liens_parente_id_seq")
    @Column(name = "id")
	private int id;
	
	@Column(name = "description")
	private String description;

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
	 * @return the description
	 */
	public String getDescription() 
	{
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	
}
