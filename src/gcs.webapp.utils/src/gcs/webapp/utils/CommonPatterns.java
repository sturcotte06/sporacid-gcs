package gcs.webapp.utils;

/**
 * Common regular expressions pattern that can be used.
 * 
 * @author Simon Turcotte-Langevin
 */
public final class CommonPatterns
{
    /** Regex to test ipv4 addresses validity. */
    public static final String Ipv4Address = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    /**
     * Regex to test a gcs username validity. Current value is equal to the ets
     * permanent code AJxxxxx.
     */
    public static final String GcsUsername = "^[aA]{1}[A-Za-z]{1}[0-9]{5}$";
}
