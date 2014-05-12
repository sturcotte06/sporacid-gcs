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
@Table(name="fournisseurs_items", schema="public")
@IdClass(Fournisseurs_itemsPK.class)
public class Fournisseurs_items implements Serializable {
	public Fournisseurs_items() {
	}
	
	public boolean equals(Object aObj) {
		if (aObj == this)
			return true;
		if (!(aObj instanceof Fournisseurs_items))
			return false;
		Fournisseurs_items fournisseurs_items = (Fournisseurs_items)aObj;
		if (getFournisseur() == null) {
			if (fournisseurs_items.getFournisseur() != null)
				return false;
		}
		else if (!getFournisseur().equals(fournisseurs_items.getFournisseur()))
			return false;
		if (getItems() == null) {
			if (fournisseurs_items.getItems() != null)
				return false;
		}
		else if (!getItems().equals(fournisseurs_items.getItems()))
			return false;
		return true;
	}
	
	public int hashCode() {
		int hashcode = 0;
		if (getFournisseur() != null) {
			hashcode = hashcode + (int) getFournisseur().getORMID();
		}
		if (getItems() != null) {
			hashcode = hashcode + (int) getItems().getORMID();
		}
		return hashcode;
	}
	
	@PrimaryKeyJoinColumn	
	@ManyToOne(targetEntity=gcs.Fournisseur.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="fournisseurid", referencedColumnName="id", nullable=false) })	
	private gcs.Fournisseur fournisseur;
	
	@Column(name="fournisseurid", nullable=false, insertable=false, updatable=false)	
	@Id	
	@GeneratedValue(generator="GCS_FOURNISSEURS_ITEMS_FOURNISSEURID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="GCS_FOURNISSEURS_ITEMS_FOURNISSEURID_GENERATOR", strategy="foreign", parameters=@org.hibernate.annotations.Parameter(name="property", value="Id"))	
	private int fournisseurId;
	
	private void setFournisseurId(int value) {
		this.fournisseurId = value;
	}
	
	public int getFournisseurId() {
		return fournisseurId;
	}
	
	@PrimaryKeyJoinColumn	
	@ManyToOne(targetEntity=gcs.Items.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="itemsid", referencedColumnName="id", nullable=false) })	
	private gcs.Items items;
	
	@Column(name="itemsid", nullable=false, insertable=false, updatable=false)	
	@Id	
	@GeneratedValue(generator="GCS_FOURNISSEURS_ITEMS_ITEMSID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="GCS_FOURNISSEURS_ITEMS_ITEMSID_GENERATOR", strategy="foreign", parameters=@org.hibernate.annotations.Parameter(name="property", value="Id"))	
	private int itemsId;
	
	private void setItemsId(int value) {
		this.itemsId = value;
	}
	
	public int getItemsId() {
		return itemsId;
	}
	
	@Column(name="code_fournisseur", nullable=false, length=10)	
	private int code_fournisseur;
	
	public void setCode_fournisseur(int value) {
		this.code_fournisseur = value;
	}
	
	public int getCode_fournisseur() {
		return code_fournisseur;
	}
	
	public void setFournisseur(gcs.Fournisseur value) {
		this.fournisseur = value;
	}
	
	public gcs.Fournisseur getFournisseur() {
		return fournisseur;
	}
	
	public void setItems(gcs.Items value) {
		this.items = value;
	}
	
	public gcs.Items getItems() {
		return items;
	}
	
	public String toString() {
		return String.valueOf(((getFournisseur() == null) ? "" : String.valueOf(getFournisseur().getORMID())) + " " + ((getItems() == null) ? "" : String.valueOf(getItems().getORMID())));
	}
	
	@Transient	
	private boolean _saved = false;
	
	public void onSave() {
		_saved=true;
	}
	
	
	public void onLoad() {
		_saved=true;
	}
	
	
	public boolean isSaved() {
		return _saved;
	}
	
	
}
