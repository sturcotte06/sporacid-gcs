package gcs.webservices.services;

import javax.ws.rs.Path;

import gcs.webapp.utils.app.security.SecureModule;

import org.springframework.stereotype.Component;

@Component
@SecureModule(name = "suppliers_module")
@Path("/context/{contextName}/session/{ipv4Address}/{sessionKey}/fournisseur")
public class FournisseurService extends SecureHttpService
{
    
}
