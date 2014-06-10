package gcs.webservices.dao;

import gcs.webapp.utils.exceptions.InternalException;
import gcs.webapp.utils.hibernate.HibernateUtils;
import gcs.webapp.utils.reflect.ReflectionUtils;
import gcs.webservices.models.Commandite;
import gcs.webservices.models.Membre;
import gcs.webservices.models.Suivie;
import gcs.webservices.models.SuivieStatut;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

public class CommanditeDao implements ICommanditeDao
{
    private SessionFactory sessionFactory;

    @Override
    public Collection<Commandite> getAllCommandite()
    {
        return HibernateUtils.getEntities(Commandite.class, sessionFactory);
        
    }

    @Override
    public Commandite getCommandite(int idCommandite)
    {
        return HibernateUtils.getEntity(idCommandite, Commandite.class, sessionFactory);
    }
    
    public Collection<SuivieStatut> getAllSuivieStatuts()
    {
        return HibernateUtils.getEntities(SuivieStatut.class, sessionFactory);
    }
    
    public SuivieStatut getSuivieStatutsById(int id)
    {
        return HibernateUtils.getEntity(id, SuivieStatut.class, sessionFactory);
    }
    
    @Override
    public Integer addCommandite(Commandite commandite) throws InternalException
    {
        Integer commanditeId = null;

        try {
            // Add the member
        	commanditeId = HibernateUtils.addEntity(commandite, sessionFactory);
        } catch (HibernateException ex) {
            // Couldn't add the membre
            throw new InternalException("commanditedao_addcommandite_exception", ex);
        }

        return commanditeId;
    }
    
    /**
     * @param  suivie à ajouter.
     */
    @Override
    public Integer addSuivie(Suivie suivie) throws InternalException
    {
    	Integer suivieId = null;

        try {
            // Add the member
        	suivieId = HibernateUtils.addEntity(suivie, sessionFactory);
        } catch (HibernateException ex) {
            // Couldn't add the membre
            throw new InternalException("commanditedao_addsuivie_exception", ex);
        }

        return suivieId;
    }

    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    /**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

	@Override
	public SuivieStatut getSuivieStatutsByName(String strSuivieStatut) {
		// TODO Auto-generated method stub
		return null;
	}
}
