package gcs.webservices.aop;

import lombok.Getter;
import lombok.Setter;
import gcs.webservices.aop.services.IAuditService;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AuditAspect
{
   // private static final Logger logger = Logger.getLogger(AuditAspect.class);
   
   @Getter @Setter
   private IAuditService auditService;
   
   /*@Before("args(gcs.webservices.services.beans.requests.AuthenticatedRequest)")
   public void auditPrivateCall(AuthenticatedRequest request)
   {
      auditService.audit(request.getSessionKey(), "Test");
   }*/
   
//   @Before(value = "execution(* gcs.webservices.services.*(..))")
   public void auditPrivateCall()
   {
      auditService.audit("test", "Test");
   }
}
