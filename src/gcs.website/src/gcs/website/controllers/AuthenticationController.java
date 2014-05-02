package gcs.website.controllers;

import gcs.webapp.utils.Message;
import gcs.webapp.utils.MessageType;
import gcs.webapp.utils.app.menus.IMenuProvider;
import gcs.webapp.utils.app.messages.IMessageLocalizer;
import gcs.website.controllers.services.IAuthenticationService;
import gcs.website.controllers.services.beans.responses.LoginResponse;
import gcs.website.controllers.services.beans.responses.Response;
import gcs.website.utils.SessionUtils;
import gcs.website.views.beans.AuthenticationForm;
import gcs.website.views.beans.WsSession;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import lombok.Getter;
import lombok.Setter;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/public/**")
public class AuthenticationController /*implements ApplicationContextAware*/ 
{
	/**
	 * 
	 */
   @Resource(name = "authenticationService")
   @Getter @Setter
	private IAuthenticationService authenticationService;
	
	/**
	 * 
	 */
   @Resource(name = "messageLocalizer")
   @Getter @Setter
	private IMessageLocalizer messageLocalizer;
	
	/**
	 * 
	 */
   @Resource(name = "menuProvider")
   @Getter @Setter
	private IMenuProvider menuProvider;
	
	/**
	 * 
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
	 * 
	 * @param form
	 * @param result
	 * @param request
	 * @param referer
	 * @return
	 */
	@RequestMapping(value = "/connexion", method = RequestMethod.POST)
	public String login(@ModelAttribute @Valid AuthenticationForm form, 
			BindingResult result, HttpServletRequest request,
			@RequestHeader(value = "referer", required = true) final String referer)
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
			
			LoginResponse response = authenticationService.login(ipAddress, form.getUsername(), form.getPassword());
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
					SessionUtils.addMessage(message.getMessageType(), message.getMessageContent(), session);
				}
				
   	      request.setAttribute("authenticationForm", form);
   	      direction = "login";
			}
		}
		
		return direction;
	}
	
	/**
	 * 
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
			// Remove the session from the web services
		   String ipAddress = request.getHeader("X-FORWARDED-FOR");  
		   if (ipAddress == null) {  
			   ipAddress = request.getRemoteAddr();  
		   }
			Response response = authenticationService.logout(ipAddress, wsSession.getSessionKey());
			
			if (!response.isSuccess()) {
				// Failure; tell the user why
				for (Message message : response.getMessages()) {
					SessionUtils.addMessage(message.getMessageType(), message.getMessageContent(), session);
				}
			}
			
			// Sets the web services session; No more action can be taken
			// with that token anyway
			SessionUtils.setWsSession(request.getSession(), null);
		}
		
		return direction;
	}
	
	/**
	 * 
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
	 * 
	 * @param form
	 * @param result
	 * @param request
	 * @param referer
	 * @return
	 */
	@RequestMapping(value = "/reinitialiser-mot-de-passe", method = RequestMethod.POST)
	public String passwordRetrieval(@ModelAttribute @Valid AuthenticationForm form, 
			BindingResult result, HttpServletRequest request,
			@RequestHeader(value = "referer", required = true) final String referer)
	{
		String direction = "redirect:/";
		// HttpSession session = request.getSession();
		
		return direction;
	}
	
	/**
	 * ApplicationContextAware implementation
	 * Get specific instances from the application context
	 * @param context	The application context
	 */
	/*@Override
	public void setApplicationContext(ApplicationContext context) 
			throws BeansException 
	{ 
		authenticationService = context.getBean("authenticationService", IAuthenticationService.class);
		messageLocalizer = context.getBean("messageLocalizer", IMessageLocalizer.class);
		menuProvider = context.getBean("menuProvider", IMenuProvider.class);
	}*/
}
