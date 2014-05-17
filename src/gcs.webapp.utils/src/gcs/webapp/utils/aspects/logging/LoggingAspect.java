package gcs.webapp.utils.aspects.logging;

import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author Simon Turcotte-Langevin
 */
@Aspect
public class LoggingAspect
{
    /** Log4j logger. */
    private static final Logger logger = Logger.getLogger(LoggingAspect.class);

    @Pointcut("within(@gcs.webapp.utils.aspects.logging.Loggable *)")
    public void loggableType() {}
    
    @Pointcut("execution(@gcs.webapp.utils.aspects.logging.Loggable * *(..))")
    public void loggableMethod() {}

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {}
    
    @Around("loggableMethod() && @annotation(loggable)")
    public Object aroundAnnotatedMethods(ProceedingJoinPoint joinPoint, Loggable loggable) throws Throwable
    {
        return logThenProceed(joinPoint, loggable);
    }

    @Around("loggableType() && publicMethod()")
    public Object aroundAnnotatedTypes(ProceedingJoinPoint joinPoint) throws Throwable
    {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        Loggable loggable = Class.forName(className).getAnnotation(Loggable.class);
        
        return logThenProceed(joinPoint, loggable);
    }

    /**
     * Logs events before, after and on exceptions for a given join point.
     * 
     * @param joinPoint The aspectj join point.
     * @param loggable The loggable annotation.
     * @return The join point procession.
     * @throws Throwable
     */
    private Object logThenProceed(ProceedingJoinPoint joinPoint, Loggable loggable) throws Throwable
    {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = ((MethodSignature) joinPoint.getSignature()).getMethod().getName();
        StopWatch watch = new StopWatch();

        logger.log(getPriority(loggable.enterLevel()), String.format("Entering %s.%s();", className, methodName));
        try {
            watch.start();
            return joinPoint.proceed();
        } catch (RuntimeException e) {
            logger.error(String.format("Exception in %s.%s();", className, methodName), e);
            throw e;
        } catch (Throwable t) {
            logger.fatal(String.format("Fatal exception of type %s in %s.%s();", t.getClass().getName(), className,
                    methodName), t);
            throw t;
        } finally {
            watch.stop();
            logger.log(getPriority(loggable.exitLevel()), String.format("Exiting %s.%s(); Total execution time: %dms",
                    className, methodName, watch.getTime()));
        }
    }

    /**
     * Does the conversion between the LoggingLevel enum and the Priority static
     * values.
     * 
     * @param level The logging level enum value.
     * @return The Priority static instance.
     */
    private Priority getPriority(LoggingLevel level)
    {
        switch (level) {
            case Debug:
                return Level.DEBUG;
            case Error:
                return Level.ERROR;
            case Fatal:
                return Level.FATAL;
            case Information:
                return Level.INFO;
            case Trace:
                return Level.TRACE;
            case Warning:
                return Level.WARN;
            default:
                return Level.OFF;
        }
    }
}
