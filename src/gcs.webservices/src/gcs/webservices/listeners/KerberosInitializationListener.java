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
		System.setProperty("java.security.krb5.realm", "ENS.AD.ETSMTL.CA");
		System.setProperty("java.security.krb5.kdc", "ens.ad.etsmtl.ca");
		System.setProperty("gcs.login.context.id", "ActiveDirectoryAuthentication");
	}
}
