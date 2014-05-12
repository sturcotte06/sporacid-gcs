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
@Table(name="lignetransaction", schema="public")
public class Lignetransaction implements Serializable {
	public Lignetransaction() {
	}
	
	@Column(name="id", nullable=false, unique=true)	
	@Id	
	@GeneratedValue(generator="GCS_LIGNETRANSACTION_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="GCS_LIGNETRANSACTION_ID_GENERATOR", strategy="sequence", parameters={ @org.hibernate.annotations.Parameter(name="sequence", value="lignetransaction_id_seq") })	
	private int id;
	
	@ManyToOne(targetEntity=gcs.Transaction.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="transactionid", referencedColumnName="id", nullable=false) })	
	private gcs.Transaction transaction;
	
	@OneToOne(targetEntity=gcs.Items.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="itemsid", nullable=false) })	
	private gcs.Items items;
	
	@Column(name="qty", nullable=false, length=10)	
	private int qty;
	
	@Column(name="prix", nullable=false, length=10)	
	private int prix;
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setQty(int value) {
		this.qty = value;
	}
	
	public int getQty() {
		return qty;
	}
	
	public void setPrix(int value) {
		this.prix = value;
	}
	
	public int getPrix() {
		return prix;
	}
	
	public void setTransaction(gcs.Transaction value) {
		this.transaction = value;
	}
	
	public gcs.Transaction getTransaction() {
		return transaction;
	}
	
	public void setItems(gcs.Items value) {
		this.items = value;
	}
	
	public gcs.Items getItems() {
		return items;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
