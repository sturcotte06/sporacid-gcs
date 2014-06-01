package gcs.webservices.dao;

import gcs.webservices.models.Fournisseur;

import java.util.Collection;

public interface IFournisseurDao {
	public Collection<Fournisseur> getAllFournisseur();
	public Fournisseur getFournisseurById(int idFournisseur);
}
