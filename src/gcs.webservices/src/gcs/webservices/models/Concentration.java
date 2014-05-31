package gcs.webservices.models;

import gcs.webapp.utils.hibernate.AbstractModelObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Concentrations")
@XmlRootElement
@SequenceGenerator(name = "concentrations_id_seq", sequenceName = "concentrations_id_seq", allocationSize = 1)
public class Concentration extends AbstractModelObject
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "concentrations_id_seq")
    @Column(name = "id")
    private int id;

    @Column(name = "acronyme")
    private String acronyme;

    @Column(name = "description")
    private String description;

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
     * @return the acronyme
     */
    public String getAcronyme()
    {
        return acronyme;
    }

    /**
     * @param acronyme the acronyme to set
     */
    public void setAcronyme(String acronyme)
    {
        this.acronyme = acronyme;
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
