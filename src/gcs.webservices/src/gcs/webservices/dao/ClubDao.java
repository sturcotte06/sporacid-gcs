package gcs.webservices.dao;

import gcs.webapp.utils.exceptions.EntityNotFoundException;
import gcs.webapp.utils.exceptions.InternalException;
import gcs.webapp.utils.hibernate.HibernateUtils;
import gcs.webservices.models.Club;

import java.util.Collection;

import org.hibernate.SessionFactory;

public class ClubDao implements IClubDao
{
    private SessionFactory sessionFactory;

    @Override
    public Collection<Club> getClubs() throws InternalException
    {
        Collection<Club> clubs;
        clubs = HibernateUtils.getEntities(Club.class, sessionFactory);

        if (clubs == null) {
            throw new EntityNotFoundException();
        }

        return clubs;
    }

    @Override
    public Club getClub(int clubId) throws InternalException
    {
        Club club = HibernateUtils.getEntity(clubId, Club.class, sessionFactory);

        if (club == null) {
            throw new EntityNotFoundException("club", clubId);
        }

        return club;
    }

    @Override
    public Integer addClub(Club club) throws InternalException
    {
        // TODO Auto-generated method stub
        return null;
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
}
