package com.frabef.twtls;

import com.frabef.twtls.httpclient.GetHtml;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class WebConfig {






//    private SSLContext sslContext() {
//
//        SSLContext sslContext = SSLContextBuilder.create()
//            .
//
//
//
//        return null;
//    }


    @Bean
    public Retrofit retrofit() {

        return new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://localhost")
            .client(new OkHttpClient())
            .build();
    }


    @Bean
    public GetHtml getHtml() {
        return retrofit().create(GetHtml.class);
    }

}
