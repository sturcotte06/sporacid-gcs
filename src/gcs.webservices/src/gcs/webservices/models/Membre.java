package gcs.webservices.models;

import gcs.webapp.utils.exceptions.ArgumentNullException;
import gcs.webapp.utils.exceptions.EntityNotFoundException;
import gcs.webapp.utils.hibernate.AbstractModelObject;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.log4j.Logger;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "Membres")
@SequenceGenerator(name = "membres_id_seq", sequenceName = "membres_id_seq", allocationSize = 1)
public class Membre extends AbstractModelObject
{
    /** Log4j logger. */
    private static final Logger logger = Logger.getLogger(Membre.class);

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "membres_id_seq")
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "concentrations_id", referencedColumnName = "id", nullable = false)
    private Concentration concentration;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "membre")
    @Cascade(CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<MembreClub> clubs;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "membreId")
    @Cascade(CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<ContactUrgence> contactsUrgence;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "membres_allergies", joinColumns = { @JoinColumn(name = "membres_id") }, inverseJoinColumns = { @JoinColumn(name = "allergies_id") })
    private Set<Allergie> allergies;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "courriel")
    private String courriel;

    @Column(name = "code_permanent")
    private String codePermanent;

    @Column(name = "code_universel")
    private String codeUniversel;

    @Column(name = "actif")
    private boolean actif;

    @Column(name = "telephone")
    private String telephone;

    public Club getClubByName(String name)
    {
        if (name == null) {
            throw new ArgumentNullException("name");
        }

        for (MembreClub membreClub : clubs) {
            Club club = membreClub.getClub();

            // TODO Possiblement à remplacer
            if (club != null) {
                String clubName = club.getNom();
                if (name.equalsIgnoreCase(clubName)) {
                    // Found the club requested by the client
                    return membreClub.getClub();
                }
            } else {
                logger.warn(String.format("Couldn't get club for the membre %s. ", id));
            }
        }

        throw new EntityNotFoundException("club", name);
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
     * @return the concentration
     */
    public Concentration getConcentration()
    {
        return concentration;
    }

    /**
     * @param concentration the concentration to set
     */
    public void setConcentration(Concentration concentration)
    {
        this.concentration = concentration;
    }

    /**
     * @return the nom
     */
    public String getNom()
    {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom)
    {
        this.nom = nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom()
    {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    /**
     * @return the courriel
     */
    public String getCourriel()
    {
        return courriel;
    }

    /**
     * @param courriel the courriel to set
     */
    public void setCourriel(String courriel)
    {
        this.courriel = courriel;
    }

    /**
     * @return the codePermanent
     */
    public String getCodePermanent()
    {
        return codePermanent;
    }

    /**
     * @param codePermanent the codePermanent to set
     */
    public void setCodePermanent(String codePermanent)
    {
        this.codePermanent = codePermanent;
    }

    /**
     * @return the actif
     */
    public boolean isActif()
    {
        return actif;
    }

    /**
     * @param actif the actif to set
     */
    public void setActif(boolean actif)
    {
        this.actif = actif;
    }

    /**
     * @return the telephone
     */
    public String isTelephone()
    {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    /**
     * @return the codeUniversel
     */
    public String getCodeUniversel()
    {
        return codeUniversel;
    }

    /**
     * @param codeUniversel the codeUniversel to set
     */
    public void setCodeUniversel(String codeUniversel)
    {
        this.codeUniversel = codeUniversel;
    }

    /**
     * @return the clubs
     */
    public Set<MembreClub> getClubs()
    {
        return clubs;
    }

    /**
     * @param clubs the clubs to set
     */
    public void setClubs(Set<MembreClub> clubs)
    {
        this.clubs = clubs;
    }

    /**
     * @return the contactsUrgence
     */
    public Set<ContactUrgence> getContactsUrgence()
    {
        return contactsUrgence;
    }

    /**
     * @param contactsUrgence the contactsUrgence to set
     */
    public void setContactsUrgence(Set<ContactUrgence> contactsUrgence)
    {
        this.contactsUrgence = contactsUrgence;
    }

    /**
     * @return the allergies
     */
    public Set<Allergie> getAllergies()
    {
        return allergies;
    }

    /**
     * @param allergies the allergies to set
     */
    public void setAllergies(Set<Allergie> allergies)
    {
        this.allergies = allergies;
    }
}
