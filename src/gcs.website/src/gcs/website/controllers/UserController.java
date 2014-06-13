package gcs.website.controllers;

import gcs.webapp.utils.exceptions.InternalException;
import gcs.webservices.client.IEnumServiceClient;
import gcs.webservices.client.IUsagerServiceClient;
import gcs.webservices.client.models.ConcentrationBean;
import gcs.webservices.client.models.usagers.UserProfileBean;
import gcs.webservices.client.responses.ResponseWithEntity;
import gcs.website.utils.SessionUtils;
import gcs.website.views.beans.ContextChangeForm;

import java.util.Collection;

import javax.annotation.Resource;
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
@RequestMapping(value = "/usager")
public class UserController
{
	@Resource(name="enumServiceClient")
	private IEnumServiceClient enumServiceClient;
	
	@Resource(name="usagerServiceClient")
	private IUsagerServiceClient usagerServiceClient;
	
	
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String getDashboard(HttpServletRequest request)
    {
        return "application";
    }

    @RequestMapping(value = "/preferences", method = RequestMethod.GET)
    public String getPreferences(HttpServletRequest request)
    {
    	HttpSession session = request.getSession();
    	
    	String wsSessionKey;
    	if (SessionUtils.getWsSession(session) == null) 
    	{
            return "redirect:/";
        }
    	else
    	{
    		wsSessionKey = SessionUtils.getWsSession(session).getSessionKey();
    	}

        String ipv4Address = request.getHeader("X-FORWARDED-FOR");
        if (ipv4Address == null) 
        {
            ipv4Address = request.getRemoteAddr();
            if (ipv4Address == null) 
            {
                throw new InternalException("controllers_ipv4address_cannotberesolved");
            }
        }
    	
        // Fetching Concentration for combobox
    	ResponseWithEntity<Collection<ConcentrationBean>> concentrationResponse = enumServiceClient.getConcentrations();
    	request.setAttribute("listeConcentrations", concentrationResponse.getEntity());
    	
    	// Fetching current user profile
    	ResponseWithEntity<UserProfileBean> userProfileResponse = usagerServiceClient.getProfile(ipv4Address, wsSessionKey); 
    	request.setAttribute("userProfile", userProfileResponse.getEntity());
    	
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

	/**
	 * @return the usagerServiceClient
	 */
	public IUsagerServiceClient getUsagerServiceClient() 
	{
		return usagerServiceClient;
	}

	/**
	 * @param usagerServiceClient the usagerServiceClient to set
	 */
	public void setUsagerServiceClient(IUsagerServiceClient usagerServiceClient) 
	{
		this.usagerServiceClient = usagerServiceClient;
	}
}