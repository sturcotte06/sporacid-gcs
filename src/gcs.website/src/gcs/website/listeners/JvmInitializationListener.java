package gcs.website.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class JvmInitializationListener implements ServletContextListener
{
    @Override
    public void contextDestroyed(ServletContextEvent arg0)
    {
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0)
    {
        System.setProperty("java.net.preferIPv4Stack", "true");
    }
}
