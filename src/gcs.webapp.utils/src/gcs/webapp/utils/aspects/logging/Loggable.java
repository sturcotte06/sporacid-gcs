package gcs.webapp.utils.aspects.logging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
public @interface Loggable
{
    /** The logging level at which method entering will be logged. */
    LoggingLevel enterLevel()   default LoggingLevel.Debug;
    /** The logging level at which method exiting will be logged. */
    LoggingLevel exitLevel()    default LoggingLevel.Information;
}
