package gcs.webservices.client.requests.commandites;

public class BindFournisseurRequest 
{
	private Integer idFournisseur;
	
	public BindFournisseurRequest() {}
	
	public BindFournisseurRequest(Integer idFournisseur)
	{
		this.idFournisseur = idFournisseur;
	}

	public Integer getIdFournisseur() {
		return idFournisseur;
	}

	public void setIdFournisseur(Integer idFournisseur) {
		this.idFournisseur = idFournisseur;
	}

}
