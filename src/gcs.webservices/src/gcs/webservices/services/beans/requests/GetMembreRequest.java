package gcs.webservices.services.beans.requests;

import javax.ws.rs.QueryParam;

public class GetMembreRequest extends AuthenticatedRequest
{
    @QueryParam(value = "membreId")
    private Integer membreId;

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
}
