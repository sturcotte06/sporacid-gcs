package gcs.webservices.models;

public class Membre 
{
	private int id;
	private String nom;
	private String codePermanent;
	
	public String getCodePermanent() 
	{
		return codePermanent;
	}
	
	public void setCodePermanent(String codePermanent) 
	{
		this.codePermanent = codePermanent;
	}
	
	public String getNom() 
	{
		return nom;
	}
	
	public void setNom(String nom) 
	{
		this.nom = nom;
	}
	
	public int getId() 
	{
		return id;
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}
}
