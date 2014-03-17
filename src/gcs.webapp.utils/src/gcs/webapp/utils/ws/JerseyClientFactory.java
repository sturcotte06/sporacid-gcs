package gcs.webapp.utils.ws;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.client.urlconnection.HTTPSProperties;

/**
 * 
 * @author Simon Turcotte-Langevin
 *
 */
public class JerseyClientFactory 
{
	/**
	 * 
	 */
	private ClientConfig clientConfig;
	
	/**
	 * Creates a static configuration for all jersey clients
	 * @return A jersey client configuration object
	 */
	private static ClientConfig generateConfiguration() 
	{
		final TrustManager[] certs = new TrustManager[] {
            new X509TrustManager() {
				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				@Override
				public void checkClientTrusted(X509Certificate[] chain,
						String authType) throws CertificateException 
				{ }
				@Override
				public void checkServerTrusted(X509Certificate[] chain,
						String authType) throws CertificateException 
				{ }
			}
	    };
		
	    SSLContext ctx = null;
	    
	    try {
	        ctx = SSLContext.getInstance("TLS");
	        ctx.init(null, certs, new SecureRandom());
	    } catch (java.security.GeneralSecurityException e) {
	    	e.printStackTrace();
	    }
	    
		// Create all-trusting host name verifier
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};

		// Install the all-trusting host verifier
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
	    HttpsURLConnection.setDefaultSSLSocketFactory(ctx.getSocketFactory());
	    
	    ClientConfig config = new DefaultClientConfig();
	    try {
	    	config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, true);
		    config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(
		        new HostnameVerifier() {
					@Override
					public boolean verify(String arg0, SSLSession arg1) {
						return false;
					}
		        }, 
		        ctx
		    ));
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }
	    
	    return config;
	}
	
	
	/**
	 * Creates a jersey client to consume web services through ssl
	 * @return A jersey client to consume web services through ssl
	 */
	public Client createClient() 
	{
		if (clientConfig == null) {
			clientConfig = generateConfiguration();
		}
		
		final Client client = Client.create(clientConfig);
	    return client;
	}
}
