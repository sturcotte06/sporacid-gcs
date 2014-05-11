package gcs.webservices.services;

import gcs.webapp.utils.app.security.SecureModule;

import org.springframework.stereotype.Component;

@Component
@SecureModule(name = "suppliers_module")
public class FournisseurService extends SecureHttpService
{
    
}
