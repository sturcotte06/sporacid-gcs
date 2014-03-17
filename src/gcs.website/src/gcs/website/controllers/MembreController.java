package gcs.website.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/membres")
public class MembreController implements ApplicationContextAware 
{
	@RequestMapping(value = "/gerer", method = RequestMethod.GET)
	public String getListMembres(HttpServletRequest request)
	{
		return "partial-views/get-list-membre";
	}
	
	@RequestMapping(value = "/obtenir", method = RequestMethod.GET)
	public String getMembre(HttpServletRequest request)
	{
		return "partial-views/get-membre";
	}
	
	@RequestMapping(value = "/editer", method = RequestMethod.POST)
	public String editMembre(HttpServletRequest request)
	{
		return "partial-views/edit-membre";
	}
	
	@RequestMapping(value = "/ajouter", method = RequestMethod.POST)
	public String addMembre(HttpServletRequest request)
	{
		return "partial-views/add-membre";
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