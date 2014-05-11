package gcs.webservices.client;

import gcs.webapp.utils.caching.DeepCopyCache;
import gcs.webservices.client.requests.Request;
import gcs.webservices.client.responses.Response;

public class HttpServiceCache extends DeepCopyCache<Request, Response>
{
	public HttpServiceCache(int validitySecondsSpan)
	{
		super(validitySecondsSpan);
	}
}
