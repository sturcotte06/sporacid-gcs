package gcs.webservices.aspects.services;

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
    public void audit(String ipAddress, String sessionKey, String message);
}
