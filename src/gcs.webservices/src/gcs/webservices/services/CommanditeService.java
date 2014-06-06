package gcs.webservices.services;

import gcs.webapp.utils.MessageType;
import gcs.webapp.utils.app.security.CrudOperation;
import gcs.webapp.utils.app.security.CrudOperator;
import gcs.webapp.utils.app.security.SecureModule;
import gcs.webservices.client.beans.ContextualSessionToken;
import gcs.webservices.client.requests.commandites.AddRequest;
import gcs.webservices.client.requests.commandites.DeleteRequest;
import gcs.webservices.client.responses.DummyResponse;
import gcs.webservices.dao.ICommanditeDao;
import gcs.webservices.dao.IFournisseurDao;
import gcs.webservices.dao.IItemDao;
import gcs.webservices.models.Club;
import gcs.webservices.models.Commandite;
import gcs.webservices.models.Membre;
import gcs.webservices.models.Suivie;

import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.BeanParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SecureModule(name = "sponsors_module")
@Path("/context/{contextName}/session/{ipv4Address}/{sessionKey}/commandite")
public class CommanditeService extends SecureHttpService
{

    @Autowired
    private ICommanditeDao commanditeDao;
    @Autowired
    private IFournisseurDao fournisseurDao;
    @Autowired
    private IItemDao itemDao;

    @GET
    @CrudOperator(CrudOperation.Read)
    public Response getAll(@BeanParam ContextualSessionToken sessionToken)
    {
        DummyResponse dummyResponse = new DummyResponse();

        dummyResponse.setObject(commanditeDao.getAllCommandite());
        dummyResponse.setSuccess(true);
        dummyResponse.addMessage(MessageType.Information, "Success");

        return completeRequest(dummyResponse);
    }

    @POST
    @CrudOperator(CrudOperation.Create)
    public Response add(@BeanParam ContextualSessionToken sessionToken, AddRequest request)
    {
        DummyResponse dummyResponse = new DummyResponse();
        Integer commanditeId = -1;
        Commandite commandite = new Commandite();

        commandite.setFournisseur(fournisseurDao.getFournisseurById(request.getIdFournisseur()));
        commandite.setItem(itemDao.getItemById(request.getIdItem()));
        commandite.setNature(request.getNature());
        commandite.setValeur(request.getValeur());

        /* Création du suivie de départ */
        Suivie suivie = new Suivie();
        suivie.setCommentaire("Création initiale");
        suivie.setDateSuivie(new Date());
        suivie.setSuivieStatut(commanditeDao.getSuivieStatutsById(1));

        super.sessionCache.withSession(sessionToken, session -> {
            Membre membre = session.getMembre();
            Club club = session.getMembre().getClubByName(sessionToken.getContext().getName());

            commandite.setClub(club);
            suivie.setMembreId(membre.getId());
        });

        commanditeId = commanditeDao.addCommandite(commandite);
        suivie.setCommanditeId(commanditeId);

        commanditeDao.addSuivie(suivie);

        // dummyResponse.setObject();
        dummyResponse.setSuccess(true);
        dummyResponse.addMessage(MessageType.Information, "Success");

        return completeRequest(dummyResponse);
    }

    @DELETE
    @CrudOperator(CrudOperation.Delete)
    public Response delete(@BeanParam ContextualSessionToken sessionToken, DeleteRequest request)
    {
        DummyResponse dummyResponse = new DummyResponse();
        ArrayList<Commandite> commandites = (ArrayList<Commandite>) commanditeDao.getAllCommandite();

        for (Commandite commandite : commandites) {
            if (commandite.getId() == request.getId()) {
                // TODO
                break;
            }
        }
        // TODO

        // dummyResponse.setObject();
        dummyResponse.setSuccess(true);
        dummyResponse.addMessage(MessageType.Information, "Success");

        return completeRequest(dummyResponse);
    }

    /**
     * @return the commanditeDao
     */
    public ICommanditeDao getCommanditeDao()
    {
        return commanditeDao;
    }

    /**
     * @param commanditeDao the commanditeDao to set
     */
    public void setCommanditeDao(ICommanditeDao commanditeDao)
    {
        this.commanditeDao = commanditeDao;
    }
}
