package gcs.website.controllers;

import gcs.webapp.utils.MessageType;
import gcs.webapp.utils.app.messages.IMessageLocalizer;
import gcs.webapp.utils.aspects.logging.Loggable;
import gcs.website.utils.SessionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

@Loggable
@Component
public abstract class BaseController
{
    /** */
    @Resource(name = "messageLocalizer")
    protected IMessageLocalizer messageLocalizer;
    
    /**
     * 
     * @param result
     */
    protected void addValidationErrorsToSessionMessages(HttpSession session, BindingResult result)
    {
        for (ObjectError error : result.getAllErrors()) {
            String localizedMsg = messageLocalizer.localizeString(error.getDefaultMessage());
            SessionUtils.addMessage(MessageType.Error, localizedMsg, session);
        }
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
}
