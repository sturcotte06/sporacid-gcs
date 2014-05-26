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
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
@Entity
//@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="items", schema="public")
public class Item implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5120041680296751894L;

	public Item() {
	}
	
	@Column(name="id", nullable=false, unique=true)	
	@Id	
	@GeneratedValue(generator="items_id_seq")	
	@GenericGenerator(name="items_id_seq", strategy="sequence", parameters={ @org.hibernate.annotations.Parameter(name="sequence", value="items_id_seq") })	
	private int id;
	
	/*@OneToOne(targetEntity=Unit.class, fetch=FetchType.LAZY)	
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="units_id", nullable=false) })	
	private Unit unit;*/
	
	@Column(name="description", nullable=false, length=255)	
	private String description;
	
	@Column(name="code_club", nullable=true, length=32)	
	private String codeClub;
	
	@Column(name="qte_courante", nullable=false, precision=6, scale=3)	
	private Double qtyCourante;
	
	@Column(name="qty_min", nullable=true, precision=6, scale=3)	
	private Double qtyMin;
	
	@Column(name="qty_max", nullable=true, precision=6, scale=3)	
	private Double qtyMax;
	
	/*@OneToMany(mappedBy="items", targetEntity=Commandite.class)	
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.LOCK})	
	@LazyCollection(LazyCollectionOption.FALSE)	
	private Set<Commandite> commandite = new HashSet<Commandite>();*/
	
	/*@OneToMany(mappedBy="items", targetEntity=FournisseurItem.class)	
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.LOCK})	
	@LazyCollection(LazyCollectionOption.FALSE)*/
	/*@OneToOne
	@JoinColumn(name="fournisseurs_items", referencedColumnName="items_id", nullable=false)
	private FournisseurItem fournisseurItem = new FournisseurItem();*/
	
	/*@OneToOne(mappedBy="items", targetEntity=Lignetransaction.class, fetch=FetchType.LAZY)	
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.LOCK})	
	private Lignetransaction lignetransaction;*/
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setDescription(String value) {
		this.description = value;
	}
	
	public String getDescription() {
		return description;
	}
	
	
	
	/*public void setUnits(Unit value) {
		this.unit = value;
	}
	
	public Unit getUnits() {
		return unit;
	}*/
	
	/*public void setCommandite(Set<Commandite> value) {
		this.commandite = value;
	}
	
	public Set<Commandite> getCommandite() {
		return commandite;
	}*/
	
	
	/*public void setLignetransaction(Lignetransaction value) {
		this.lignetransaction = value;
	}
	
	public Lignetransaction getLignetransaction() {
		return lignetransaction;
	}*/
	
	public String toString() {
		return String.valueOf(getId());
	}

	public String getCodeClub() {
		return codeClub;
	}

	public void setCodeClub(String codeClub) {
		this.codeClub = codeClub;
	}

	public Double getQtyCourante() {
		return qtyCourante;
	}

	public void setQtyCourante(Double qtyCourante) {
		this.qtyCourante = qtyCourante;
	}

	public Double getQtyMin() {
		return qtyMin;
	}

	public void setQtyMin(Double qtyMin) {
		this.qtyMin = qtyMin;
	}

	public Double getQtyMax() {
		return qtyMax;
	}

	public void setQtyMax(Double qtyMax) {
		this.qtyMax = qtyMax;
	}

	/**
	 * @return the unit
	 */
	/*public Unit getUnit() {
		return unit;
	}*/

	/**
	 * @param unit the unit to set
	 */
	/*public void setUnit(Unit unit) {
		this.unit = unit;
	}*/

	/*public FournisseurItem getFournisseurItem() {
		return fournisseurItem;
	}

	public void setFournisseurItem(FournisseurItem fournisseurItem) {
		this.fournisseurItem = fournisseurItem;
	}*/
	
}
