package com.mdg.iitr.cognizance19.network.service;

import com.mdg.iitr.cognizance19.models.CenterstageOrDepartmentalEventsResponse;
import com.mdg.iitr.cognizance19.models.EventResponse;
import com.mdg.iitr.cognizance19.models.FbGoogleLoginModel;
import com.mdg.iitr.cognizance19.models.GeneralResponse;
import com.mdg.iitr.cognizance19.models.LoginResponse;
import com.mdg.iitr.cognizance19.models.NewSchedule;
import com.mdg.iitr.cognizance19.models.PaymentRequestModel;
import com.mdg.iitr.cognizance19.models.RegEventsResponse;
import com.mdg.iitr.cognizance19.models.SignupResponse;
import com.mdg.iitr.cognizance19.models.SpotlightResponse;
import com.mdg.iitr.cognizance19.models.SpotlightSchedule;
import com.mdg.iitr.cognizance19.models.TeamResponse;
import com.mdg.iitr.cognizance19.models.UserSPPResponseModel;
import com.mdg.iitr.cognizance19.models.WorkshopResponse;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
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

    @POST("session/users/oauth/cogni_user")
    Observable<SignupResponse> signupFbGoogle(@Body FbGoogleLoginModel loginModel);

    @FormUrlEncoded
    @POST("session/users/login")
    Observable<LoginResponse> login(@Field("email") String email, @Field("password") String password);

    @Multipart
    @POST("users/upload/image")
    Observable<GeneralResponse> updateImage(@Header("Authorization") String token, @Part MultipartBody.Part file);

    @GET("session/users/logout")
    Observable<GeneralResponse> userLogout(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("users/password/update")
    Observable<GeneralResponse> updatePassword(@Header("Authorization") String token, @Field("currentPassword") String currentPassword, @Field("newPassword1") String newPassword1, @Field("newPassword2") String newPassword2);

    @FormUrlEncoded
    @POST("users/registrationReferalCode")
    Observable<GeneralResponse> useReferralCode(@Header("Authorization") String token, @Field("referalCode") String code);

    @FormUrlEncoded
    @POST("users/event/{id}/register")
    Observable<GeneralResponse> registerForEvent(@Header("Authorization") String token, @Path("id") String id, @Field("teamMembers") String teamMembers);

    @FormUrlEncoded
    @POST("users/settings")
    Observable<GeneralResponse> updateUserDetails(@Header("Authorization") String token, @Field("name") String name, @Field("city") String city, @Field("address") String address, @Field("pincode") String pincode, @Field("mobile") String mobile);

    @GET("users")
    Observable<UserSPPResponseModel> getUserDetails(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("users")
    Observable<ResponseBody> updateUserDetails(@Header("Authorization") String token,
                                               @Field("state") String state,
                                               @Field("college") String college,
                                               @Field("address") String address,
                                               @Field("city") String city,
                                               @Field("gender") String gender,
                                               @Field("year") String year,
                                               @Field("branch") String branch,
                                               @Field("pincode") String pincode,
                                               @Field("mobile") String mobile);

    @POST("users/spp/upload/excel")
    Observable<ResponseBody> uploadExcel(@Header("Authorization") String token, @Part MultipartBody.Part file);

    @FormUrlEncoded
    @POST("users/event/{id}/team/{teamID}/add_member")
    Observable<ResponseBody> addMember(@Header("Authorization") String token, @Path("id") String id, @Path("teamID") String teamID, @Field("memberCogniId") String memberCogniId);

    @FormUrlEncoded
    @POST("users/event/{id}/team/{teamID}/remove_member")
    Observable<ResponseBody> removeMember(@Header("Authorization") String token, @Path("id") String id, @Path("teamID") String teamID, @Field("memberCogniId") String memberCogniId);

    @Multipart
    @POST("users/event/{id}/abstract")
    Observable<ResponseBody> submitAbstract(@Header("Authorization") String token, @Path("id") String id, @Part MultipartBody.Part file);

    @GET("workshops")
    Observable<WorkshopResponse> fetchWorkshops();

    @GET("android/events")
    Observable<CenterstageOrDepartmentalEventsResponse> fetchEvents();

    @GET("android/event/{id}")
    Observable<EventResponse> getSpecificEventDetails(@Path("id") String id);

    @GET("users/events/registered")
    Observable<RegEventsResponse> getRegisteredEvents(@Header("Authorization") String token);

    @GET("users/event/{id}/team")
    Observable<TeamResponse> getTeam(@Header("Authorization") String token, @Path("id") String id);

    @GET("users/event/{id}/unregister")
    Observable<GeneralResponse> unregister(@Header("Authorization") String token, @Path("id") String id);

    @FormUrlEncoded
    @POST("users/verifyDetails")
    Observable<GeneralResponse> verifyDetails(@Header("Authorization") String token, @Field("gender") String gender, @Field("mobile") String mobile);

    @POST("users/payments/init")
    Observable<GeneralResponse> getPaymentUrl(@Header("Authorization") String token, @Body PaymentRequestModel paymentRequestModel);

    @GET("g/spotlight")
    Observable<SpotlightResponse> fetchSpotlightEvents();


    @GET("events/sorted")
    Observable<NewSchedule> fetchSchedule();

}
