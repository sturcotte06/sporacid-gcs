package gcs.website.models;

import gcs.webapp.utils.hibernate.AbstractModelObject;
import gcs.website.views.helpers.Display;

public class Membre extends AbstractModelObject
{
	@Display("Id") // todo : localize
	private int id;
	
	@Display("Prénom")
	private String prenom;
	
	@Display("Nom")
	private String nom;
	
	public int getId() 
	{
		return id;
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public String getPrenom() 
	{
		return prenom;
	}
	
	public void setPrenom(String prenom) 
	{
		this.prenom = prenom;
	}
	
	public String getNom() 
	{
		return nom;
	}
	
	public void setNom(String nom) 
	{
		this.nom = nom;
	}
}
