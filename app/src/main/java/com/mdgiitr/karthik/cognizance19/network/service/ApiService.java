package com.mdgiitr.karthik.cognizance19.network.service;

import com.mdgiitr.karthik.cognizance19.models.SignupResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    String BASE_URL = "https://api.cognizance.org.in/api/";

    @FormUrlEncoded
    @POST("session/users/signup/{role}")
    Observable<SignupResponse> signUp(@Field("email") String email, @Field("type") String type, @Path("role") String role, @Field("password") String password);

}
