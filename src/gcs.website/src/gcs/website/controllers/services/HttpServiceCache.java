package gcs.website.controllers.services;

import gcs.webapp.utils.caching.DeepCopyCache;
import gcs.website.controllers.services.beans.requests.Request;
import gcs.website.controllers.services.beans.responses.Response;

public class HttpServiceCache extends DeepCopyCache<Request, Response>
{
	public HttpServiceCache(int validitySecondsSpan)
	{
		super(validitySecondsSpan);
	}
}
