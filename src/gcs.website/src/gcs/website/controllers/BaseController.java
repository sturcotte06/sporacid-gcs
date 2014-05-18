package gcs.website.controllers;

import gcs.webapp.utils.app.messages.IMessageLocalizer;
import gcs.webapp.utils.aspects.logging.Loggable;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Loggable
@Component
public abstract class BaseController
{
    /** */
    @Resource(name = "messageLocalizer")
    protected IMessageLocalizer messageLocalizer;

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
