package gcs.webapp.utils.reflect;

import static org.reflections.ReflectionUtils.*;
import gcs.webapp.utils.exceptions.ArgumentNullException;
import gcs.webapp.utils.exceptions.ReflectionException;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.Bag;
import org.apache.commons.collections.bag.HashBag;

import antlr.collections.List;

import com.google.gson.Gson;

/**
 * TODO use reflections library where applicable and configure it with generated
 * metadata.
 * 
 * @author Simon Turcotte-Langevin
 */
public final class ReflectionUtils
{
    /**
     * Constant array of all primitive types.
     */
    private static final Class<?>[] cPrimitiveTypes = { boolean.class, char.class, byte.class, short.class, int.class,
            long.class, float.class, double.class };
    /**
     * Constant array of all primitive wrapper types.
     */
    private static final Class<?>[] cPrimitiveWrapperTypes = { Boolean.class, Character.class, Byte.class, Short.class,
            Integer.class, Long.class, Float.class, Double.class };

    /** */
    private static final Gson jsonSerializer = new Gson();
    
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

        boolean isPrimitive = false;
        for (int iType = 0; iType < cPrimitiveTypes.length; iType++) {
            if (cPrimitiveTypes[iType].equals(classObj) || cPrimitiveWrapperTypes[iType].equals(classObj)) {
                isPrimitive = true;
                break;
            }
        }

        return isPrimitive;
    }

    /**
     * @param obj
     * @param classObj
     * @return
     */
    public static <TObject> TObject deepCopy(TObject obj)
    {
        return deepCopy(obj, jsonSerializer);
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
     * Method that return a dictionary of all properties of an object and its
     * values.
     * 
     * @param object An object from which we want properties
     * @param classObj The class of the object
     * @return A dictionary of all properties of the object and its values
     * @throws IllegalAccessException Typical exception when dealing with
     *             reflection
     */
    @SuppressWarnings("rawtypes")
    public static <TObject> Map<String, Object> flatten(TObject object)
    {
        if (object == null) {
            throw new ArgumentNullException("object");
        }

        Map<String, Object> flattenResult = new HashMap<>();
        Class<?> classObj = object.getClass();
        if (Collection.class.isAssignableFrom(classObj)) {
            flattenCollection((Collection) object, classObj, flattenResult, null);
        } else if (classObj.isArray()) {
            flattenArray(object, classObj, flattenResult, null);
        } else {
            flatten(object, classObj, flattenResult, null);
        }

        return flattenResult;
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
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static <TObject> void flatten(Object object, Class<TObject> classObj, Map<String, Object> fieldsMap,
            String currentPath)
    {
        // Get all getters from the source using org.reflections library.
        final Set<Method> objectGetters = getAllMethods(classObj, withModifier(Modifier.PUBLIC), withPrefix("get"),
                withParametersCount(0));

        try {
            // Iterate through each of the getters
            for (Method objectGetter : objectGetters) {
                Class<?> objectGetterReturnType = objectGetter.getReturnType();
                String fieldName = objectGetter.getName().replace("get", "");
                fieldName = fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);

                if (isPrimitive(objectGetterReturnType) || String.class.isAssignableFrom(objectGetterReturnType)
                        || Date.class.isAssignableFrom(objectGetterReturnType)) {
                    // If the property is either a primitive type, a Date or a
                    // String, we simply add the return value of the getter.
                    fieldsMap.put(fieldName, objectGetter.invoke(object));
                } else if (Collection.class.isAssignableFrom(objectGetterReturnType)) {
                    // Getter return type is a collection type;
                    Collection collection = (Collection) objectGetter.invoke(object);
                    flattenCollection(collection, objectGetterReturnType, fieldsMap, currentPath + "." + fieldName);
                } else if (objectGetterReturnType.isArray()) {
                    // Getter return type is an array type;
                    Object array = objectGetter.invoke(object);
                    flattenArray(array, objectGetterReturnType, fieldsMap, currentPath + "." + fieldName);
                } else {
                    flatten(objectGetter.invoke(object), objectGetterReturnType, fieldsMap, currentPath + "."
                            + fieldName);
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new ReflectionException(ex);
        }
    }

    /**
     * 
     * @param array
     * @param classObj
     * @param fieldsMap
     * @param currentPath
     */
    private static <TObject> void flattenArray(Object array, Class<?> classObj, Map<String, Object> fieldsMap,
            String currentPath)
    {
        // Getter return type is an array type;
        Class<?> arrayType = classObj.getComponentType();
        int arrayLength = Array.getLength(array);

        // Iterate through each object and recall the method
        // recursively...
        for (int iArray = 0; iArray < arrayLength; iArray++) {
            flatten(Array.get(array, iArray), arrayType, fieldsMap, currentPath + "[" + iArray + "]");
            iArray++;
        }
    }

    /**
     * 
     * @param collection
     * @param classObj
     * @param fieldsMap
     * @param currentPath
     */
    @SuppressWarnings("rawtypes")
    private static <TObject> void flattenCollection(Collection collection, Class<TObject> classObj,
            Map<String, Object> fieldsMap, String currentPath)
    {
        // Get the source collection's elements' generic type.
        ParameterizedType collectionGenericType = (ParameterizedType) classObj.getGenericSuperclass();
        Class<?> collectionGenericClass = getClass(collectionGenericType.getActualTypeArguments()[0]);

        // Iterate through each object and recall the method
        // recursively...
        int iElement = 0;
        for (Object collectionElement : collection) {
            flatten(collectionElement, collectionGenericClass, fieldsMap, currentPath + "[" + iElement + "]");
            iElement++;
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
    @SuppressWarnings({ "unchecked", "rawtypes" })
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

        try {
            // Iterate through each of the getters
            for (Method srcGetter : srcGetters) {
                String fieldName = srcGetter.getName().replace("get", "");
                if (!dstSetters.containsKey("set" + fieldName) || !dstGetters.containsKey("get" + fieldName)) {
                    // Property getter and setter is not in the destination
                    continue;
                }

                Object srcGetterValue = srcGetter.invoke(source);
                if (srcGetterValue == null) {
                    // Don't copy null value
                    continue;
                }

                // Get the destination getter and setter
                final Method dstGetter = dstGetters.get("get" + fieldName);
                final Method dstSetter = dstSetters.get("set" + fieldName);

                // Get the types of both source and destination objects
                Class<?> srcGetterReturnType = srcGetter.getReturnType();
                Class<?> dstGetterReturnType = dstGetter.getReturnType();

                if (isPrimitive(srcGetterReturnType) || srcGetterReturnType == String.class
                        || srcGetterReturnType == Date.class) {
                    // Getter return type is either a primitive, a string or a
                    // date; simply copy the values from getter to setter.
                    dstSetter.invoke(destination, srcGetterValue);
                } else if (Collection.class.isAssignableFrom(srcGetterReturnType)) {
                    // Getter return type is a collection type;
                    // Get the source collection
                    Collection srcCollection = (Collection) srcGetterValue;

                    // Get the source collection's elements' generic type.
                    ParameterizedType srcCollectionGenericType = ((ParameterizedType) srcGetter.getGenericReturnType());
                    Class<?> srcCollectionGenericClass = getClass(srcCollectionGenericType.getActualTypeArguments()[0]);

                    // Create a new destination collection
                    Collection dstCollection = createCollection(dstGetterReturnType);

                    // Get the destination collection's elements' generic type.
                    ParameterizedType dstCollectionGenericType = ((ParameterizedType) dstGetter.getGenericReturnType());
                    Class<?> dstCollectionGenericClass = getClass(dstCollectionGenericType.getActualTypeArguments()[0]);

                    // Iterate through each object and recall the method
                    // recursively...
                    for (Object srcCollectionElement : srcCollection) {
                        Object dstCollectionElement = dstCollectionGenericClass.newInstance();
                        copyInto(srcCollectionElement, srcCollectionGenericClass, dstCollectionElement,
                                dstCollectionGenericClass);
                        dstCollection.add(dstCollectionElement);
                    }

                    // Set the collection attribute
                    dstSetter.invoke(destination, dstCollection);
                } else if (srcGetterReturnType.isArray()) {
                    // Getter return type is an array type;
                    Class<?> srcArrayType = srcGetterReturnType.getComponentType();
                    Object srcArray = srcGetterValue;
                    int srcArrayLength = Array.getLength(srcArray);

                    Class<?> dstArrayType = dstGetter.getReturnType().getComponentType();
                    Object dstArray = Array.newInstance(dstArrayType, srcArrayLength);

                    // Iterate through each object and recall the method
                    // recursively...
                    for (int iArray = 0; iArray < srcArrayLength; iArray++) {
                        Object dstArrayElement = dstArrayType.newInstance();
                        copyInto(Array.get(srcArray, iArray), srcArrayType, dstArrayElement, dstArrayType);
                        Array.set(dstArray, iArray, dstArrayElement);
                    }

                    // Set the array attribute
                    dstSetter.invoke(destination, dstArray);
                } else {
                    // Create a new instance of the sub object
                    dstSetter.invoke(destination, dstGetterReturnType.newInstance());

                    // Recursivity
                    copyInto(srcGetterValue, srcGetterReturnType, dstGetter.invoke(destination), dstGetterReturnType);
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException ex) {
            // Just wrap the exception with a clearer context and throw
            throw new ReflectionException(ex);
        }
    }

    @SuppressWarnings("rawtypes")
    private static Collection createCollection(Class<?> collectionClass) throws InstantiationException,
            IllegalAccessException
    {
        if (!Collection.class.isAssignableFrom(collectionClass)) {
            throw new IllegalArgumentException("createCollection() only accepts Set, List and Bag.");
        }

        if (Modifier.isAbstract(collectionClass.getModifiers()) && !collectionClass.isInterface()) {
            // Class is abstract, we don't support it, because the case is rare
            // plus it's a pain in the ass to find the first Collection
            // interface.
            throw new UnsupportedOperationException("createCollection() does not support abstract classes.");
        }

        if (!collectionClass.isInterface()) {
            // the collection's class is a not an interface,
            // just create a instance of this type.
            return (Collection) collectionClass.newInstance();
        }

        if (Set.class.isAssignableFrom(collectionClass)) {
            // Default Set is HashSet
            return (Collection) HashSet.class.newInstance();
        }

        if (List.class.isAssignableFrom(collectionClass)) {
            // Default List is ArrayList
            return (Collection) ArrayList.class.newInstance();
        }

        if (Bag.class.isAssignableFrom(collectionClass)) {
            // Default Bag is HashBag
            return (Collection) HashBag.class.newInstance();
        }

        // Not a supported collection
        throw new UnsupportedOperationException();
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
    public static <TSource, TDestination> TDestination generateBean(TSource source, Class<TDestination> dstClass)
    {
        if (source == null) {
            throw new ArgumentNullException("source");
        }

        if (null == dstClass) {
            throw new ArgumentNullException("dstClass");
        }

        TDestination destination;

        try {
            // Get a new instance of the destination bean.
            destination = (TDestination) dstClass.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new ReflectionException(ex);
        }

        // Copy the source into the destination.
        copyInto(source, source.getClass(), destination, dstClass);

        // Return the filled destination.
        return destination;
    }

    /**
     * Get the underlying class for a type, or null if the type is a variable
     * type.
     * 
     * @param type the type
     * @return the underlying class
     */
    @SuppressWarnings("rawtypes")
    public static Class<?> getClass(Type type)
    {
        if (type instanceof Class) {
            return (Class) type;
        } else if (type instanceof ParameterizedType) {
            return getClass(((ParameterizedType) type).getRawType());
        } else if (type instanceof GenericArrayType) {
            Type componentType = ((GenericArrayType) type).getGenericComponentType();
            Class<?> componentClass = getClass(componentType);
            if (componentClass != null) {
                return Array.newInstance(componentClass, 0).getClass();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
