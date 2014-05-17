package gcs.webapp.utils.aspects.logging;

/**
 * Enumeration of all logging levels. This is a "wrapper" over log4j Priority
 * class because we cannot use a class in an annotation.
 * 
 * @author Simon Turcotte-Langevin
 */
public enum LoggingLevel
{
    Trace, 
    Debug, 
    Information, 
    Warning, 
    Error, 
    Fatal
}
