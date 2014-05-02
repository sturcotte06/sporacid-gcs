package gcs.webservices.aop.services;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import gcs.webservices.dao.IAuditDao;
import gcs.webservices.models.Audit;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class AuditService implements IAuditService
{
   @Getter @Setter
   private IAuditDao auditDao;
   
   @Override
   public void audit(String username, String message)
   {
      Audit audit = new Audit();
      audit.setUsername(username);
      audit.setMessage(message);
      audit.setTimestamp(new Date());
      
      auditDao.addAudit(audit);
   }
}
