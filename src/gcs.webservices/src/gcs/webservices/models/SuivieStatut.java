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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "suivie_statuts", schema = "public")
@SequenceGenerator(name = "suivie_statuts_id_seq", sequenceName = "suivie_statuts_id_seq", allocationSize = 1)
public class SuivieStatut implements Serializable
{
    /** */
    private static final long serialVersionUID = 2318828983764637833L;

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "suivie_statuts_id_seq")
    private int id;

    @Column(name = "code", nullable = false, length = 32)
    private String code;

    @Column(name = "description", nullable = false, length = 128)
    private String description;

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @return the code
     */
    public String getCode()
    {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code)
    {
        this.code = code;
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
}
