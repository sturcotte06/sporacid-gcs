package gcs.webapp.utils.beans;

/**
 * @author Simon Turcotte-Langevin
 */
public interface IBeanFactory
{
    /**
     * Creates a bean object of the given class with the properties of the given
     * model object.
     * 
     * @param model The model object from which we'll copy properties.
     * @param beanClass The class object of the bean.
     * @return A bean which properties are filled from the model.
     */
    public <TModel, TBean> TBean createBean(TModel model, Class<TBean> beanClass);
}
