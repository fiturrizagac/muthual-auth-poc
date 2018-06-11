package com.frabef.twtls;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

import com.frabef.twtls.httpclient.GetHtml;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class WebConfig {

    private final String P12_TYPE = "pkcs12";
    private final String P12_PATH =
        "/home/frankieic/devenv/source-code/pocs/muthual-auth-poc/certs/nginx/client/client.p12";
    private final String P12_PASS = "topsecretclientp12";
    private final String JKS_TYPE = "jks";
    private final String JKS_PATH =
        "/home/frankieic/devenv/source-code/pocs/muthual-auth-poc/certs/nginx/server/client-ssl.fraber.com.jks";
    private final String JKS_PASS = "topsecretjks";

    @Bean
    public GetHtml getHtml() throws Exception {
        return retrofit().create(GetHtml.class);
    }

    @Bean
    public Retrofit retrofit() throws Exception {

        return new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://localhost")
            .client(this.okHttpClient())
            .build();
    }

    @Bean
    public OkHttpClient okHttpClient() throws Exception {
        return new OkHttpClient.Builder()
            .sslSocketFactory(this.sslContext().getSocketFactory())
            .hostnameVerifier((String s, SSLSession sslSession) -> true)
            .build();
    }

    public SSLContext sslContext() throws Exception {

        SSLContext sslContext = SSLContext.getInstance("tls");
        sslContext.init(
            this.keyManagerFactory().getKeyManagers(),
            this.trustManagerFactory().getTrustManagers(),
            new SecureRandom());

        return sslContext;
    }

    public KeyManagerFactory keyManagerFactory()
        throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, UnrecoverableKeyException {

        char[] password = P12_PASS.toCharArray();

        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(this.keyStore(P12_PATH, password,P12_TYPE), password);
        return keyManagerFactory;
    }

    public TrustManagerFactory trustManagerFactory()
        throws NoSuchAlgorithmException, CertificateException, KeyStoreException, IOException {

        char[] password = JKS_PASS.toCharArray();

        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(this.keyStore(JKS_PATH,password, JKS_TYPE));
        return trustManagerFactory;
    }

    public KeyStore keyStore(final String path, final char[] password, final String type)
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

}
