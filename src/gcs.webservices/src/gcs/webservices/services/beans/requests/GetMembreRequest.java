package gcs.webservices.services.beans.requests;

import javax.ws.rs.QueryParam;

import lombok.Getter;
import lombok.Setter;

public class GetMembreRequest extends AuthenticatedRequest
{
	@QueryParam(value = "membreId")
	@Getter @Setter
	private Integer membreId;
}
