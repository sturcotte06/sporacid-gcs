package gcs.webservices.client;

import gcs.webapp.utils.HttpMethod;

public class HttpServiceRoute
{
    private String path;
    private HttpMethod method;

    public HttpServiceRoute(String path, HttpMethod method)
    {
        this.setPath(path);
        this.setMethod(method);
    }

    /**
     * @return the path
     */
    public String getPath()
    {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path)
    {
        this.path = path;
    }

    /**
     * @return the method
     */
    public HttpMethod getMethod()
    {
        return method;
    }

    /**
     * @param method the method to set
     */
    public void setMethod(HttpMethod method)
    {
        this.method = method;
    }
}
