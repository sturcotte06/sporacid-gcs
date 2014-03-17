package gcs.webapp.utils.app.security;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface SecureModule 
{
	String name();
}
