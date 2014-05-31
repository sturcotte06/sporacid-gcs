package gcs.webservices.client.beans;

import javax.ws.rs.PathParam;

import org.hibernate.validator.constraints.NotEmpty;

public class Context
{
    @NotEmpty(message = "beans_context_contextname_notempty")
    @PathParam("contextName")
    private String name;

    public Context()
    {
        super();
    }

    public Context(String name)
    {
        this.name = name;
    }

    /**
     * @return the contextName
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param contextName the contextName to set
     */
    public void setName(String name)
    {
        this.name = name;
    }
}
