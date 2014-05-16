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

import javax.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="items", schema="public")
public class Item implements Serializable {
	public Item() {
	}
	
	@Column(name="id", nullable=false, unique=true)	
	@Id	
	@GeneratedValue(generator="GCS_ITEMS_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="GCS_ITEMS_ID_GENERATOR", strategy="sequence", parameters={ @org.hibernate.annotations.Parameter(name="sequence", value="items_id_seq") })	
	private int id;
	
	@OneToOne(targetEntity=Units.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="unitsid", nullable=false) })	
	private Units units;
	
	@Column(name="description", nullable=false, length=255)	
	private String description;
	
	@Column(name="code_club", nullable=true, length=32)	
	private String code_club;
	
	@Column(name="qte_courante", nullable=false, precision=6, scale=3)	
	private java.math.BigDecimal qte_courante;
	
	@Column(name="qty_min", nullable=true, precision=6, scale=3)	
	private java.math.BigDecimal qty_min;
	
	@Column(name="qty_max", nullable=true, precision=6, scale=3)	
	private java.math.BigDecimal qty_max;
	
	@OneToMany(mappedBy="items", targetEntity=Commandite.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)	
	private Set commandite = new HashSet();
	
	@OneToMany(mappedBy="items", targetEntity=Fournisseurs_items.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)	
	private Set fournisseurs_items = new HashSet();
	
	@OneToOne(mappedBy="items", targetEntity=Lignetransaction.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	private Lignetransaction lignetransaction;
	
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
	
	public void setCode_club(String value) {
		this.code_club = value;
	}
	
	public String getCode_club() {
		return code_club;
	}
	
	public void setQte_courante(java.math.BigDecimal value) {
		this.qte_courante = value;
	}
	
	public java.math.BigDecimal getQte_courante() {
		return qte_courante;
	}
	
	public void setQty_min(java.math.BigDecimal value) {
		this.qty_min = value;
	}
	
	public java.math.BigDecimal getQty_min() {
		return qty_min;
	}
	
	public void setQty_max(java.math.BigDecimal value) {
		this.qty_max = value;
	}
	
	public java.math.BigDecimal getQty_max() {
		return qty_max;
	}
	
	public void setUnits(Units value) {
		this.units = value;
	}
	
	public Units getUnits() {
		return units;
	}
	
	public void setCommandite(Set value) {
		this.commandite = value;
	}
	
	public Set getCommandite() {
		return commandite;
	}
	
	
	public void setFournisseurs_items(Set value) {
		this.fournisseurs_items = value;
	}
	
	public Set getFournisseurs_items() {
		return fournisseurs_items;
	}
	
	
	public void setLignetransaction(Lignetransaction value) {
		this.lignetransaction = value;
	}
	
	public Lignetransaction getLignetransaction() {
		return lignetransaction;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
