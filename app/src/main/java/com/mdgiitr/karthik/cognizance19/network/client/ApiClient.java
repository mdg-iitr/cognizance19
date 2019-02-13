package com.mdgiitr.karthik.cognizance19.network.client;

import com.google.gson.Gson;
import com.mdgiitr.karthik.cognizance19.models.CenterstageOrDepartmentalEventsResponse;
import com.mdgiitr.karthik.cognizance19.models.EventResponse;
import com.mdgiitr.karthik.cognizance19.models.FbGoogleLoginModel;
import com.mdgiitr.karthik.cognizance19.models.GeneralResponse;
import com.mdgiitr.karthik.cognizance19.models.HomeMenuWorkshopResponse;
import com.mdgiitr.karthik.cognizance19.models.LoginResponse;
import com.mdgiitr.karthik.cognizance19.models.RegEventsResponse;
import com.mdgiitr.karthik.cognizance19.models.SignupResponse;
import com.mdgiitr.karthik.cognizance19.models.UserSPPResponseModel;
import com.mdgiitr.karthik.cognizance19.network.service.ApiService;

import java.io.File;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
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

    public Observable<SignupResponse> signUpRemote(String email, String role, String password, String name) {

        return apiService.signUp(email, "remote", role, password, name).subscribeOn(Schedulers.io());

    }

    public Observable<LoginResponse> userLogin(String email, String password) {

        return apiService.login(email, password).subscribeOn(Schedulers.io());

    }

    public Observable<ResponseBody> updateUserImage(String token, File file) {

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        return apiService.updateImage("Token " + token, body).subscribeOn(Schedulers.io());

    }

    public Observable<GeneralResponse> logout(String token) {

        return apiService.userLogout("Token " + token).subscribeOn(Schedulers.io());

    }

    public Observable<GeneralResponse> updatePassword(String token, String currentPassword, String newPassword1, String newPassword2) {

        return apiService.updatePassword("Token " + token, currentPassword, newPassword1, newPassword2).subscribeOn(Schedulers.io());

    }

    public Observable<UserSPPResponseModel> getUserDetails(String token) {

        return apiService.getUserDetails("Token " + token).subscribeOn(Schedulers.io());

    }

    public Observable<ResponseBody> uploadExcel(String token, File file) {

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        return apiService.uploadExcel("Token " + token, body).subscribeOn(Schedulers.io());
    }

    public Observable<HomeMenuWorkshopResponse> fetchWorkshops() {
        return apiService.fetchWorkshops().subscribeOn(Schedulers.io());
    }

    public Observable<CenterstageOrDepartmentalEventsResponse> fetchEvents() {
        return apiService.fetchEvents().subscribeOn(Schedulers.io());
    }

    public Observable<EventResponse> fetchSpecificEventDetails(String id) {
        return apiService.getSpecificEventDetails(id).subscribeOn(Schedulers.io());
    }

    public Observable<ResponseBody> updateUserDetails(String token, String state, String college, String address, String city, String gender, String year, String branch, String pincode, String mobile) {
        return apiService.updateUserDetails("Token " + token, state, college, address, city, gender, year, branch, pincode, mobile).subscribeOn(Schedulers.io());
    }

    public Observable<SignupResponse> fbGoogleLogin(FbGoogleLoginModel loginModel) {
        return apiService.signupFbGoogle(loginModel).subscribeOn(Schedulers.io());
    }

    public Observable<RegEventsResponse> fetchRegisteredEvents(String token) {
        return apiService.getRegisteredEvents("Token " + token).subscribeOn(Schedulers.io());
    }

    public Observable<GeneralResponse> useReferralCode(String token, String code) {
        return apiService.useReferralCode("Token " + token, code).subscribeOn(Schedulers.io());
    }

    public Observable<GeneralResponse> registerForEvent(String token, String eventId, ArrayList<String> teamMembers) {

        String members = "";
        for (String s : teamMembers) {
            if (!s.trim().isEmpty())
                members = members + s + ",";
        }
        if (!members.isEmpty())
            members = members.substring(0, members.length() - 1);
        return apiService.registerForEvent("Token " + token, eventId, members).subscribeOn(Schedulers.io());

    }

}
