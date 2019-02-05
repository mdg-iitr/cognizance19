package com.mdgiitr.karthik.cognizance19.network.service;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

public interface DownloadService {

    String BASE_URL = "https://drive.google.com/";

    @POST("{url}")
    @Streaming
    Observable<ResponseBody> downloadFile(@Path("url") String url);

}
