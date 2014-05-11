package gcs.webservices.services;

import gcs.webapp.utils.app.security.SecureModule;

import org.springframework.stereotype.Component;

@Component
@SecureModule(name = "sponsors_module")
public class CommanditeService extends SecureHttpService
{

}
