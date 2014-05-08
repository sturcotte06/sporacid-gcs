package gcs.website.models;

import gcs.webapp.utils.hibernate.AbstractModelObject;
import gcs.website.views.helpers.Display;

public class Membre extends AbstractModelObject
{
    @Display("Id")
    // todo : localize
    private int id;

    @Display("Prénom")
    private String prenom;

    @Display("Nom")
    private String nom;

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
}
