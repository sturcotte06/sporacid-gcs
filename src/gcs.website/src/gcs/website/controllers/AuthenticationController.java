package gcs.website.controllers;

import gcs.webapp.utils.Message;
import gcs.webapp.utils.MessageType;
import gcs.webapp.utils.app.menus.IMenuProvider;
import gcs.webapp.utils.app.messages.IMessageLocalizer;
import gcs.webservices.client.ISessionServiceClient;
import gcs.webservices.client.exceptions.WebServiceClientException;
import gcs.webservices.client.responses.Response;
import gcs.webservices.client.responses.sessions.CreateResponse;
import gcs.website.utils.SessionUtils;
import gcs.website.views.beans.AuthenticationForm;
import gcs.website.views.beans.WsSession;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Simon Turcotte-Langevin
 */
@Controller
@RequestMapping(value = "/public/**")
public class AuthenticationController extends BaseController
{
    /** */
    @Resource(name = "sessionServiceClient")
    private ISessionServiceClient sessionServiceClient;

    /** */
    @Resource(name = "menuProvider")
    private IMenuProvider menuProvider;

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "/connexion", method = RequestMethod.GET)
    public String login(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        String direction = "redirect:/";

        if (SessionUtils.getWsSession(session) == null) {
            request.setAttribute("authenticationForm", new AuthenticationForm());
            direction = "login";
        }

        return direction;
    }

    /**
     * @param form
     * @param result
     * @param request
     * @param referer
     * @return
     */
    @RequestMapping(value = "/connexion", method = RequestMethod.POST)
    public String login(@ModelAttribute @Valid AuthenticationForm form, BindingResult result, HttpServletRequest request)
    {
        HttpSession session = request.getSession();

        if (result.hasErrors()) {
            // Form had errors; tell the user why
            addValidationErrorsToSessionMessages(session, result);

            // Reset the password
            form.setPassword(null);

            // Redirect to the login page so the user can retry
            request.setAttribute("authenticationForm", form);
            return "login";
        }

        // We need the ipv4 address of the user for authentication
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        try {
            // Try to create the session in the web services
            CreateResponse createResponse = sessionServiceClient.create(form.getUsername(), ipAddress,
                    form.getPassword());

            if (createResponse.isSuccess()) {
                // Success; create a new web services session
                WsSession wsSession = new WsSession();
                wsSession.setSessionKey(createResponse.getSessionKey());
                wsSession.setUsername(form.getUsername());
                SessionUtils.setWsSession(session, wsSession);

                // TODO change forRole param
                SessionUtils.setApplicationMenu(session,
                        menuProvider.provideMenu(messageLocalizer.getDefaultLocale(), "capitaine"));

                // Redirect to the default page
                return "redirect:/";
            }

            // Failure, fork to the catch
            throw new WebServiceClientException(createResponse);

        } catch (WebServiceClientException ex) {
            if (ex.hasResponseEntity()) {
                // Failure; tell the user why
                SessionUtils.addMessages(ex.getResponseEntity().getMessages(), session);
            }

            // Redirect to the login page so the user can retry
            request.setAttribute("authenticationForm", form);
            return "login";
        }
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "/deconnexion", method = RequestMethod.GET)
    public String logout(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        WsSession wsSession = SessionUtils.getWsSession(session);

        if (wsSession == null) {
            return "redirect:/";
        }

        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        // Remove the session
        try {
            Response response = sessionServiceClient.invalidate(ipAddress, wsSession.getSessionKey());

            if (response.isSuccess()) {

                // Resets the web services session; No more action can be taken
                // with that token anyway
                SessionUtils.setWsSession(request.getSession(), null);

                // Redirect to the default page
                return "redirect:/";
            }

            // Failure, fork to the catch
            throw new WebServiceClientException(response);

        } catch (WebServiceClientException ex) {
            if (ex.hasResponseEntity()) {
                // Failure; tell the user why
                SessionUtils.addMessages(ex.getResponseEntity().getMessages(), session);
            }

            // Redirect to the default page
            return "redirect:/";
        }
    }

    /**
     * @return the menuProvider
     */
    public IMenuProvider getMenuProvider()
    {
        return menuProvider;
    }

    /**
     * @param menuProvider the menuProvider to set
     */
    public void setMenuProvider(IMenuProvider menuProvider)
    {
        this.menuProvider = menuProvider;
    }

    /**
     * @return the sessionServiceClient
     */
    public ISessionServiceClient getSessionServiceClient()
    {
        return sessionServiceClient;
    }

    /**
     * @param sessionServiceClient the sessionServiceClient to set
     */
    public void setSessionServiceClient(ISessionServiceClient sessionServiceClient)
    {
        this.sessionServiceClient = sessionServiceClient;
    }
}
