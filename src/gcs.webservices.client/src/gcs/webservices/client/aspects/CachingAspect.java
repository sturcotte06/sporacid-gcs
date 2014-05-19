package gcs.webservices.client.aspects;

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
    
    @Pointcut("within(@gcs.webservices.client.aspects.Cacheable *)")
    public void cacheableType()
    {}

    @Pointcut("execution(@gcs.webservices.client.aspects.Cacheable * *(..))")
    public void cacheableMethod()
    {}

    @Pointcut("execution(public * *(..))")
    public void publicMethod()
    {}
}
