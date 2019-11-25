package com.mengk.viewmodellivedata.common.http;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Desc
 * Date 2019/11/1
 * author mengk
 */
public class RetrofitManager {

    private static final String BASE_URL = "";
    private static final String BASE_UPLOAD_URL = "http://multimedia.test.qiutianxia.com";

    private RetrofitManager() {}

    private static RetrofitManager instance;

    public static RetrofitManager getInstance() {
        if (instance == null) {
            synchronized (RetrofitManager.class) {
                if (instance == null) {
                    instance = new RetrofitManager();
                }
            }
        }
        return instance;
    }

    public Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("BASE_URL")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public Retrofit getUploadRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_UPLOAD_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
