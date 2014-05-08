package gcs.website.utils;

import gcs.webapp.utils.Message;
import gcs.webapp.utils.MessageType;
import gcs.webapp.utils.app.menus.MainMenu;
import gcs.website.views.beans.WsSession;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * @author Simon Turcotte-Langevin
 */
public final class SessionUtils
{
    /**
     * Private constant for the session attribute key to access the list of
     * application messages.
     */
    private static final String cSessionMessagesKey = "applicationMessages";

    /**
     * Private constant for the session attribute key to access the key for this
     * user's identity
     */
    private static final String cSessionWsSessionKey = "webservicesSessionKey";

    /** */
    private static final String cSessionApplicationMenu = "applicationMenu";

    /**
     * Public static method to add a message to the messages list of a request
     * object.
     * 
     * @param message A message to log
     * @param request An http session object
     */
    public static void addMessage(MessageType messageType, String messageContent, HttpSession session)
    {
        Message message = new Message(messageType, messageContent);
        List<Message> listeMessages = getMessages(session);
        listeMessages.add(message);
    }

    /**
     * Public static method to obtain the message list from a request object.
     * 
     * @param request An http session object
     * @return The list of messages for the request object
     */
    public static List<Message> getMessages(HttpSession session)
    {
        @SuppressWarnings("unchecked")
        List<Message> listeMessages = (List<Message>) session.getAttribute(cSessionMessagesKey);

        if (listeMessages == null) {
            listeMessages = new ArrayList<Message>();
            session.setAttribute(cSessionMessagesKey, listeMessages);
        }

        return listeMessages;
    }

    /**
     * @param session An http session object
     */
    public static void clearMessages(HttpSession session)
    {
        getMessages(session).clear();
    }

    /**
     * Static setter for the web services session key
     * 
     * @param session An http session object
     * @param sessionKey The new web services session key
     */
    public static void setWsSession(HttpSession session, WsSession wsSession)
    {
        session.setAttribute(cSessionWsSessionKey, wsSession);
    }

    /**
     * Static getter for the web services session key
     * 
     * @param session An http session object
     * @return The web services session key
     */
    public static WsSession getWsSession(HttpSession session)
    {
        return (WsSession) session.getAttribute(cSessionWsSessionKey);
    }

    /**
     * Static setter for the application menu
     * 
     * @param session An http session object
     * @param sessionKey The new application menu
     */
    public static void setApplicationMenu(HttpSession session, MainMenu applicationMenu)
    {
        session.setAttribute(cSessionApplicationMenu, applicationMenu);
    }

    /**
     * Static getter for the application menu
     * 
     * @param session An http session object
     * @return The application menu
     */
    public static MainMenu getApplicationMenu(HttpSession session)
    {
        return (MainMenu) session.getAttribute(cSessionApplicationMenu);
    }
}