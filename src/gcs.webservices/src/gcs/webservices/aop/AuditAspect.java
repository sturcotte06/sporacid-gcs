package gcs.webservices.aop;

import lombok.Getter;
import lombok.Setter;
import gcs.webservices.aop.services.IAuditService;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AuditAspect
{
   private static final Logger logger = Logger.getLogger(AuditAspect.class);
   
   @Getter @Setter
   private IAuditService auditService;
   
   /*@Before("args(gcs.webservices.services.beans.requests.AuthenticatedRequest)")
   public void auditPrivateCall(AuthenticatedRequest request)
   {
      auditService.audit(request.getSessionKey(), "Test");
   }*/
   
   @Around(value = "execution(* gcs.webservices.services.*(..))")
   public void auditPrivateCall()
   {
      auditService.audit("test", "Test");
   }
}
