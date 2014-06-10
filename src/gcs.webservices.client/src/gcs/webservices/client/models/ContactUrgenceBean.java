package gcs.webservices.client.models;

import javax.validation.constraints.NotNull;

public class ContactUrgenceBean extends AbstractBean
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "webservices_contacturgencemodels_id_notnull")
	private int id;
	
	@NotNull(message = "webservices_contacturgencemodels_id_notnull")
	private LienParenteBean lienParente;
	
	@NotNull(message = "webservices_contacturgencemodels_membreId_notnull")
	private int membreId;
	
	private String nom;
	
	private String prenom;
	
	private String telephone;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LienParenteBean getLienParente() {
		return lienParente;
	}

	public void setLienParent(LienParenteBean lienParenteBean) {
		this.lienParente = lienParenteBean;
	}

	public int getMembreId() {
		return membreId;
	}

	public void setMembreId(int membreId) {
		this.membreId = membreId;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
