package gcs.webservices.models;

import lombok.Getter;
import lombok.Setter;

public class Membre 
{
   @Getter @Setter
	private int id;
   
   @Getter @Setter
	private String nom;
   
   @Getter @Setter
	private String codePermanent;
}
