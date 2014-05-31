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
@Table(name = "units", schema = "public")
@SequenceGenerator(name = "units_id_seq", sequenceName = "units_id_seq", allocationSize = 1)
public class Unit extends AbstractModelObject implements Serializable
{
    /** */
    private static final long serialVersionUID = -3227719600945663302L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "units_id_seq")
    private int id;

    @Column(name = "unit_code", nullable = false, length = 8)
    private String unitCode;

    @Column(name = "systeme", nullable = false, length = 12)
    private String systeme;

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @return the unitCode
     */
    public String getUnitCode()
    {
        return unitCode;
    }

    /**
     * @param unitCode the unitCode to set
     */
    public void setUnitCode(String unitCode)
    {
        this.unitCode = unitCode;
    }

    /**
     * @return the systeme
     */
    public String getSysteme()
    {
        return systeme;
    }

    /**
     * @param systeme the systeme to set
     */
    public void setSysteme(String systeme)
    {
        this.systeme = systeme;
    }
}
