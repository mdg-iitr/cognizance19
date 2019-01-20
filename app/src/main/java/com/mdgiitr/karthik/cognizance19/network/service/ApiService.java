package com.mdgiitr.karthik.cognizance19.network.service;

import com.mdgiitr.karthik.cognizance19.models.SignupResponse;

import io.reactivex.Observable;
import retrofit2.http.POST;

public interface ApiService {

    String BASE_URL = "https://api.cognizance.org.in//api/";

    @POST("session/users/signup/:role")
    Observable<SignupResponse> signUp(String email, String type, String role, String password);

}
