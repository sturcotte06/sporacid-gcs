package gcs.website.controllers;

import gcs.website.views.beans.ContextChangeForm;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/usager")
public class UserController implements ApplicationContextAware 
{
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String getDashboard(HttpServletRequest request)
	{
		return "application";
	}
	
	@RequestMapping(value = "/preferences", method = RequestMethod.GET)
	public String getPreferences(HttpServletRequest request)
	{
		return "redirect:/";
	}
	
	@RequestMapping(value = "/modifier-contexte", method = RequestMethod.POST)
	public String changeContext(@ModelAttribute @Valid ContextChangeForm form, 
			BindingResult result, HttpServletRequest request,
			@RequestHeader(value = "referer", required = true) final String referer)
	{
		return "redirect:/";
	}
	
	/**
	 * ApplicationContextAware implementation
	 * Get specific instances from the application context
	 * @param context	The application context
	 */
	@Override
	public void setApplicationContext(ApplicationContext context) 
			throws BeansException 
	{ }
}