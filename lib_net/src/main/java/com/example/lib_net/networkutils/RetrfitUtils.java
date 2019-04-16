package com.example.lib_net.networkutils;

import com.example.lib_net.networkutils.api.Api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrfitUtils {

    private static volatile RetrfitUtils retrfitUtils;
    private final Retrofit retrofit;

    private RetrfitUtils() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient ok = new OkHttpClient.Builder()
                .addNetworkInterceptor(loggingInterceptor)
                .connectTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Api.BASE_URL)
                .client(ok)
                .build();



    }

    public static RetrfitUtils initRetrfitUtils(){
        if (retrfitUtils==null){
            synchronized (RetrfitUtils.class){
                if (retrfitUtils==null){
                    retrfitUtils = new RetrfitUtils();
                }
            }
        }
        return retrfitUtils;
    }

    public <T> T createService(Class<T> clazz){
        return retrofit.create(clazz);
    }


    public void desttach(){
        if (retrfitUtils!=null){
            retrfitUtils = null;
        }
    }


}
