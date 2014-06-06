package gcs.webapp.utils.reflect;

import static org.reflections.ReflectionUtils.*;
import gcs.webapp.utils.exceptions.ArgumentNullException;
import gcs.webapp.utils.exceptions.ReflectionException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.gson.Gson;

/**
 * TODO use reflections library where applicable and configure it with generated
 * metadata.
 * 
 * @author Simon Turcotte-Langevin
 */
// @Loggable
public final class ReflectionUtils
{
    /**
     * Returns whether the object is a wrapper for a primitive type or not.
     * 
     * @param o An object to test
     * @return Whether the object is a wrapper for a primitive type or not
     */
    public static boolean isPrimitive(Object obj)
    {
        if (obj == null) {
            throw new ArgumentNullException("obj");
        }

        return isPrimitive(obj.getClass());
    }

    /**
     * Returns whether the object type is a wrapper for a primitive type or not.
     * 
     * @param classObj An object to test.
     * @return Whether the object is a wrapper for a primitive type or not
     */
    public static boolean isPrimitive(Class<?> classObj)
    {
        if (classObj == null) {
            throw new ArgumentNullException("classObj");
        }

        final Class<?>[] wrapperTypes = { Boolean.class, Character.class, Byte.class, Short.class, Integer.class,
                Long.class, Float.class, Double.class, Void.class };

        boolean isPrimitive = false;
        for (int iWrapperType = 0; iWrapperType < wrapperTypes.length; iWrapperType++) {
            if (wrapperTypes[iWrapperType].equals(classObj)) {
                isPrimitive = true;
                break;
            }
        }

        return isPrimitive;
    }

    /**
     * Method that return a dictionary of all properties of an object and its
     * values.
     * 
     * @param object An object from which we want properties
     * @param classObj The class of the object
     * @return A dictionary of all properties of the object and its values
     * @throws IllegalAccessException Typical exception when dealing with
     *             reflection
     */
    public static <E> Map<String, Object> getObjectProperties(Object object, Class<E> classObj)
    {
        if (object == null) {
            throw new ArgumentNullException("object");
        }

        if (classObj == null) {
            throw new ArgumentNullException("classObj");
        }

        return getObjectProperties(object, classObj, null);
    }

    /**
     * @param obj
     * @param classObj
     * @return
     */
    public static <TObject> TObject deepCopy(TObject obj)
    {
        return deepCopy(obj, new Gson());
    }

    /**
     * @param obj
     * @param classObj
     * @param jsonSerializer
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <TObject> TObject deepCopy(TObject obj, Gson jsonSerializer)
    {
        if (obj == null) {
            throw new ArgumentNullException("obj");
        }

        if (jsonSerializer == null) {
            throw new ArgumentNullException("jsonSerializer");
        }

        String json = jsonSerializer.toJson(obj);
        TObject clone = (TObject) jsonSerializer.fromJson(json, obj.getClass());
        return clone;
    }

    /**
     * Recursive method that return a dictionary of all properties of an object
     * and its values.
     * 
     * @param object An object from which we want properties
     * @param classObj The class of the object
     * @param currentPath Current property path
     * @return A dictionary of all properties of the object and its values
     * @throws IllegalAccessException Typical exception when dealing with
     *             reflection
     */
    private static <E> Map<String, Object> getObjectProperties(Object object, Class<E> classObj, String currentPath)
    {
        Map<String, Object> fieldsMap = new HashMap<String, Object>();
        Field[] valueObjFields = classObj.getDeclaredFields();

        // compare values now
        for (int i = 0; i < valueObjFields.length; i++) {
            String fieldName = (currentPath != null)
                    ? currentPath + "." + valueObjFields[i].getName()
                    : valueObjFields[i].getName();
            valueObjFields[i].setAccessible(true);

            Object newObj = null;
            try {
                newObj = valueObjFields[i].get(object);
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                throw new ReflectionException(ex);
            }

            if (newObj != null) {
                // If the property is either a primitive type, a Date or a
                // String,
                // toString() can be called to get the value.
                if (ReflectionUtils.isPrimitive(newObj) || newObj instanceof Date || newObj instanceof String) {
                    fieldsMap.put(fieldName, newObj);
                }
                // The property is a complex object, resolve the sub object
                else {
                    // Recursivity
                    fieldsMap.putAll(getObjectProperties(newObj, newObj.getClass(), fieldName));
                }
            }
        }

        return fieldsMap;
    }

    /**
     * Copies an object into a new instance of the associated bean type. Types
     * for the source and destination can differ. This method can be used to
     * transfer the state of an object into another object, or simply to copy an
     * object.
     * 
     * @param source The source object, from which we'll take properties'
     *            values.
     * @param srcClass The source class object.
     * @param dstClass The destination class object.
     * @return The generated bean with its properties filled.
     */
    @SuppressWarnings("unchecked")
    public static <TSource, TDestination> void copyInto(TSource source, Class<?> srcClass, TDestination destination,
            Class<?> dstClass)
    {
        if (source == null) {
            throw new ArgumentNullException("source");
        }

        if (destination == null) {
            throw new ArgumentNullException("destination");
        }

        // Get all getters from the source using org.reflections library.
        final Set<Method> srcGetters = getAllMethods(srcClass, withModifier(Modifier.PUBLIC), withPrefix("get"),
                withParametersCount(0));

        // This is so stupidly inefficient... but whatever. Pretty sure that is
        // faster than querying the method in the loop. I wish HashSet had a
        // get(obj) method.
        // Get all setters from the destination using org.reflections library.
        final Map<String, Method> dstSetters = getAllMethods(dstClass, withModifier(Modifier.PUBLIC),
                withPrefix("set"), withParametersCount(1)).stream().collect(
                Collectors.toMap(Method::getName, method -> method));

        // Get all getters from the destination using org.reflections library.
        final Map<String, Method> dstGetters = getAllMethods(dstClass, withModifier(Modifier.PUBLIC),
                withPrefix("get"), withParametersCount(0)).stream().collect(
                Collectors.toMap(Method::getName, method -> method));

        // Iterate through each of the getters
        for (Method srcGetter : srcGetters) {
            Class<?> srcGetterReturnType = srcGetter.getReturnType();
            String fieldName = srcGetter.getName().replace("get", "");

            if (isPrimitive(srcGetterReturnType) || srcGetterReturnType == String.class
                    || srcGetterReturnType == Date.class || Collection.class.isAssignableFrom(srcGetterReturnType)
                    || srcGetterReturnType.isArray() || Map.class.isAssignableFrom(srcGetterReturnType)) {

                // Method is either a collection, an array or a primitive;
                // simply copy the values from getter to setter.
                if (!dstSetters.containsKey("set" + fieldName)) {
                    // Property setter is not in the destination
                    continue;
                }

                // Copy the values from getter to setter.
                Method dstSetter = dstSetters.get("set" + fieldName);
                try {
                    dstSetter.invoke(destination, srcGetter.invoke(source));
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    throw new ReflectionException(ex);
                }
            } else {
                // Method is a nested object getter.
                // Recursivity.
                if (!dstGetters.containsKey("get" + fieldName)) {
                    // Property getter is not in the destination
                    continue;
                }

                // Pretty sick recursivity, huh?
                Method dstGetter = dstGetters.get("get" + fieldName);
                Class<?> dstGetterReturnType = dstGetter.getReturnType();
                try {
                    copyInto(srcGetter.invoke(source), srcGetterReturnType, dstGetter.invoke(destination),
                            dstGetterReturnType);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    throw new ReflectionException(ex);
                }
            }
        }
    }

    /**
     * Copies an object into a new instance of the associated bean type. Types
     * for the source and destination can differ. This method can be used to
     * transfer the state of an object into another object, or simply to copy an
     * object.
     * 
     * @param source The source object, from which we'll take properties'
     *            values.
     * @param srcClass The source class object.
     * @param dstClass The destination class object.
     * @return The generated bean with its properties filled.
     */
    @SuppressWarnings("unchecked")
    public static <TSource, TDestination> TDestination generateBean(TSource source, Class<?> srcClass, Class<?> dstClass)
    {
        TDestination destination;

        try {
            // Get a new instance of the destination bean.
            destination = (TDestination) dstClass.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new ReflectionException(ex);
        }

        // Copy the source into the destination.
        copyInto(source, srcClass, destination, dstClass);

        // Return the filled destination.
        return destination;
    }
}
