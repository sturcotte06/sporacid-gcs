package gcs.website.models;

import lombok.Getter;
import lombok.Setter;
import gcs.webapp.utils.hibernate.AbstractModelObject;
import gcs.website.views.helpers.Display;

public class Membre extends AbstractModelObject
{
	@Display("Id") // todo : localize
	@Getter @Setter
	private int id;
	
	@Display("Prénom")
	@Getter @Setter
	private String prenom;
	
	@Display("Nom")
	@Getter @Setter
	private String nom;
}
