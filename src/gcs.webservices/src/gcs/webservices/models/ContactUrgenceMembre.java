package gcs.webservices.models;

import gcs.webapp.utils.hibernate.AbstractModelObject;
import gcs.webservices.models.pks.ContactUrgenceMembrePK;

import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "contacts_urgence_membres")
@IdClass(ContactUrgenceMembrePK.class)
public class ContactUrgenceMembre extends AbstractModelObject 
{
	
}
