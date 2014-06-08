package gcs.website.controllers;

import gcs.webservices.client.IEnumServiceClient;
import gcs.webservices.client.models.ConcentrationBean;
import gcs.webservices.client.responses.ResponseWithEntity;
import gcs.website.views.beans.ContextChangeForm;

import java.util.Collection;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/usager")
public class UserController
{
	@Resource(name="enumServiceClient")
	private IEnumServiceClient enumServiceClient;
	
	
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String getDashboard(HttpServletRequest request)
    {
        return "application";
    }

    @RequestMapping(value = "/preferences", method = RequestMethod.GET)
    public String getPreferences(HttpServletRequest request)
    {
    	ResponseWithEntity<Collection<ConcentrationBean>> response = enumServiceClient.getConcentrations();
    	
    	request.setAttribute("listeConcentrations", response.getEntity());
        return "partial-views/profil-prive";
    }

    @RequestMapping(value = "/modifier-contexte", method = RequestMethod.POST)
    public String changeContext(@ModelAttribute @Valid ContextChangeForm form, BindingResult result, HttpServletRequest request, @RequestHeader(value = "referer", required = true) final String referer)
    {
        return "redirect:/";
    }

	/**
	 * @return the enumServiceClient
	 */
	public IEnumServiceClient getEnumServiceClient() 
	{
		return enumServiceClient;
	}

	/**
	 * @param enumServiceClient the enumServiceClient to set
	 */
	public void setEnumServiceClient(IEnumServiceClient enumServiceClient) 
	{
		this.enumServiceClient = enumServiceClient;
	}
}