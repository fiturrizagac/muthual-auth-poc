package com.frabef.twtls.httpclient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetHtml {

    @GET("/")
    Call<ResponseBody> getPage();

}
