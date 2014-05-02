package gcs.webservices.aop.services;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public interface IAuditService
{
   /**
    * 
    * @param audit
    */
   public void audit(String username, String message);
}
