package com.frabef.twtls.services.commons;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

import lombok.Setter;
import okhttp3.OkHttpClient;

public class TwoWayTlsOkHttpClient {

    private final static String JKS_INSTANCE_TYPE = "JKS";
    private final static String SSL_CONTEXT_INSTANCE_TYPE = "tls";

    private OkHttpClient okHttpClient;

    private String clientKeyStorePath;
    private char[] clientKeyStorePassword;
    @Setter
    private String clientKeyStoreType;

    private String serverKeyStorePath;
    private char[] serverKeyStorePassword;
    @Setter
    private String serverKeyStoreType;

    public TwoWayTlsOkHttpClient(final String clientKeyStorePath,
                                 final String clientKeyStorePassword,
                                 final String serverKeyStorePath,
                                 final String serverKeyStorePassword) {

        this.clientKeyStorePath = clientKeyStorePath;
        this.clientKeyStorePassword = clientKeyStorePassword.toCharArray();
        clientKeyStoreType = JKS_INSTANCE_TYPE;
        this.serverKeyStorePath = serverKeyStorePath;
        this.serverKeyStorePassword = serverKeyStorePassword.toCharArray();
        serverKeyStoreType = JKS_INSTANCE_TYPE;

    }

    protected SSLContext sslContext()
        throws UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException,
        IOException, KeyManagementException {

        SSLContext sslContext = SSLContext.getInstance(SSL_CONTEXT_INSTANCE_TYPE);
        sslContext.init(
            this.keyManagerFactory().getKeyManagers(),
            this.trustManagerFactory().getTrustManagers(),
            new SecureRandom());

        return sslContext;
    }

    protected KeyManagerFactory keyManagerFactory()
        throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, UnrecoverableKeyException {

        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(
            this.keyStore(clientKeyStorePath, clientKeyStorePassword, clientKeyStoreType),
            clientKeyStorePassword);
        return keyManagerFactory;
    }

    protected TrustManagerFactory trustManagerFactory()
        throws NoSuchAlgorithmException, CertificateException, KeyStoreException, IOException {

        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(this.keyStore(serverKeyStorePath, serverKeyStorePassword, serverKeyStoreType));
        return trustManagerFactory;
    }

    protected KeyStore keyStore(final String path, final char[] password, final String type)
        throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException {

        InputStream is = null;
        KeyStore keyStore = null;

        try {
            is = new FileInputStream(path);

            keyStore = KeyStore.getInstance(type);
            keyStore.load(is, password);

        } finally {
            is.close();
        }

        return keyStore;
    }

    public OkHttpClient getOkHttpClient() throws Exception {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                .sslSocketFactory(sslContext().getSocketFactory())
                .hostnameVerifier((String s, SSLSession sslSession) -> true)
                .build();
        }
        return okHttpClient;
    }
}
