package com.frabef.twtls.services.http;

import com.frabef.twtls.services.clients.http.GetHtml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@RestController
@RequestMapping("/test")
public class TestRestServices {

    @Autowired
    private GetHtml getHtml;

    @GetMapping
    public void test() throws Exception {

        Call<ResponseBody> call = getHtml.getPage();

        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println(response.body().source().toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
