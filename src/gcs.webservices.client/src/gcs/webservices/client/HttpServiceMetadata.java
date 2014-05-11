package gcs.webservices.client;

import gcs.webapp.utils.HttpMethod;

public class HttpServiceMetadata
{
   private String path;
   private HttpMethod method;
   
   public HttpServiceMetadata(String path, HttpMethod method)
   {
      this.path = path;
      this.method = method;
   }
   
   public HttpMethod getMethod()
   {
      return method;
   }
  
   public String getPath()
   {
      return path;
   }
}
