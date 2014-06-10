package gcs.webservices.dao;

import gcs.webservices.models.Commandite;
import gcs.webservices.models.Suivie;
import gcs.webservices.models.SuivieStatut;

import java.util.Collection;

public interface ICommanditeDao {
	public Collection<Commandite> getAllCommandite();
	public Commandite getCommandite(int idCommandite);
	public Collection<SuivieStatut> getAllSuivieStatuts();
	public SuivieStatut getSuivieStatutsById(int id);
	public Integer addCommandite(Commandite commandite);
	public Integer addSuivie(Suivie suivie);
	public SuivieStatut getSuivieStatutsByName(String strSuivieStatut);
}
