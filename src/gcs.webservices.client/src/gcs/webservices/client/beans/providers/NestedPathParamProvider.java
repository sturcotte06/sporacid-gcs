package gcs.webservices.client.beans.providers;

import java.lang.reflect.Type;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

/**
 * @author Simon Turcotte-Langevin
 */
// @Provider
//public class NestedPathParamProvider implements Injectable<Object>, InjectableProvider<InjectParam, Type>
//{
//    private final Type type;
//    
//    @Context 
//    private UriInfo uriInfo;
//    
//    public NestedPathParamProvider(Type type) 
//    {
//        this.type = type;
//    }
//    
//    @Override
//    public ComponentScope getScope()
//    {
//        return ComponentScope.PerRequest;
//    }
//
//    @Override
//    public Injectable<Object> getInjectable(ComponentContext ic, InjectParam a, Type c)
//    {
//        if (c.equals(Context.class)) {  
//            return this;  
//        }
//        
//        return null;
//    }
//
//    @Override
//    public Object getValue()
//    {
//        final MultivaluedMap<String, String> pathParams = uriInfo.getPathParameters();
//        return null;
//    }
//}
