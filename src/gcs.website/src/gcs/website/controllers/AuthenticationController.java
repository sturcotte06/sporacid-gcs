package gcs.website.controllers;

import gcs.webapp.utils.Message;
import gcs.webapp.utils.MessageType;
import gcs.webapp.utils.app.menus.IMenuProvider;
import gcs.webapp.utils.app.messages.IMessageLocalizer;
import gcs.webservices.client.ISessionServiceClient;
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
    public String login(@ModelAttribute @Valid AuthenticationForm form, BindingResult result,
            HttpServletRequest request, @RequestHeader(value = "referer", required = true) final String referer)
    {
        String direction = "redirect:/";
        HttpSession session = request.getSession();

        if (result.hasErrors()) {
            // Form had errors; tell the user why
            for (ObjectError error : result.getAllErrors()) {
                String localizedMsg = messageLocalizer.localizeString(error.getDefaultMessage());
                SessionUtils.addMessage(MessageType.Error, localizedMsg, session);
            }
            // Reset the password
            form.setPassword(null);
            // Return the authentication page
            request.setAttribute("authenticationForm", form);
            direction = "login";
        } else {
            // Try to authenticate the user through the web services
            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();
            }

            CreateResponse response = sessionServiceClient.create(form.getUsername(), ipAddress, form.getPassword());
            if (response.isSuccess()) {
                // Success; create a new web services session
                WsSession wsSession = new WsSession();
                wsSession.setSessionKey(response.getSessionKey());
                wsSession.setUsername(form.getUsername());
                SessionUtils.setWsSession(request.getSession(), wsSession);

                // TODO change forRole param
                SessionUtils.setApplicationMenu(session,
                        menuProvider.provideMenu(messageLocalizer.getDefaultLocale(), "capitaine"));
            } else {
                // Failure; tell the user why
                for (Message message : response.getMessages()) {
                    SessionUtils.addMessage(message.getType(), message.getContent(), session);
                }

                request.setAttribute("authenticationForm", form);
                direction = "login";
            }
        }

        return direction;
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "/deconnexion", method = RequestMethod.GET)
    public String logout(HttpServletRequest request)
    {
        String direction = "redirect:/";
        HttpSession session = request.getSession();
        WsSession wsSession = SessionUtils.getWsSession(session);

        if (wsSession != null) {
            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();
            }

            // Remove the session
            Response response = sessionServiceClient.invalidate(ipAddress, wsSession.getSessionKey());

            if (!response.isSuccess()) {
                // Failure; tell the user why
                for (Message message : response.getMessages()) {
                    SessionUtils.addMessage(message.getType(), message.getContent(), session);
                }
            }

            // Sets the web services session; No more action can be taken
            // with that token anyway
            SessionUtils.setWsSession(request.getSession(), null);
        }

        return direction;
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "/reinitialiser-mot-de-passe", method = RequestMethod.GET)
    public String passwordRetrieval(HttpServletRequest request)
    {
        String direction = "redirect:/";
        // HttpSession session = request.getSession();

        return direction;
    }

    /**
     * @param form
     * @param result
     * @param request
     * @param referer
     * @return
     */
    @RequestMapping(value = "/reinitialiser-mot-de-passe", method = RequestMethod.POST)
    public String passwordRetrieval(@ModelAttribute @Valid AuthenticationForm form, BindingResult result,
            HttpServletRequest request, @RequestHeader(value = "referer", required = true) final String referer)
    {
        String direction = "redirect:/";
        // HttpSession session = request.getSession();

        return direction;
    }

    /**
     * @return the messageLocalizer
     */
    public IMessageLocalizer getMessageLocalizer()
    {
        return messageLocalizer;
    }

    /**
     * @param messageLocalizer the messageLocalizer to set
     */
    public void setMessageLocalizer(IMessageLocalizer messageLocalizer)
    {
        this.messageLocalizer = messageLocalizer;
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

    /**
     * ApplicationContextAware implementation Get specific instances from the
     * application context
     * 
     * @param context The application context
     */
    /* @Override public void setApplicationContext(ApplicationContext context)
     * throws BeansException { authenticationService =
     * context.getBean("authenticationService", IAuthenticationService.class);
     * messageLocalizer = context.getBean("messageLocalizer",
     * IMessageLocalizer.class); menuProvider = context.getBean("menuProvider",
     * IMenuProvider.class); } */
}
