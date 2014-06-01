package gcs.webservices.models;

import gcs.webapp.utils.hibernate.AbstractModelObject;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

@Entity
@Table(name = "audits")
@BatchSize(size = 100)
@SequenceGenerator(name = "audits_id_seq", sequenceName = "audits_id_seq", allocationSize = 1)
public class Audit extends AbstractModelObject implements Serializable
{
    /** */
    private static final long serialVersionUID = 1678770584374182550L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "audits_id_seq")
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "date_audit")
    private Date timestamp;

    @Column(name = "message")
    private String message;

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
     * @return the username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * @return the timestamp
     */
    public Date getTimestamp()
    {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(Date timestamp)
    {
        this.timestamp = timestamp;
    }

    /**
     * @return the message
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message)
    {
        this.message = message;
    }
}
