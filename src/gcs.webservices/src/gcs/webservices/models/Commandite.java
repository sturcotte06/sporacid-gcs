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

import gcs.webapp.utils.hibernate.AbstractModelObject;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
//@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "commandites", schema = "public")
public class Commandite extends AbstractModelObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6205999501552864331L;

	public Commandite() {
	}

	@Column(name = "id", nullable = false, unique = true)
	@Id
	@GeneratedValue(generator = "commandites_id_seq")
	@org.hibernate.annotations.GenericGenerator(name = "commandites_id_seq", strategy = "sequence", parameters = { @org.hibernate.annotations.Parameter(name = "sequence", value = "commandites_id_seq") })
	private int id;

	/*@ManyToOne(targetEntity = Fournisseur.class, fetch = FetchType.LAZY)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.LOCK })
	@JoinColumns({ @JoinColumn(name = "fournisseurid", referencedColumnName = "id", nullable = false) })
	private Fournisseur fournisseur;*/

	/*@ManyToOne(targetEntity = Item.class, fetch = FetchType.LAZY)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.LOCK })
	@JoinColumns({ @JoinColumn(name = "itemsid", referencedColumnName = "id", nullable = false) })
	private Item items;*/

	/*@ManyToOne(targetEntity = Club.class, fetch = FetchType.LAZY)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.LOCK })
	@JoinColumns({ @JoinColumn(name = "clubsid", referencedColumnName = "id", nullable = false) })
	private Club club;*/

	@Column(name = "valeur", nullable = false, precision = 6, scale = 2)
	private Double valeur;

	@Column(name = "nature", nullable = false, length = 64)
	private String nature;

	/*@OneToMany(mappedBy = "commandite", targetEntity = Suivies.class)
	@org.hibernate.annotations.Cascade({
			org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.LOCK })
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)
	//LIST @ORDER BYTIMESTAMP
	private Set suivies = new HashSet();*/

	public void setId(int value) {
		this.id = value;
	}

	public int getId() {
		return id;
	}

	public int getORMID() {
		return getId();
	}

	public void setValeur(double value) {
		this.valeur = value;
	}

	public double getValeur() {
		return valeur;
	}

	public void setNature(String value) {
		this.nature = value;
	}

	public String getNature() {
		return nature;
	}

	/*public void setFournisseur(Fournisseur value) {
		this.fournisseur = value;
	}

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setItems(Item value) {
		this.items = value;
	}

	public Item getItems() {
		return items;
	}

	public void setClubs(Club value) {
		this.club = value;
	}

	public Club getClubs() {
		return club;
	}

	public void setSuivies(Set value) {
		this.suivies = value;
	}

	public Set getSuivies() {
		return suivies;
	}*/

	public String toString() {
		return String.valueOf(getId());
	}

}
