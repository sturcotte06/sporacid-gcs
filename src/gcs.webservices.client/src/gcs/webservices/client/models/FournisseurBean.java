package gcs.webservices.client.models;

import gcs.webapp.utils.beans.AbstractBean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FournisseurBean extends AbstractBean
{
    private static final long serialVersionUID = -7322592090709689156L;

    @NotNull(message = "webservices_fournisseurbeanmodels_id_notnull")
    private int id;

    @NotNull(message = "webservices_fournisseurbeanmodels_adresse_notnull")
    private AdresseBean adresse;

    @Size(min = 1, max = 255, message = "webservices_clientmodels_membre_email_size")
    @NotNull(message = "webservices_fournisseurbeanmodels_nom_notnull")
    private String nom;

    @Size(min = 1, max = 64, message = "webservices_clientmodels_contact_size")
    @NotNull(message = "webservices_clientmodels_contact_notnull")
    private String contact;

    @Size(min = 1, max = 24, message = "webservices_clientmodels_telephone_size")
    @NotNull(message = "webservices_clientmodels_telephone_notnull")
    private String telephone;

    @Size(min = 1, max = 24, message = "webservices_clientmodels_fax_size")
    @NotNull(message = "webservices_clientmodels_fax_notnull")
    private String fax;

    @Size(min = 1, max = 255, message = "webservices_clientmodels_courriel_size")
    @NotNull(message = "webservices_clientmodels_courriel_notnull")
    private String courriel;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public AdresseBean getAdresse()
    {
        return adresse;
    }

    public void setAdresse(AdresseBean adresse)
    {
        this.adresse = adresse;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getContact()
    {
        return contact;
    }

    public void setContact(String contact)
    {
        this.contact = contact;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    public String getFax()
    {
        return fax;
    }

    public void setFax(String fax)
    {
        this.fax = fax;
    }

    public String getCourriel()
    {
        return courriel;
    }

    public void setCourriel(String courriel)
    {
        this.courriel = courriel;
    }
}
