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
@Embeddable
public class Fournisseurs_itemsPK implements Serializable {
	public boolean equals(Object aObj) {
		if (aObj == this)
			return true;
		if (!(aObj instanceof Fournisseurs_itemsPK))
			return false;
		Fournisseurs_itemsPK fournisseurs_itemspk = (Fournisseurs_itemsPK)aObj;
		if (getFournisseur() == null) {
			if (fournisseurs_itemspk.getFournisseur() != null)
				return false;
		}
		else if (!getFournisseur().equals(fournisseurs_itemspk.getFournisseur()))
			return false;
		if (getItems() == null) {
			if (fournisseurs_itemspk.getItems() != null)
				return false;
		}
		else if (!getItems().equals(fournisseurs_itemspk.getItems()))
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
	
	@ManyToOne(targetEntity=gcs.Fournisseur.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="fournisseurid", referencedColumnName="id", nullable=false) })	
	@org.hibernate.annotations.Index(name="fournisseurs_items_fournisseurid_key")	
	private gcs.Fournisseur fournisseur;
	
	public void setFournisseur(GCS.Fournisseur value)  {
		this.fournisseur =  value;
	}
	
	public GCS.Fournisseur getFournisseur()  {
		return this.fournisseur;
	}
	
	@ManyToOne(targetEntity=gcs.Items.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="itemsid", referencedColumnName="id", nullable=false) })	
	@org.hibernate.annotations.Index(name="fournisseurs_items_itemsid_key")	
	private gcs.Items items;
	
	public void setItems(GCS.Items value)  {
		this.items =  value;
	}
	
	public GCS.Items getItems()  {
		return this.items;
	}
	
}
