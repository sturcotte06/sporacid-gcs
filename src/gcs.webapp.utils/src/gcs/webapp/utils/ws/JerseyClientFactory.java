package gcs.webapp.utils.ws;

import gcs.webapp.utils.exceptions.InternalException;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.client.urlconnection.HTTPSProperties;

/**
 * @author Simon Turcotte-Langevin
 */
public final class JerseyClientFactory
{
    /** The client configuration from whcih we'll create all jersey clients. */
    private ClientConfig clientConfig;

    /**
     * Creates a static configuration for all jersey clients
     * 
     * @return A jersey client configuration object
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     */
    private static ClientConfig generateConfiguration() throws KeyManagementException, NoSuchAlgorithmException
    {
        /* final TrustManager[] certs = new TrustManager[] { new
         * X509TrustManager() {
         * 
         * @Override public X509Certificate[] getAcceptedIssuers() { return
         * null; }
         * 
         * @Override public void checkServerTrusted(X509Certificate[] arg0,
         * String arg1) throws CertificateException { }
         * 
         * @Override public void checkClientTrusted(X509Certificate[] arg0,
         * String arg1) throws CertificateException { } } };
         * 
         * SSLContext sslContext = null; sslContext =
         * SSLContext.getInstance("TLS"); sslContext.init(null, certs, new
         * SecureRandom());
         * 
         * // Create all-trusting host name verifier HostnameVerifier
         * allHostsValid = (hostname, session) -> true;
         * 
         * // Install the all-trusting host verifier
         * HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
         * HttpsURLConnection
         * .setDefaultSSLSocketFactory(sslContext.getSocketFactory());
         * 
         * ClientConfig config = new DefaultClientConfig();
         * config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
         * true);
         * config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES,
         * new HTTPSProperties((hostname, session) -> false, sslContext));
         * 
         * return config; */
        return null;
    }

    /**
     * Creates a jersey client to consume web services through ssl.
     * 
     * @return A jersey client to consume web services through ssl.
     */
    public Client createClient() throws InternalException
    {
        if (clientConfig == null) {
            try {
                clientConfig = generateConfiguration();
            } catch (KeyManagementException | NoSuchAlgorithmException ex) {
                throw new InternalException("utils_jerseyclientfactory_cannotgenerateconfig",
                        "Couldn't create a client for the jersey web services.", ex);
            }
        }

        return Client.create(clientConfig);
    }
}
