package com.mdgiitr.karthik.cognizance19.network.client;

import com.google.gson.Gson;
import com.mdgiitr.karthik.cognizance19.models.LoginResponse;
import com.mdgiitr.karthik.cognizance19.models.SignupResponse;
import com.mdgiitr.karthik.cognizance19.network.service.ApiService;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);

    }

    public Observable<SignupResponse> signUpRemote(String email, String role, String password) {

        return apiService.signUp(email, "remote", role, password);

    }

    public Observable<LoginResponse> userLogin(String email, String password) {

        return apiService.login(email, password);

    }
}
