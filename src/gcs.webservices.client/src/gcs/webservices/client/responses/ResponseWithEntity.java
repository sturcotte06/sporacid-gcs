package gcs.webservices.client.responses;

public class ResponseWithEntity<TEntity> extends Response
{
    private TEntity entity;

    /**
     * @return the entity
     */
    public TEntity getEntity()
    {
        return entity;
    }

    /**
     * @param entity the entity to set
     */
    public void setEntity(TEntity entity)
    {
        this.entity = entity;
    }
}