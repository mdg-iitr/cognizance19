package com.mdgiitr.karthik.cognizance19.network.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mdgiitr.karthik.cognizance19.network.service.ApiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import static com.mdgiitr.karthik.cognizance19.network.service.ApiService.BASE_URL;

public class ApiClient {

    Retrofit retrofit;
    HttpLoggingInterceptor interceptor;
    OkHttpClient client;
    Gson gson;
    ApiService apiService;

    public ApiClient() {

        interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);

    }

    public void signUpRemote(String email, String role, String password) {

        apiService.signUp(email, "remote", role, password);

    }

}
