package gcs.website.controllers.services;

import gcs.webapp.utils.caching.Cache;
import gcs.website.controllers.services.beans.requests.Request;
import gcs.website.controllers.services.beans.responses.Response;

public class HttpServiceCache extends Cache<Request, Response>
{
	public HttpServiceCache(int validitySecondsSpan)
	{
		super(validitySecondsSpan);
	}
}
