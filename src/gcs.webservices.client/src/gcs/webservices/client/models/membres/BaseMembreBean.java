package gcs.webservices.client.models.membres;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import gcs.webapp.utils.Display;
import gcs.webapp.utils.beans.AbstractBean;
import gcs.webservices.client.models.ConcentrationBean;

public class BaseMembreBean extends AbstractBean
{
    /** */
    private static final long serialVersionUID = 4140884890312466382L;

    private int id;

    @NotNull(message = "webservices_clientmodels_membre_id_notnull")
    private ConcentrationBean concentration;

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
     * @return the actif
     */
    public boolean getActif()
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
}
