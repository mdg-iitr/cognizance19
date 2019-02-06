package com.mdgiitr.karthik.cognizance19.network.client;

import com.google.gson.Gson;
import com.mdgiitr.karthik.cognizance19.network.service.DownloadService;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.mdgiitr.karthik.cognizance19.network.service.DownloadService.BASE_URL;

public class DownloadClient {

    Retrofit retrofit;
    HttpLoggingInterceptor interceptor;
    OkHttpClient client;
    Gson gson;
    DownloadService downloadService;

    public DownloadClient() {

        interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        downloadService = retrofit.create(DownloadService.class);

    }

    public Observable<ResponseBody> downloadFile(String d) {
        return downloadService.downloadFile(d).subscribeOn(Schedulers.io());
    }

}
