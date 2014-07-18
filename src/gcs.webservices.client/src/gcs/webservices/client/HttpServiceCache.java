package gcs.webservices.client;

import gcs.webapp.utils.caching.DeepCopyCache;
import gcs.webservices.client.responses.Response;

public class HttpServiceCache extends DeepCopyCache<HttpServiceRoute, Response>
{
	public HttpServiceCache(int validitySecondsSpan)
	{
		super(validitySecondsSpan);
	}
}
