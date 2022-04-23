package com.example.android.rxweather.retrofit;

import static com.example.android.rxweather.util.GsonClientUtil.createGson;
import static com.example.android.rxweather.util.OkHttpClientUtil.buildOkHttpClient;
import com.example.android.rxweather.util.AppConstants;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetRetrofitObj {
    static WeatherService retrofitService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(buildOkHttpClient())
                .build();
        return retrofit.create(WeatherService.class);
    }
}
