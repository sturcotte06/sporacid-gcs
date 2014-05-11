package gcs.webservices.client.beans.providers;

import java.lang.reflect.Type;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import gcs.webservices.client.beans.Context;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
// @Provider
//public class ContextProvider implements Injectable<Context>, InjectableProvider<InjectContext, Type>
//{
//    @javax.ws.rs.core.Context 
//    private UriInfo uriInfo;
//    
//    /** 
//     * From interface InjectableProvider.
//     * A new Injectable is instanciated per request.
//     */  
//    @Override
//    public ComponentScope getScope()
//    {
//        return ComponentScope.PerRequest;
//    }
//
//    @Override
//    public Injectable<Context> getInjectable(ComponentContext ic, InjectContext a, Type c)
//    {
//        if (c.equals(Context.class)) {  
//            return this;  
//        }
//        
//        return null;
//    }
//
//    @Override
//    public Context getValue()
//    {
//        final MultivaluedMap<String, String> pathParams = uriInfo.getPathParameters();
//        String contextName = pathParams.getFirst("contextName");
//        return new Context(contextName);
//    }
//}
