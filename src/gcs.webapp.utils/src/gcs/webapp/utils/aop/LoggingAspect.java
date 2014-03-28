package gcs.webapp.utils.aop;

import java.lang.reflect.Method;

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
   
   @Around(value = "execution(* gcs.*.*(..))")
   public Object around(ProceedingJoinPoint joinPoint) throws Throwable
   {
      Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
      StopWatch watch = new StopWatch();
      
      watch.start();
      logger.fatal(String.format("Entering %s();", method.getName()));
      try {
         return joinPoint.proceed();
      } catch (RuntimeException e) {
         logger.error(String.format("Exception in %s();", method.getName()), e);
         throw e;
      } catch (Throwable t) {
         logger.fatal(String.format("Fatal exception in %s();", method.getName()), t);
         throw t;
      } finally {
         watch.stop();
         logger.fatal(String.format("Exiting %s(); Total execution time: %ims", 
               method.getName(), watch.getSplitTime() - watch.getStartTime()));
      }
   }
}
