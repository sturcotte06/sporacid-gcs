package gcs.webservices.client.requests.commandites;

import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;

import com.sun.research.ws.wadl.Request;

public class AddRequest extends Request
{
	public AddRequest(int idClub, int idItem, double valeur, String nature) {
		super();
		this.idClub = idClub;
		this.idItem = idItem;
		this.valeur = valeur;
		this.nature = nature;
	}


	public AddRequest(Integer idFournisseur, int idClub, int idItem, double valeur, String nature) {
		super();
		this.idFournisseur = idFournisseur;
		this.idClub = idClub;
		this.idItem = idItem;
		this.valeur = valeur;
		this.nature = nature;
	}
	
	@FormParam("idFounisseur")
	private Integer idFournisseur;
	
	@NotNull(message = "commandites_idClub_notempty")
	@FormParam("idClub")
	private int idClub;
	
	@NotNull(message = "commandites_idItem_notempty")
	@FormParam("idItem")
	private int idItem;
	
	@NotNull(message = "commandites_valeur_notempty")
	@FormParam("valeur")
	private double valeur;
	
	@NotNull(message = "commandites_nature_notempty")
	@FormParam("nature")
	private String nature;
	
	
	public AddRequest(){}


	public Integer getIdFournisseur() {
		return idFournisseur;
	}


	public void setIdFournisseur(Integer idFournisseur) {
		this.idFournisseur = idFournisseur;
	}


	public int getIdClub() {
		return idClub;
	}


	public void setIdClub(int idClub) {
		this.idClub = idClub;
	}


	public int getIdItem() {
		return idItem;
	}


	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}


	public double getValeur() {
		return valeur;
	}


	public void setValeur(double valeur) {
		this.valeur = valeur;
	}


	public String getNature() {
		return nature;
	}


	public void setNature(String nature) {
		this.nature = nature;
	}
	
		
	
}
