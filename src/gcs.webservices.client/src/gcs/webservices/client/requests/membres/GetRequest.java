package gcs.webservices.client.requests.membres;

import gcs.webservices.client.requests.Request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.PathParam;

public class GetRequest extends Request
{
    @NotNull(message = "membres_membreid_notnull")
    @Min(value = 1, message = "membres_membreid_positive")
    @PathParam(value = "membreId")
    private Integer membreId;

    public GetRequest()
    {

    }

    public GetRequest(Integer membreId)
    {
        this.membreId = membreId;
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
}
