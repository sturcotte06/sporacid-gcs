package gcs.webapp.utils.reflect;

import java.lang.reflect.Member;
import java.lang.reflect.Field;

import com.google.common.base.Predicate;

public class ReflectionsExtension
{
    /**
     * 
     * @return
     */
    public static Predicate<Field> isPrimitive()
    {
        return new Predicate<Field>() {
            @Override
            public boolean apply(Field field) {
                return ReflectionUtils.isPrimitive(field.getType());
            }
        };   
    }
    
    /**
     * 
     * @return
     */
    public static Predicate<Field> isArray()
    {
        return new Predicate<Field>() {
            @Override
            public boolean apply(Field field) {
                return field.getType().isArray();
            }
        };   
    }
    
    /**
     * 
     * @param predicate
     * @return
     */
    public static <T extends Member> Predicate<T> not(final Predicate<T> predicate) 
    {
        return new Predicate<T>() {
            @Override
            public boolean apply(T obj) {
                return !predicate.apply(obj);
            }
        };        
    }
    
    /**
     * 
     * @param predicates
     * @return
     */
    @SafeVarargs
    public static <T extends Member> Predicate<T> and(final Predicate<T>... predicates) 
    {
        return new Predicate<T>() {
            @Override
            public boolean apply(T obj) {
                for (final Predicate<T> predicate : predicates) {
                    if (!predicate.apply(obj)) {
                        return false;
                    }
                }

                return true;
            }
        };    
    }
    
    /**
     * 
     * @param predicates
     * @return
     */
    @SafeVarargs
    public static <T extends Member> Predicate<T> or(final Predicate<T>... predicates) 
    {
        return new Predicate<T>() {
            @Override
            public boolean apply(T obj) {
                for (final Predicate<T> predicate : predicates) {
                    if (predicate.apply(obj)) {
                        return true;
                    }
                }

                return false;
            }
        };    
    }
}
