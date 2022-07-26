package com.example.android.rxweather;

import static com.example.android.rxweather.util.GsonClientUtil.createGson;
import static com.example.android.rxweather.util.OkHttpClientUtil.buildOkHttpClient;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import android.content.Context;

import androidx.annotation.NonNull;

import com.example.android.rxweather.retrofit.TestService;
import com.example.android.rxweather.util.NetworkCheck;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Objects;

import mockwebserver3.Dispatcher;
import mockwebserver3.MockResponse;
import mockwebserver3.MockWebServer;
import mockwebserver3.RecordedRequest;
import okhttp3.HttpUrl;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@RunWith(MockitoJUnitRunner.class)
public class NetworkCheckTest {
    TestService service;
    Dispatcher dispatcher;
    public MockWebServer mockWebServer = new MockWebServer();
    @Before
    public void setUp() throws Exception {
        service = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(buildOkHttpClient())
                .build()
                .create(TestService.class);

        dispatcher = new Dispatcher() {
            @NonNull
            @Override
            public MockResponse dispatch(@NonNull RecordedRequest request) throws InterruptedException {
                switch (Objects.requireNonNull(request.getPath())) {
                    case "/person/1":
                        return new MockResponse().setResponseCode(200);
                    case "/person/2":
                        return new MockResponse().setResponseCode(500);
                    case "/person/3":
                        return new MockResponse().setResponseCode(200).setBody("{\"name\": \"A\",\"age\": 11}");
                }
                return new MockResponse().setResponseCode(404);
            }
        };
    }

    @After
    public void tearDown() throws Exception {
        mockWebServer.shutdown();
    }

    @Test
    public void isNetworkConnected() {
        mockWebServer.setDispatcher(dispatcher);


    }



}