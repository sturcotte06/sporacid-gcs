package gcs.webservices.client.beans;

import javax.validation.constraints.NotNull;
import javax.ws.rs.BeanParam;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class ContextualSessionToken extends SessionToken
{
    @NotNull
    @BeanParam
    private Context context;
    
    public ContextualSessionToken()
    {
        super();
    }
    
    public ContextualSessionToken(String sessionKey, String ipv4Address, Context context)
    {
        super(sessionKey, ipv4Address);
        this.context = context;
    }

    /**
     * @return the context
     */
    public Context getContext()
    {
        return context;
    }

    /**
     * @param context the context to set
     */
    public void setContext(Context context)
    {
        this.context = context;
    }
}
