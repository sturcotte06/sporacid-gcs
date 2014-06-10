package gcs.webservices.client.models;

import gcs.webapp.utils.CommonPatterns;
import gcs.webapp.utils.Display;
import gcs.webapp.utils.beans.AbstractBean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class ContactUrgenceBean extends AbstractBean
{
    /** */
    private static final long serialVersionUID = 1139468746452385883L;

    private int id;

    @NotNull(message = "webservices_clientmodels_contacturgence_lienparente_notnull")
    private LienParenteBean lienParente;

    @Size(min = 1, max = 50, message = "webservices_clientmodels_contacturgence_nom_size")
    @NotEmpty(message = "webservices_clientmodels_contacturgence_nom_notempty")
    @Display(header = "webservices_clientmodels_contacturgence_nom_display", width = 175)
    private String nom;

    @Size(min = 1, max = 50, message = "webservices_clientmodels_contacturgence_prenom_size")
    @NotEmpty(message = "webservices_clientmodels_contacturgence_prenom_notempty")
    @Display(header = "webservices_clientmodels_contacturgence_prenom_display", width = 125)
    private String prenom;

    @NotEmpty(message = "webservices_clientmodels_contacturgence_telephone_notnull")
    @Pattern(regexp = CommonPatterns.PhoneNumber, message = "webservices_clientmodels_contacturgence_telephone_pattern")
    private String telephone;

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
     * @return the lienParente
     */
    public LienParenteBean getLienParente()
    {
        return lienParente;
    }

    /**
     * @param lienParente the lienParente to set
     */
    public void setLienParente(LienParenteBean lienParente)
    {
        this.lienParente = lienParente;
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
}
