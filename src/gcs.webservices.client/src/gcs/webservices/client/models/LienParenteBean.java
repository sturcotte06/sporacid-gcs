package gcs.webservices.client.models;

import gcs.webapp.utils.beans.AbstractBean;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class LienParenteBean extends AbstractBean
{
    /** */
    private static final long serialVersionUID = -8065057633811880747L;

    private int id;

    @Size(min = 1, max = 50, message = "webservices_clientmodels_lienparente_description_size")
    @NotEmpty(message = "webservices_clientmodels_lienparente_description_notempty")
    private String description;

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
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description)
    {
        this.description = description;
    }
}
