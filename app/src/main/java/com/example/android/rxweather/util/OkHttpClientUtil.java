package com.example.android.rxweather.util;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpClientUtil {
    public static OkHttpClient buildOkHttpClient(){
        return new OkHttpClient
                .Builder()
                .addInterceptor
                        (new HttpLoggingInterceptor()
                                .setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build();
    }
}
