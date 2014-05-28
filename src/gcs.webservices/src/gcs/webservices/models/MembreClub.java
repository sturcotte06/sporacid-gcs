package gcs.webservices.models;

import gcs.webapp.utils.hibernate.AbstractModelObject;

import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "Membres_Clubs")
@SequenceGenerator(name = "membres_clubs_id_seq", sequenceName = "membres_clubs_id_seq", allocationSize = 1)
public class MembreClub extends AbstractModelObject
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "membres_clubs_id_seq")
    @Column(name = "id")
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "clubs_id", referencedColumnName = "id", nullable = false)
    private Club club;

    @OneToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "membres_id", referencedColumnName = "id", nullable = false)
    private Membre membre;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "membres_clubs_roles", joinColumns = { @JoinColumn(name = "membres_clubs_id") }, inverseJoinColumns = { @JoinColumn(name = "roles_id") })
    private Set<Role> roles;

    @Column(name = "date_debut")
    private Date dateDebut;

    @Column(name = "date_fin")
    private Date dateFin;

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
     * @return the membre
     */
    public Membre getMembre()
    {
        return membre;
    }

    /**
     * @param membre the membre to set
     */
    public void setMembre(Membre membre)
    {
        this.membre = membre;
    }

    /**
     * @return the roles
     */
    public Set<Role> getRoles()
    {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(Set<Role> roles)
    {
        this.roles = roles;
    }

    /**
     * @return the dateDebut
     */
    public Date getDateDebut()
    {
        return dateDebut;
    }

    /**
     * @param dateDebut the dateDebut to set
     */
    public void setDateDebut(Date dateDebut)
    {
        this.dateDebut = dateDebut;
    }

    /**
     * @return the dateFin
     */
    public Date getDateFin()
    {
        return dateFin;
    }

    /**
     * @param dateFin the dateFin to set
     */
    public void setDateFin(Date dateFin)
    {
        this.dateFin = dateFin;
    }
}
