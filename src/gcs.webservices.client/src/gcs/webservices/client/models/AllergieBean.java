package gcs.webservices.client.models;

import gcs.webapp.utils.Display;
import gcs.webapp.utils.beans.AbstractBean;

import javax.validation.constraints.NotNull;

public class AllergieBean extends AbstractBean
{
    /** */
    private static final long serialVersionUID = 1L;

    @NotNull(message = "webservices_allergiebeanmodels_id_notnull")
    @Display(header = "webservices_clientmodels_alergies_id_display", width = 175)
    private int id;

    @NotNull(message = "webservices_allergiebeanmodels_description_notnull")
    @Display(header = "webservices_clientmodels_alergies_desc_display", width = 175)
    private String description;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

}
