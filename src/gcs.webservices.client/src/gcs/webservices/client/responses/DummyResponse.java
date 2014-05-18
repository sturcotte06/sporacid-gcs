package gcs.webservices.client.responses;

public class DummyResponse extends gcs.webservices.client.responses.Response
{
    private Object object;

    /**
     * @return the object
     */
    public Object getObject()
    {
        return object;
    }

    /**
     * @param object the object to set
     */
    public void setObject(Object object)
    {
        this.object = object;
    }
}