package gcs.webservices.client.models;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import gcs.webapp.utils.Display;
import gcs.webapp.utils.beans.AbstractBean;

public class ConcentrationBean extends AbstractBean
{
    /** */
    private static final long serialVersionUID = -2102816735215371938L;

    private int id;

    @Size(min = 1, max = 10, message = "webservices_clientmodels_concentration_acronyme_size")
    @NotEmpty(message = "webservices_clientmodels_concentration_acronyme_notempty")
    @Display(header = "webservices_clientmodels_concentration_acronyme_display", width = 50)
    private String acronyme;

    @Size(min = 1, max = 150, message = "webservices_clientmodels_concentration_description_size")
    @NotEmpty(message = "webservices_clientmodels_concentration_description_notempty")
//    @Display(header = "webservices_clientmodels_concentration_description_display", width = 50)
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
     * @return the acronyme
     */
    public String getAcronyme()
    {
        return acronyme;
    }

    /**
     * @param acronyme the acronyme to set
     */
    public void setAcronyme(String acronyme)
    {
        this.acronyme = acronyme;
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
