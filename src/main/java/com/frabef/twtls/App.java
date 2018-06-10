package com.frabef.twtls;

import com.frabef.twtls.httpclient.GetHtml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SpringBootApplication
@RestController
public class App {

    @Autowired
    private GetHtml getHtml;

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }


    @GetMapping("/test")
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
