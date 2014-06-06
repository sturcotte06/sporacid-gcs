package gcs.webservices.models;

import java.io.Serializable;
import java.util.Set;

import gcs.webapp.utils.hibernate.AbstractModelObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * 
 * @author Poat
 * @version 1.0
 * 
 */
@Entity
@Table(name = "Clubs")
@XmlRootElement
@SequenceGenerator(name = "clubs_id_seq", sequenceName = "clubs_id_seq", allocationSize = 1)
public class Club extends AbstractModelObject implements Serializable
{
	/** */
    private static final long serialVersionUID = -3844469323637992610L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clubs_id_seq")
    @Column(name = "id")
	private int id;
	
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "club")
    @Cascade(CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<MembreClub> membres;
    
	@Column(name = "nom")
	private String nom;
	
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

    /**
     * @return the membres
     */
    public Set<MembreClub> getMembres()
    {
        return membres;
    }

    /**
     * @param membres the membres to set
     */
    public void setMembres(Set<MembreClub> membres)
    {
        this.membres = membres;
    }
}
