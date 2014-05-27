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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "suivies", schema = "public")
@SequenceGenerator(name = "suivies_id_seq", sequenceName = "suivies_id_seq", allocationSize = 1)
public class Suivie extends AbstractModelObject implements Serializable
{
    /** */
    private static final long serialVersionUID = 3720905955780572417L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "suivies_id_seq")
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "membre_id", referencedColumnName = "id", nullable = false)
    private Membre membre;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commandites_id", referencedColumnName = "id", nullable = false)
    private Commandite commandite;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "suivie_statuts_id", referencedColumnName = "id", nullable = false)
    private SuivieStatut suivieStatut;

    @Column(name = "date_suivie", nullable = false, length = 6)
    // @Temporal(TemporalType.TIMESTAMP)
    private Date dateSuivie;

    @Column(name = "commentaire", nullable = false, length = 255)
    private String commentaire;

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
     * @return the commandite
     */
    public Commandite getCommandite()
    {
        return commandite;
    }

    /**
     * @param commandite the commandite to set
     */
    public void setCommandite(Commandite commandite)
    {
        this.commandite = commandite;
    }

    /**
     * @return the suivieStatut
     */
    public SuivieStatut getSuivieStatut()
    {
        return suivieStatut;
    }

    /**
     * @param suivieStatut the suivieStatut to set
     */
    public void setSuivieStatut(SuivieStatut suivieStatut)
    {
        this.suivieStatut = suivieStatut;
    }

    /**
     * @return the dateSuivie
     */
    public Date getDateSuivie()
    {
        return dateSuivie;
    }

    /**
     * @param dateSuivie the dateSuivie to set
     */
    public void setDateSuivie(Date dateSuivie)
    {
        this.dateSuivie = dateSuivie;
    }

    /**
     * @return the commentaire
     */
    public String getCommentaire()
    {
        return commentaire;
    }

    /**
     * @param commentaire the commentaire to set
     */
    public void setCommentaire(String commentaire)
    {
        this.commentaire = commentaire;
    }
}
