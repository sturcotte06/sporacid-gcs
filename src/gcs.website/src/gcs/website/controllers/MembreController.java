package gcs.website.controllers;

import java.util.Collection;

import gcs.webapp.utils.exceptions.InternalException;
import gcs.webservices.client.IEnumServiceClient;
import gcs.webservices.client.IMembreServiceClient;


import gcs.webservices.client.IUsagerServiceClient;
import gcs.webservices.client.models.ConcentrationBean;
import gcs.webservices.client.models.usagers.UserProfileBean;
import gcs.webservices.client.responses.ResponseWithEntity;
import gcs.webservices.client.responses.membres.GetAllMembresOfClubResponse;
import gcs.website.utils.SessionUtils;
import gcs.website.views.beans.WsSession;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/membres")
public class MembreController
{
    @Resource(name = "membreServiceClient")
    private IMembreServiceClient membreServiceClient;

    
    @Resource(name="enumServiceClient")
	private IEnumServiceClient enumServiceClient;
    
    @RequestMapping(value = "/gerer", method = RequestMethod.GET)
    public String getListMembres(HttpServletRequest request)
    {
        /* Collection<Membre> membres = new ArrayList<Membre>();
         * 
         * Membre m1 = new Membre(); m1.setId(1); m1.setPrenom("Simon");
         * m1.setNom("Turcotte-Langevin"); membres.add(m1);
         * 
         * Membre m2 = new Membre(); m2.setId(2); m2.setPrenom("Patrick");
         * m2.setNom("Lavallée"); membres.add(m2);
         * 
         * Membre m3 = new Membre(); m3.setId(3); m3.setPrenom("Jean");
         * m3.setNom("Bernier-Vibert"); membres.add(m3); */

        HttpSession session = request.getSession();
        WsSession wsSession = SessionUtils.getWsSession(session);

        if (wsSession == null) {
            return "redirect:/";
        }

        String ipv4Address = request.getHeader("X-FORWARDED-FOR");
        if (ipv4Address == null) {
            ipv4Address = request.getRemoteAddr();
            if (ipv4Address == null) {
                throw new InternalException("controllers_ipv4address_cannotberesolved");
            }
        }

        GetAllMembresOfClubResponse response = membreServiceClient.getAllMembresOfClub(ipv4Address,
                wsSession.getSessionKey(), "preci");
        request.setAttribute("listeMembres", response.getEntity());

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

    @RequestMapping(value = "/ajouter", method = RequestMethod.GET)
    public String ajoutMembre(HttpServletRequest request)
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
        	
        return "partial-views/add-membre";
    }
    
    @RequestMapping(value = "/ajouter", method = RequestMethod.POST)
    public String addMembre(HttpServletRequest request)
    {
        return "partial-views/add-membre";

		return null;
    }
}