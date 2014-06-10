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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "suivies", schema = "public")
@SequenceGenerator(name = "suivies_id_seq", sequenceName = "suivies_id_seq", allocationSize = 1)
public class Suivie extends AbstractModelObject implements Serializable
{
    /** */
    private static final long serialVersionUID = 3720905955780572417L;

    /*@EmbeddedId
    private SuiviePK suiviePK;*/
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "suivies_id_seq")
    private int id;
    
    @Column(name = "commandites_id", nullable = false)
    private int commanditeId;

    /*@Column(name = "membres_id", nullable = false)
    private Membre membre;*/

    /*@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "suivies_id_seq")
    private int id;*/

    @OneToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "membres_id", referencedColumnName = "id", nullable = false)
    private Membre membre;
    
    /*@Column(name = "commandites_id", nullable = false)
    private Integer commanditeId;*/

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "suivie_statuts_id", referencedColumnName = "id", nullable = false)
    private SuivieStatut suivieStatut;

    @Column(name = "date_suivie", nullable = false, length = 6)
    // @Temporal(TemporalType.TIMESTAMP)
    private Date dateSuivie;

    @Column(name = "commentaire", nullable = false, length = 255)
    private String commentaire;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCommanditeId() {
		return commanditeId;
	}

	public void setCommanditeId(int commanditeId) {
		this.commanditeId = commanditeId;
	}

	public Membre getMembre() {
		return membre;
	}

	public void setMembre(Membre membre) {
		this.membre = membre;
	}

	/* INNER CLASS FOR PK */
	/*@Embeddable
	public static class SuiviePK implements Serializable 
	{

		public SuiviePK(){}
		
	   	public SuiviePK(Commandite commandite, Membre membre) {
			super();
			this.commandite = commandite;
			this.membre = membre;
		}
	   	
		private static final long serialVersionUID = 1L;
		
	    @OneToOne
	    @JsonIgnore
	    @JoinColumn(name = "commandites_id", referencedColumnName = "id", nullable = false)
	    private Commandite commandite;

	    @OneToOne
	    @JsonIgnore
	    @JoinColumn(name = "membres_id", referencedColumnName = "id", nullable = false)
	    private Membre membre;

		public Commandite getCommandite() {
			return commandite;
		}

		public void setCommandite(Commandite commandite) {
			this.commandite = commandite;
		}

		public Membre getMembre() {
			return membre;
		}

		public void setMembre(Membre membre) {
			this.membre = membre;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((commandite == null) ? 0 : commandite.hashCode());
			result = prime * result
					+ ((membre == null) ? 0 : membre.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			SuiviePK other = (SuiviePK) obj;
			if (commandite == null) {
				if (other.commandite != null)
					return false;
			} else if (!commandite.equals(other.commandite))
				return false;
			if (membre == null) {
				if (other.membre != null)
					return false;
			} else if (!membre.equals(other.membre))
				return false;
			return true;
		}
*/
		
	}


