package com.example.android.rxweather.retrofit;

import com.example.android.rxweather.PersonTest;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TestService {
    @FormUrlEncoded
    @POST("modify")
    Call<PersonTest> modifyPerson(@Field("name") String name);

    @GET("person/{id}")
    Call<PersonTest> fetchPersonById(@Path("id") int id);
}