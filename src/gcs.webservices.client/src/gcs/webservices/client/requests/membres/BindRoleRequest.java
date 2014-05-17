package gcs.webservices.client.requests.membres;

import gcs.webservices.client.requests.Request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.PathParam;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Simon Turcotte-Langevin
 */
public class BindRoleRequest extends Request
{
    @NotNull(message = "membres_membreid_notnull")
    @Min(value = 1, message = "membres_membreid_positive")
    @PathParam(value = "membreId")
    private Integer membreId;

    @NotEmpty(message = "membres_rolename_notempty")
    @PathParam(value = "roleName")
    private String roleName;

    public BindRoleRequest()
    {
    }

    public BindRoleRequest(Integer membreId, String roleName)
    {
        this.membreId = membreId;
        this.roleName = roleName;
    }

    /**
     * @return the membreId
     */
    public Integer getMembreId()
    {
        return membreId;
    }

    /**
     * @param membreId the membreId to set
     */
    public void setMembreId(Integer membreId)
    {
        this.membreId = membreId;
    }

    /**
     * @return the roleName
     */
    public String getRoleName()
    {
        return roleName;
    }

    /**
     * @param roleName the roleName to set
     */
    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }
}
