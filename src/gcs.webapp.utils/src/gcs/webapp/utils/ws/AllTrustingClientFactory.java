package gcs.webapp.utils.ws;

import gcs.webapp.utils.exceptions.InternalException;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

/**
 * @author Simon Turcotte-Langevin
 */
public class AllTrustingClientFactory implements IClientFactory
{
    /**  */
    private final TrustManager[] certificates = new TrustManager[] { new X509TrustManager()
    {
        @Override
        public X509Certificate[] getAcceptedIssuers()
        {
            return null;
        }

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException
        {
        }

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException
        {
        }
    } };

    /** An all-trusting host name verifier. */
    private final HostnameVerifier hostnameVerifier = (hostname, session) -> true;

    /** The secure socket layer context. */
    private SSLContext sslContext;

    /**
     * Constructor.
     * 
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     */
    public AllTrustingClientFactory() throws KeyManagementException, NoSuchAlgorithmException
    {
        this.sslContext = SSLContext.getInstance("TLS");
        this.sslContext.init(null, certificates, new SecureRandom());
    }

    /**
     * Creates a web service client to consume web services.
     * 
     * @return A web service client to consume web services.
     * @throws InternalException
     */
    public Client createClient() throws InternalException
    {
        return ClientBuilder.newBuilder().sslContext(sslContext).hostnameVerifier(hostnameVerifier).build();
    }
}
