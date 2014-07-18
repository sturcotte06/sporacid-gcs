package gcs.webapp.utils;

/**
 * Stupid wrapper object to bypass java's final 
 * requirement for closures (lambda).
 * 
 * @author Simon Turcotte-Langevin
 */
public class Wrapper<TObject>
{
    /**
     * The object to wrap.
     */
    private TObject object;

    /**
     * @return the object
     */
    public TObject getObject()
    {
        return object;
    }

    /**
     * @param object the object to set
     */
    public void setObject(TObject object)
    {
        this.object = object;
    }
}
