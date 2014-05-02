package gcs.webapp.utils.aop;

import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class LoggingAspect 
{
   private static final Logger logger = Logger.getLogger(LoggingAspect.class);
   private static final int cExecutionTimeThreshold = 500;
   
   @Around(value = "execution(* gcs..*(..))")
   public Object aroundGcsMethods(ProceedingJoinPoint joinPoint) throws Throwable
   {
      String className = joinPoint.getSignature().getDeclaringTypeName();
      String methodName = ((MethodSignature) joinPoint.getSignature()).getMethod().getName();
      StopWatch watch = new StopWatch();
      
      logger.debug(String.format("Entering %s.%s();", className, methodName));
      try {
         watch.start();
         return joinPoint.proceed();
      } catch (RuntimeException e) {
         logger.error(String.format("Exception in %s.%s();", className, methodName), e);
         throw e;
      } catch (Throwable t) {
         logger.fatal(String.format("Fatal exception in %s.%s();", className, methodName), t);
         throw t;
      } finally {
         watch.stop();
         if (logger.isDebugEnabled()) {
            logger.debug(String.format("Exiting %s.%s(); Total execution time: %dms", 
                  className, methodName, watch.getTime()));
         } else if (watch.getTime() > cExecutionTimeThreshold) {
            // Method took more than 500ms, log a warning
            logger.warn(String.format("Method %s.%s() took more than %dms. Total execution time: %dms",
                  className, methodName, cExecutionTimeThreshold, watch.getTime()));
         }
      }
   }
}
