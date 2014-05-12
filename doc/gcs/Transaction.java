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
package gcs;

import java.io.Serializable;
import javax.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="`Transaction`", schema="public")
public class Transaction implements Serializable {
	public Transaction() {
	}
	
	@Column(name="id", nullable=false, unique=true)	
	@Id	
	@GeneratedValue(generator="GCS_TRANSACTION_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="GCS_TRANSACTION_ID_GENERATOR", strategy="sequence", parameters={ @org.hibernate.annotations.Parameter(name="sequence", value=""Transaction_id_seq"") })	
	private int id;
	
	@OneToOne(targetEntity=gcs.Fournisseur.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="fournisseurid", nullable=false) })	
	private gcs.Fournisseur fournisseur;
	
	@Column(name="type", nullable=false, length=25)	
	private String type;
	
	@Column(name="date_transaction", nullable=false, length=7)	
	@Temporal(TemporalType.DATE)	
	private java.util.Date date_transaction;
	
	@Column(name="total", nullable=false, precision=6, scale=3)	
	private java.math.BigDecimal total;
	
	@Column(name="recu_preuve_achat", nullable=false)	
	private boolean recu_preuve_achat;
	
	@OneToMany(mappedBy="transaction", targetEntity=gcs.Lignetransaction.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)	
	private java.util.Set lignetransaction = new java.util.HashSet();
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setType(String value) {
		this.type = value;
	}
	
	public String getType() {
		return type;
	}
	
	public void setDate_transaction(java.util.Date value) {
		this.date_transaction = value;
	}
	
	public java.util.Date getDate_transaction() {
		return date_transaction;
	}
	
	public void setTotal(java.math.BigDecimal value) {
		this.total = value;
	}
	
	public java.math.BigDecimal getTotal() {
		return total;
	}
	
	public void setRecu_preuve_achat(boolean value) {
		this.recu_preuve_achat = value;
	}
	
	public boolean getRecu_preuve_achat() {
		return recu_preuve_achat;
	}
	
	public void setFournisseur(gcs.Fournisseur value) {
		this.fournisseur = value;
	}
	
	public gcs.Fournisseur getFournisseur() {
		return fournisseur;
	}
	
	public void setLignetransaction(java.util.Set value) {
		this.lignetransaction = value;
	}
	
	public java.util.Set getLignetransaction() {
		return lignetransaction;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
