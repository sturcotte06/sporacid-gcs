package gcs.webapp.utils.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public final class ReflectionUtils
{
	/**
	 * Get an executing method from the stack trace at the specified depth
	 * @param depth	Depth within the stack trace
	 * @return			The method name in the stack trace
	 */
	public static String getMethodName(final int depth)
	{
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		return ste[ste.length - 1 - depth].getMethodName(); 
	}
	
	/**
	 * Get the first executing method name from the stack trace for the specified class
	 * @param forClass	Class object on which we'll test stack trace elements
	 * @return				Name of the current executing method for the class
	 */
	public static String getCurrentMethodName(Class<?> forClass)
	{
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
	  
		// Get the last executing method for the specified class
		String currentMethodName = null;
		for(int i = 0; i < ste.length; i++) {
			// If current stack strace element class name matches the specified class,
			// then we found the current method name
			if (ste[i].getClassName().equals(forClass.getName())) {
				currentMethodName = ste[i].getMethodName();
				break;
			}
		}
	  
		return currentMethodName; 
	}
	
	/**
	 * Get the metadata for the first executing method from the stack trace for the specified class
	 * @param forClass	Class object on which we'll test stack trace elements
	 * @return				The method metadata or null
	 */
	public static Method getFirstCurrentMethodMetadata(Class<?> forClass) 
	{
		Method method = null;
		final String methodName = getCurrentMethodName(forClass);
		final Method[] potentialMethods = forClass.getMethods();
		for(Method potentialMethod : potentialMethods) {
			if(potentialMethod.getName().equals(methodName)) {
				method = potentialMethod;
				break;
			}
		}
		
		return method;
	}
	

	/**
	 * Returns whether the object is a wrapper for a primitive type or not.
	 * @param o An object to test
	 * @return	Whether the object is a wrapper for a primitive type or not
	 */
	public static boolean isPrimitive(Object o)
	{
		final Class<?>[] wrapperTypes = {
			Boolean.class,
			Character.class,
			Byte.class,
			Short.class,
			Integer.class,
			Long.class,
			Float.class,
			Double.class,
			Void.class
		};
		
		boolean isPrimitive = false;
		
		if(o != null) {
			for (int i = 0; i < wrapperTypes.length; i++) {
				if (wrapperTypes[i].equals(o.getClass())) {
					isPrimitive = true;
					break;
				}
			}
		}
		
		return isPrimitive;
	}
	
	/**
	 * Method that return a dictionary of all properties of an object and its values.
	 * 
	 * @param object		An object from which we want properties
	 * @param classObj		The class of the object
	 * @return				A dictionary of all properties of the object and its values
	 * @throws IllegalAccessException	Typical exception when dealing with reflection
	 */
	public static <E> Map<String, Object> getObjectProperties(Object object, Class<E> classObj) 
			throws IllegalAccessException 
	{
		Map<String, Object> fieldsMap = new HashMap<String, Object>();
		if(object != null && classObj != null) {
			fieldsMap = getObjectProperties(object, classObj, null);
		}
		
		return fieldsMap;
	}
	

	/**
	 * 
	 * @param obj
	 * @param classObj
	 * @return
	 */
	public static <E> E deepCopy(E obj) 
	{
	   return deepCopy(obj, new Gson());
	}

	/**
	 * 
	 * @param obj
	 * @param classObj
	 * @param jsonSerializer
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <E> E deepCopy(E obj, Gson jsonSerializer) 
	{
		E clone = null;
		if (obj != null) {
			String json = jsonSerializer.toJson(obj);
			clone = (E) jsonSerializer.fromJson(json, obj.getClass());
		}
	   return clone;
	}
	
	/**
	 * Recursive method that return a dictionary of all properties of an object and its values.
	 * 
	 * @param object		An object from which we want properties
	 * @param classObj		The class of the object
	 * @param currentPath	Current property path
	 * @return				A dictionary of all properties of the object and its values
	 * @throws IllegalAccessException	Typical exception when dealing with reflection
	 */
	private static <E> Map<String, Object> getObjectProperties(Object object, Class<E> classObj, String currentPath) 
    		throws IllegalAccessException
	{
		Map<String, Object> fieldsMap = new HashMap<String, Object>();
		Field[] valueObjFields = classObj.getDeclaredFields();
		
		// compare values now
		for (int i = 0; i < valueObjFields.length; i++) {
			String fieldName = (currentPath != null) ? currentPath + "." + valueObjFields[i].getName() : valueObjFields[i].getName();
			valueObjFields[i].setAccessible(true);
	
			Object newObj = valueObjFields[i].get(object);
			if(newObj != null) {
				// If the property is either a primitive type, a Date or a String, 
				// toString() can be called to get the value.
				if (ReflectionUtils.isPrimitive(newObj) ||
						newObj instanceof Date ||
						newObj instanceof String) {
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
}
