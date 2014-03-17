package gcs.webapp.utils;

import gcs.webapp.utils.HttpMethod;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface HandledByHttpService 
{
	String path();
	HttpMethod method() default HttpMethod.Get;
}
