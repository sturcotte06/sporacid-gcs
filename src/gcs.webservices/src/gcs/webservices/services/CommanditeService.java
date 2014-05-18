package gcs.webservices.services;

import gcs.webapp.utils.MessageType;
import gcs.webapp.utils.app.security.CrudOperation;
import gcs.webapp.utils.app.security.CrudOperator;
import gcs.webapp.utils.app.security.SecureModule;
import gcs.webservices.client.beans.ContextualSessionToken;
import gcs.webservices.client.responses.DummyResponse;
import gcs.webservices.dao.ICommanditeDao;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SecureModule(name = "sponsors_module")
@Path("/context/{contextName}/session/{ipv4Address}/{sessionKey}")
public class CommanditeService extends SecureHttpService
{
    @Autowired
    private ICommanditeDao commanditeDao;

    @GET
    @Path("/commandite")
    @CrudOperator(CrudOperation.Read)
    public Response getAll(@BeanParam ContextualSessionToken sessionToken)
    {
        DummyResponse dummyResponse = new DummyResponse();

        dummyResponse.setObject(commanditeDao.getAllCommandite());
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
