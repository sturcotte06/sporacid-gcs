package gcs.webapp.utils.beans;

import gcs.webapp.utils.exceptions.ArgumentNullException;
import gcs.webapp.utils.exceptions.ReflectionException;
import gcs.webapp.utils.reflect.ReflectionUtils;

public class BeanFactory implements IBeanFactory
{
    /**
     * Creates a bean object of the given class with the properties of the given
     * model object.
     * 
     * @param model The model object from which we'll copy properties.
     * @param beanClass The class object of the bean.
     * @return A bean which properties are filled from the model.
     */
    @Override
    public <TModel, TBean> TBean createBean(TModel model, Class<TBean> beanClass)
    {
        if (model == null) {
            throw new ArgumentNullException("source");
        }

        if (beanClass == null) {
            throw new ArgumentNullException("dstClass");
        }

        TBean bean;

        try {
            // Get a new instance of the destination bean.
            bean = (TBean) beanClass.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new ReflectionException(ex);
        }

        // Copy the source into the destination.
        ReflectionUtils.copyInto(model, model.getClass(), bean, beanClass);

        // Return the filled destination.
        return bean;
    }
}
