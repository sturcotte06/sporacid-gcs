package gcs.webservices.authentication;

import java.util.Date;

import javax.security.auth.login.LoginContext;

/**
 * @author Simon Turcotte-Langevin
 */
public class LDAPAuthenticationToken
{
    private Date emittedAt;
    private String emittedFor;
    private LoginContext loginContext;

    public LDAPAuthenticationToken(String emittedFor, LoginContext context)
    {
        this.loginContext = context;
        this.emittedFor = emittedFor;
        this.emittedAt = new Date();
    }

    /**
     * @return the emittedAt
     */
    public Date getEmittedAt()
    {
        return emittedAt;
    }

    /**
     * @return the emittedFor
     */
    public String getEmittedFor()
    {
        return emittedFor;
    }

    /**
     * @return the loginContext
     */
    public LoginContext getLoginContext()
    {
        return loginContext;
    }
}
