package com.example.android.rxweather.retrofit;

import com.example.android.rxweather.datamodel.Dto_RX;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("VisualCrossingWebServices/rest/services/timeline/{cityName}")
    Observable<Dto_RX> getDto(@Path("cityName") String cityName,//Seattle
                              @Query("unitGroup") String unitGroup,//metric
                              @Query("key") String key,//UDR74JLWCB3CRZBZQSTL3AVQH
                              @Query("contentType") String contentType);//json
}