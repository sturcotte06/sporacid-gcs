package gcs.webapp.utils;

import lombok.Getter;
import gcs.webapp.utils.HttpMethod;

/*@Retention(RetentionPolicy.RUNTIME)
public @interface HandledByHttpService 
{
	String path();
	HttpMethod method() default HttpMethod.Get;
}*/

public class HandledByHttpService 
{
   @Getter
   private String path;
   
   @Getter
   private HttpMethod method;
   
   public HandledByHttpService(String path, HttpMethod method)
   {
      this.path = path;
      this.method = method;
   }
}
