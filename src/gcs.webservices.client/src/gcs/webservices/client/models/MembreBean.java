package gcs.webservices.client.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import gcs.webapp.utils.Display;

public class MembreBean extends AbstractBean
{
    /** */
    private static final long serialVersionUID = 4140884890312466382L;

    private int id;

    @NotNull(message = "webservices_clientmodels_membre_id_notnull")
    private final ConcentrationBean concentration = new ConcentrationBean();
    
    @Size(min = 1, max = 64, message = "webservices_clientmodels_membre_nom_size")
    @NotEmpty(message = "webservices_clientmodels_membre_nom_notempty")
    @Display("webservices_clientmodels_membre_nom_display")
    private String nom;

    @Size(min = 1, max = 64, message = "webservices_clientmodels_membre_prenom_size")
    @NotEmpty(message = "webservices_clientmodels_membre_prenom_notempty")
    @Display("webservices_clientmodels_membre_prenom_display")
    private String prenom;

    @Size(min = 1, max = 255, message = "webservices_clientmodels_membre_email_size")
    @NotEmpty(message = "webservices_clientmodels_membre_email_notempty")
    @Display("webservices_clientmodels_membre_email_display")
    private String courriel;

    @NotNull(message = "webservices_clientmodels_membre_active_notnull")
    @Display("webservices_clientmodels_membre_active_display")
    private boolean actif;

    /**
     * @return the concentration
     */
    public ConcentrationBean getConcentration()
    {
        return concentration;
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
}
