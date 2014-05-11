package gcs.webservices.client.beans;

import javax.ws.rs.PathParam;

import org.hibernate.validator.constraints.NotEmpty;

public class Context
{
    @NotEmpty(message = "beans_context_contextname_notempty")
    @PathParam("context.contextName")
    private String contextName;

    public Context()
    {
        super();
    }
    
    public Context(String contextName)
    {
        this.contextName = contextName;
    }

    /**
     * @return the contextName
     */
    public String getContextName()
    {
        return contextName;
    }

    /**
     * @param contextName the contextName to set
     */
    public void setContextName(String contextName)
    {
        this.contextName = contextName;
    }
}
