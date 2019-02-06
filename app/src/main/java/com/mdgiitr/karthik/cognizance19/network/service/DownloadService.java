package com.mdgiitr.karthik.cognizance19.network.service;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

public interface DownloadService {

    String BASE_URL = "https://doc-10-b0-docs.googleusercontent.com/docs/securesc/rmrg4qpf9kckk6q2n00qooh39511jgt9/gf1v043afn1h9jn5nb84rmdptirlco0a/1549389600000/09428279093433865237/08585603504612197686/";

    @GET("1fLpz2kI2OoIPwqy4nx1zBjbJVdYGpnMv")
    @Streaming
    Observable<ResponseBody> downloadFile(@Query("e") String d);

}
