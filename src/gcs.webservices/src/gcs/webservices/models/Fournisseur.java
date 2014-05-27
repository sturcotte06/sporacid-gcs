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
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "fournisseurs", schema = "public")
@SequenceGenerator(name = "fournisseurs_id_seq", sequenceName = "fournisseurs_id_seq", allocationSize = 1)
public class Fournisseur extends AbstractModelObject implements Serializable
{
    /** */
    private static final long serialVersionUID = -7322592090709689156L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fournisseurs_id_seq")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "adresses_id", referencedColumnName = "id", nullable = false)
    private Adresse adresse;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "fournisseurs_items", joinColumns = { @JoinColumn(name = "fournisseurs_id") }, inverseJoinColumns = { @JoinColumn(name = "items_id") })
    private Set<Item> items;

    @Column(name = "nom", nullable = false, length = 255)
    private String nom;

    @Column(name = "contact", nullable = false, length = 64)
    private String contact;

    @Column(name = "telephone", nullable = false, length = 24)
    private String telephone;

    @Column(name = "fax", nullable = true, length = 24)
    private String fax;

    @Column(name = "courriel", nullable = true, length = 255)
    private String courriel;

    public int getORMID()
    {
        return getId();
    }

    public String toString()
    {
        return String.valueOf(getId());
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getContact()
    {
        return contact;
    }

    public void setContact(String contact)
    {
        this.contact = contact;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    public String getFax()
    {
        return fax;
    }

    public void setFax(String fax)
    {
        this.fax = fax;
    }

    public String getCourriel()
    {
        return courriel;
    }

    public void setCourriel(String courriel)
    {
        this.courriel = courriel;
    }

    public Adresse getAdresse()
    {
        return adresse;
    }

    public void setAdresse(Adresse adresse)
    {
        this.adresse = adresse;
    }

    public Set<Item> getItems()
    {
        return items;
    }

    public void setItems(Set<Item> item)
    {
        this.items = item;
    }

}
