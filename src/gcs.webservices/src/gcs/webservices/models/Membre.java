package gcs.webservices.models;

public class Membre
{
    private int id;
    private String nom;
    private String codePermanent;

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
}
