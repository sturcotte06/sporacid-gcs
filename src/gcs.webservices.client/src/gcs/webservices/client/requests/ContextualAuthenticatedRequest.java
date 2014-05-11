package gcs.webservices.client.requests;

import gcs.webservices.client.beans.Context;
import gcs.webservices.client.beans.SessionToken;

import javax.validation.constraints.NotNull;

import com.sun.jersey.api.core.InjectParam;

/**
 * @author Simon Turcotte-Langevin
 */
public class ContextualAuthenticatedRequest extends AuthenticatedRequest
{
    @NotNull
    @InjectParam
    private Context context;

    public ContextualAuthenticatedRequest()
    {
        super();
    }

    public ContextualAuthenticatedRequest(SessionToken sessionToken, Context context)
    {
        super(sessionToken);
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
