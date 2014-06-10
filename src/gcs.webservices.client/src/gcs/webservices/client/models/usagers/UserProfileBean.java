package gcs.webservices.client.models.usagers;

import gcs.webapp.utils.CommonPatterns;
import gcs.webapp.utils.Display;
import gcs.webapp.utils.beans.AbstractBean;
import gcs.webservices.client.models.AllergieBean;
import gcs.webservices.client.models.ConcentrationBean;
import gcs.webservices.client.models.ContactUrgenceBean;

import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class UserProfileBean extends AbstractBean
{
    /** */
    private static final long serialVersionUID = 1295833085694098639L;

    private int id;

    @NotNull(message = "webservices_clientmodels_membre_concentration_notnull")
    private ConcentrationBean concentration;

    @NotNull(message = "webservices_clientmodels_membre_contactsurgence_notnull")
    private Set<ContactUrgenceBean> contactsUrgence;

    @NotNull(message = "webservices_clientmodels_membre_allergies_notnull")
    private Set<AllergieBean> allergies;

    @Size(min = 1, max = 64, message = "webservices_clientmodels_membre_nom_size")
    @NotEmpty(message = "webservices_clientmodels_membre_nom_notempty")
    @Display(header = "webservices_clientmodels_membre_nom_display", width = 175)
    private String nom;

    @Size(min = 1, max = 64, message = "webservices_clientmodels_membre_prenom_size")
    @NotEmpty(message = "webservices_clientmodels_membre_prenom_notempty")
    @Display(header = "webservices_clientmodels_membre_prenom_display", width = 125)
    private String prenom;

    @Size(min = 1, max = 255, message = "webservices_clientmodels_membre_email_size")
    @NotEmpty(message = "webservices_clientmodels_membre_email_notempty")
    @Display(header = "webservices_clientmodels_membre_email_display", width = 175)
    private String courriel;

    @NotEmpty(message = "webservices_clientmodels_membre_codeuniversel_notempty")
    @Pattern(regexp = CommonPatterns.GcsUsername, message = "webservices_clientmodels_membre_codeuniversel_pattern")
    private String codeUniversel;

    @Pattern(regexp = CommonPatterns.PhoneNumber, message = "webservices_clientmodels_membre_telephone_pattern")
    private String telephone;

    @NotNull(message = "webservices_clientmodels_membre_active_notnull")
    @Display(header = "webservices_clientmodels_membre_active_display", width = 30)
    private boolean actif;

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
    public ConcentrationBean getConcentration()
    {
        return concentration;
    }

    /**
     * @param concentration the concentration to set
     */
    public void setConcentration(ConcentrationBean concentration)
    {
        this.concentration = concentration;
    }

    /**
     * @return the contactsUrgence
     */
    public Set<ContactUrgenceBean> getContactsUrgence()
    {
        return contactsUrgence;
    }

    /**
     * @param contactsUrgence the contactsUrgence to set
     */
    public void setContactsUrgence(Set<ContactUrgenceBean> contactsUrgence)
    {
        this.contactsUrgence = contactsUrgence;
    }

    /**
     * @return the allergies
     */
    public Set<AllergieBean> getAllergies()
    {
        return allergies;
    }

    /**
     * @param allergies the allergies to set
     */
    public void setAllergies(Set<AllergieBean> allergies)
    {
        this.allergies = allergies;
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
     * @return the telephone
     */
    public String getTelephone()
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
}
