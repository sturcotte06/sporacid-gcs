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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OrderBy;

@Entity
@Table(name = "commandites", schema = "public")
@SequenceGenerator(name = "commandites_id_seq", sequenceName = "commandites_id_seq", allocationSize = 1)
public class Commandite extends AbstractModelObject implements Serializable
{
    /** */
    private static final long serialVersionUID = 6205999501552864331L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "commandites_id_seq")
    @Column(name = "id")
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fournisseurs_id", referencedColumnName = "id", nullable = true)
    private Fournisseur fournisseur;

    @OneToOne(fetch = FetchType.EAGER)
    @Cascade({ CascadeType.ALL })
    @JoinColumn(name = "items_id", referencedColumnName = "id", nullable = true)
    private Item item;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clubs_id", referencedColumnName = "id", nullable = false)
    private Club club;

    @Column(name = "valeur", nullable = false, precision = 6, scale = 2)
    private Double valeur;

    @Column(name = "nature", nullable = false, length = 64)
    private String nature;

    @OneToMany(mappedBy = "commandite", fetch = FetchType.EAGER)
    @Cascade({ CascadeType.ALL })
    @OrderBy(clause = "date_suivie")
    private Set<Suivie> suivies = new HashSet<>();

    public int getORMID()
    {
        return getId();
    }

    public String toString()
    {
        return String.valueOf(getId());
    }

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
     * @return the fournisseur
     */
    public Fournisseur getFournisseur()
    {
        return fournisseur;
    }

    /**
     * @param fournisseur the fournisseur to set
     */
    public void setFournisseur(Fournisseur fournisseur)
    {
        this.fournisseur = fournisseur;
    }

    /**
     * @return the items
     */
    public Item getItem()
    {
        return item;
    }

    /**
     * @param items the items to set
     */
    public void setItem(Item items)
    {
        this.item = items;
    }

    /**
     * @return the club
     */
    public Club getClub()
    {
        return club;
    }

    /**
     * @param club the club to set
     */
    public void setClub(Club club)
    {
        this.club = club;
    }

    /**
     * @return the valeur
     */
    public Double getValeur()
    {
        return valeur;
    }

    /**
     * @param valeur the valeur to set
     */
    public void setValeur(Double valeur)
    {
        this.valeur = valeur;
    }

    /**
     * @return the nature
     */
    public String getNature()
    {
        return nature;
    }

    /**
     * @param nature the nature to set
     */
    public void setNature(String nature)
    {
        this.nature = nature;
    }

    /**
     * @return the suivies
     */
    public Set<Suivie> getSuivies()
    {
        return suivies;
    }

    /**
     * @param suivies the suivies to set
     */
    public void setSuivies(Set<Suivie> suivies)
    {
        this.suivies = suivies;
    }
}
