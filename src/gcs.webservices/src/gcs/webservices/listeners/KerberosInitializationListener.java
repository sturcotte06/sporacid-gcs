package gcs.webservices.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class KerberosInitializationListener implements ServletContextListener
{
   @Override
   public void contextDestroyed(ServletContextEvent event) { }

   @Override
   public void contextInitialized(ServletContextEvent event) 
   {
      String catalinaBase = System.getProperty("catalina.base");
      
      System.setProperty("java.security.auth.login.config", 
            catalinaBase + "/conf/gcs.webservices.jaas.ets-active-directory.config");
      System.setProperty("java.security.krb5.realm", "ENS.AD.ETSMTL.CA");
      System.setProperty("java.security.krb5.kdc", "ens.ad.etsmtl.ca");
      System.setProperty("gcs.login.context.id", "ActiveDirectoryAuthentication");
   }
}
