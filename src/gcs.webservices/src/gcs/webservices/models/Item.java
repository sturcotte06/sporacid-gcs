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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "items", schema = "public")
@SequenceGenerator(name = "items_id_seq", sequenceName = "items_id_seq", allocationSize = 1)
public class Item extends AbstractModelObject implements Serializable
{
    /** */
    private static final long serialVersionUID = -5120041680296751894L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "items_id_seq")
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "units_id", nullable = false)
    private Unit unit;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Column(name = "code_club", nullable = true, length = 32)
    private String codeClub;

    @Column(name = "qte_courante", nullable = false, precision = 6, scale = 3)
    private Double qtyCourante;

    @Column(name = "qty_min", nullable = true, precision = 6, scale = 3)
    private Double qtyMin;

    @Column(name = "qty_max", nullable = true, precision = 6, scale = 3)
    private Double qtyMax;

    /* @OneToMany(mappedBy="items", targetEntity=Commandite.class)
     * 
     * @Cascade({CascadeType.SAVE_UPDATE, CascadeType.LOCK})
     * 
     * @LazyCollection(LazyCollectionOption.FALSE) private Set<Commandite>
     * commandite = new HashSet<Commandite>(); */

    /* @OneToMany(mappedBy="items", targetEntity=FournisseurItem.class)
     * 
     * @Cascade({CascadeType.SAVE_UPDATE, CascadeType.LOCK})
     * 
     * @LazyCollection(LazyCollectionOption.FALSE) */
    /* @OneToOne
     * 
     * @JoinColumn(name="fournisseurs_items", referencedColumnName="items_id",
     * nullable=false) private FournisseurItem fournisseurItem = new
     * FournisseurItem(); */

    /* @OneToOne(mappedBy="items", targetEntity=Lignetransaction.class,
     * fetch=FetchType.LAZY)
     * 
     * @Cascade({CascadeType.SAVE_UPDATE, CascadeType.LOCK}) private
     * Lignetransaction lignetransaction; */

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
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * @return the codeClub
     */
    public String getCodeClub()
    {
        return codeClub;
    }

    /**
     * @param codeClub the codeClub to set
     */
    public void setCodeClub(String codeClub)
    {
        this.codeClub = codeClub;
    }

    /**
     * @return the qtyCourante
     */
    public Double getQtyCourante()
    {
        return qtyCourante;
    }

    /**
     * @param qtyCourante the qtyCourante to set
     */
    public void setQtyCourante(Double qtyCourante)
    {
        this.qtyCourante = qtyCourante;
    }

    /**
     * @return the qtyMin
     */
    public Double getQtyMin()
    {
        return qtyMin;
    }

    /**
     * @param qtyMin the qtyMin to set
     */
    public void setQtyMin(Double qtyMin)
    {
        this.qtyMin = qtyMin;
    }

    /**
     * @return the qtyMax
     */
    public Double getQtyMax()
    {
        return qtyMax;
    }

    /**
     * @param qtyMax the qtyMax to set
     */
    public void setQtyMax(Double qtyMax)
    {
        this.qtyMax = qtyMax;
    }
}
