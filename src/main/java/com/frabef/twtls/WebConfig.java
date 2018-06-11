package com.frabef.twtls;

import com.frabef.twtls.httpclient.GetHtml;
import com.frabef.twtls.services.commons.TwoWayTlsOkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class WebConfig {

    @Value("${poc.2waytls.client.keystore.path}")
    private String clientKeyStorePath;
    @Value("${poc.2waytls.client.keystore.password}")
    private String clientKeyStorePassword;
    @Value("${poc.2waytls.client.keystore.type}")
    private String clientKeyStoreType;
    @Value("${poc.2waytls.server.keystore.path}")
    private String serverKeyStorePath;
    @Value("${poc.2waytls.server.keystore.password}")
    private String serverKeyStorePassword;
    @Value("${poc.2waytls.server.url}")
    private String serverURL;

    @Bean
    public GetHtml getHtml() throws Exception {
        return retrofit().create(GetHtml.class);
    }

    @Bean
    public Retrofit retrofit() throws Exception {

        return new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(serverURL)
            .client(this.okHttpClient())
            .build();
    }

    @Bean
    public OkHttpClient okHttpClient() throws Exception {

        TwoWayTlsOkHttpClient twoWayTlsOkHttpClient =
            new TwoWayTlsOkHttpClient(clientKeyStorePath, clientKeyStorePassword, serverKeyStorePath, serverKeyStorePassword);
        twoWayTlsOkHttpClient.setClientKeyStoreType(clientKeyStoreType);
        return twoWayTlsOkHttpClient.getOkHttpClient();
    }
}
