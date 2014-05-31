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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "adresses", schema = "public")
@SequenceGenerator(name = "adresses_id_seq", sequenceName = "adresses_id_seq", allocationSize = 1)
public class Adresse extends AbstractModelObject implements Serializable
{
    /**  */
    private static final long serialVersionUID = -2014045198601273676L;

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adresses_id_seq")
    private int id;

    @Column(name = "no_civique", nullable = false, length = 10)
    private int noCivique;

    @Column(name = "rue", nullable = false, length = 64)
    private String rue;

    @Column(name = "appartement", nullable = true, length = 10)
    private String appartement;

    @Column(name = "ville", nullable = false, length = 128)
    private String ville;

    @Column(name = "code_postal", nullable = false, length = 16)
    private String codePostal;

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @return the noCivique
     */
    public int getNoCivique()
    {
        return noCivique;
    }

    /**
     * @param noCivique the noCivique to set
     */
    public void setNoCivique(int noCivique)
    {
        this.noCivique = noCivique;
    }

    /**
     * @return the rue
     */
    public String getRue()
    {
        return rue;
    }

    /**
     * @param rue the rue to set
     */
    public void setRue(String rue)
    {
        this.rue = rue;
    }

    /**
     * @return the appartement
     */
    public String getAppartement()
    {
        return appartement;
    }

    /**
     * @param appartement the appartement to set
     */
    public void setAppartement(String appartement)
    {
        this.appartement = appartement;
    }

    /**
     * @return the ville
     */
    public String getVille()
    {
        return ville;
    }

    /**
     * @param ville the ville to set
     */
    public void setVille(String ville)
    {
        this.ville = ville;
    }

    /**
     * @return the codePostal
     */
    public String getCodePostal()
    {
        return codePostal;
    }

    /**
     * @param codePostal the codePostal to set
     */
    public void setCodePostal(String codePostal)
    {
        this.codePostal = codePostal;
    }
}
