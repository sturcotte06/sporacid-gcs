package gcs.webservices.client.aspects;

import gcs.webapp.utils.caching.DeepCopyCache;
import gcs.webservices.client.responses.Response;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author Simon Turcotte-Langevin
 */
@Aspect
public class CachingAspect
{
    /** Log4j logger. */
    private static final Logger logger = Logger.getLogger(CachingAspect.class);
    
    private final HttpServiceCache httpServiceCache = new HttpServiceCache(300);
    
    @Pointcut("within(@gcs.webservices.client.aspects.Cacheable *)")
    public void cacheableType()
    {}

    @Pointcut("execution(@gcs.webservices.client.aspects.Cacheable * *(..))")
    public void cacheableMethod()
    {}

    @Pointcut("execution(public * *(..))")
    public void publicMethod()
    {}
    
    private class HttpServiceCache extends DeepCopyCache<Object[], Response>
    {
        public HttpServiceCache(int validitySecondsSpan)
        {
            super(validitySecondsSpan);
            // TODO Auto-generated constructor stub
        }
    }
}
