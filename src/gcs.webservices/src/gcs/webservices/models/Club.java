package gcs.webservices.models;

import gcs.webapp.utils.hibernate.AbstractModelObject;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.Entity;

/**
 * 
 * @author Poat
 * @version 1.0
 * 
 */
@Entity
@Table(name = "Clubs")
@SequenceGenerator(name = "clubs_id_seq", sequenceName = "clubs_id_seq", allocationSize = 1)
public class Club extends AbstractModelObject
{
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clubs_id_seq")
    @Column(name = "id")
	private int id;
	
	@Column(name = "nom")
	private String nom;
	
	@Column(name = "description")
	private String description;
}
