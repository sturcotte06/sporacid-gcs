package gcs.website.controllers;

import gcs.website.utils.SessionUtils;
import gcs.website.views.beans.SearchForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/**")
public class HomeController
{
	/**
	 * 
	 * @param form
	 * @param result
	 * @param request
	 * @param referer
	 * @return
	 */
	@RequestMapping(value = "/rechercher", method = RequestMethod.POST)
	public String search(@ModelAttribute @Valid SearchForm form, 
			BindingResult result, HttpServletRequest request,
			@RequestHeader(value = "referer", required = true) final String referer)
	{
		// Resolve research, with magic perhaps
		return "home";
	}
	
	/**
	 * Default behaviour
	 * @param request
	 * @return 
	 */
	@RequestMapping(value = "/**")
	public String redirect(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		if (SessionUtils.getWsSession(session) != null) {
			return "redirect:/usager/dashboard";
		} else {
			return "redirect:/public/connexion";
		}
	}
}