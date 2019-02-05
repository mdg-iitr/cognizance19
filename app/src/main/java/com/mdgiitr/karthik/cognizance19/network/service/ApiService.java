package com.mdgiitr.karthik.cognizance19.network.service;

import com.mdgiitr.karthik.cognizance19.models.CenterstageOrDepartmentalEventsResponse;
import com.mdgiitr.karthik.cognizance19.models.GeneralResponse;
import com.mdgiitr.karthik.cognizance19.models.HomeMenuWorkshopResponse;
import com.mdgiitr.karthik.cognizance19.models.LoginResponse;
import com.mdgiitr.karthik.cognizance19.models.SignupResponse;
import com.mdgiitr.karthik.cognizance19.models.UserSPPResponseModel;


import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiService {

    String BASE_URL = "https://api.cognizance.org.in/api/";

    @FormUrlEncoded
    @POST("session/users/signup/{role}")
    Observable<SignupResponse> signUp(@Field("email") String email, @Field("type") String type, @Path("role") String role, @Field("password") String password, @Field("name") String name);

    @FormUrlEncoded
    @POST("session/users/login")
    Observable<LoginResponse> login(@Field("email") String email, @Field("password") String password);

    @Multipart
    @POST("users/upload/image")
    Observable<ResponseBody> updateImage(@Header("Authorization") String token, @Part MultipartBody.Part file);

    @GET("session/users/logout")
    Observable<GeneralResponse> userLogout(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("users/password/update")
    Observable<GeneralResponse> updatePassword(@Header("Authorization") String token, @Field("currentPassword") String currentPassword, @Field("newPassword1") String newPassword1, @Field("newPassword2") String newPassword2);

    @GET("users")
    Observable<UserSPPResponseModel> getUserDetails(@Header("Authorization") String token);

    @POST("users/spp/upload/excel")
    Observable<ResponseBody> uploadExcel(@Header("Authorization") String token, @Part MultipartBody.Part file);

    @GET("android/workshops")
    Observable<HomeMenuWorkshopResponse> fetchWorkshops();

    @GET("android/events")
    Observable<CenterstageOrDepartmentalEventsResponse> fetchEvents();


}
