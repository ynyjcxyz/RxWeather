package com.example.android.rxweather;

import static com.example.android.rxweather.util.GsonClientUtil.createGson;
import static com.example.android.rxweather.util.OkHttpClientUtil.buildOkHttpClient;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import androidx.annotation.NonNull;
import com.example.android.rxweather.retrofit.TestService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import mockwebserver3.Dispatcher;
import mockwebserver3.MockResponse;
import mockwebserver3.MockWebServer;
import mockwebserver3.RecordedRequest;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@RunWith(JUnit4.class)
public class ServiceTestSample {
    TestService service;
    Dispatcher dispatcher;
    public MockWebServer mockWebServer = new MockWebServer();

    @Before
    public void setUp() throws Exception {
        service = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))  //实际项目中可使用真实url
//                .addConverterFactory(FastJsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(buildOkHttpClient())
                .build()
                .create(TestService.class);

        //dispatcher即为上文中的 mockWebServer.setDispatcher(dispatcher);
        dispatcher = new Dispatcher() {
            @NonNull
            @Override
            public MockResponse dispatch(@NonNull RecordedRequest request) throws InterruptedException {
                if ("/person/5".equals(request.getPath())) {           //5即为GET请求fetchPersonById(@Path("id") int id)中的请求参数
                    return new MockResponse().setResponseCode(200).setBody("{\"name\": \"A\",\"age\": 11}");
                }
                return new MockResponse().setResponseCode(404);
            }
        };
    }

    @After
    public void tearDown() throws Exception {
        mockWebServer.close();
    }

    @Test
    public void testPOST_method() throws Exception {
        mockWebServer.enqueue(new MockResponse().setBody("{\"name\": \"Tom\",\"age\": 100}"));
        Call<PersonTest> call = service.modifyPerson("Tom");
        PersonTest person = call.execute().body();

        assertNotNull(person);
        assertEquals("Tom", person.getName());
        assertEquals(100, person.getAge());
        assertEquals("POST", mockWebServer.takeRequest().getMethod());
/*        mockWebServer.enqueue(new MockResponse().setBody("{\"queryCost\":1,\"latitude\":47.6036,\"longitude\":-122.329,\"resolvedAddress\":\"Seattle,WA,UnitedStates\",\"address\":\"Seattle\",\"timezone\":\"America/Los_Angeles\",\"tzoffset\":-7.0,\"description\":\"Similartemperaturescontinuingwithachanceofrainmultipledays.\",\"days\":[{\"datetime\":\"2022-04-13\",\"datetimeEpoch\":1649833200,\"tempmax\":7.6,\"tempmin\":1.0,\"temp\":3.9,\"feelslikemax\":6.5,\"feelslikemin\":-0.2,\"feelslike\":2.8,\"dew\":0.6,\"humidity\":79.9,\"precip\":0.2,\"precipprob\":9.5,\"precipcover\":null,\"preciptype\":[\"rain\"],\"snow\":0.0,\"snowdepth\":0.0,\"windgust\":27.0,\"windspeed\":11.7,\"winddir\":214.7,\"pressure\":1015.8,\"cloudcover\":72.9,\"visibility\":16.5,\"solarradiation\":121.1,\"solarenergy\":10.5,\"uvindex\":5.0,\"severerisk\":10.0,\"sunrise\":\"06:23:59\",\"sunriseEpoch\":1649856239,\"sunset\":\"19:56:31\",\"sunsetEpoch\":1649904991,\"moonphase\":0.44,\"conditions\":\"Partiallycloudy\",\"description\":\"Partlycloudythroughouttheday.\",\"icon\":\"partly-cloudy-day\",\"hours\":[{\"datetime\":\"00:00:00\",\"datetimeEpoch\":1649833200,\"temp\":2.2,\"feelslike\":2.2,\"humidity\":89.69,\"dew\":0.7,\"precip\":0.0,\"precipprob\":0.0,\"snow\":0.0,\"snowdepth\":0.0,\"preciptype\":null,\"windgust\":6.5,\"windspeed\":0.0,\"winddir\":0.0,\"pressure\":1016.0,\"visibility\":15.6,\"cloudcover\":3.8,\"solarradiation\":0.0,\"solarenergy\":null,\"uvindex\":0.0,\"severerisk\":10.0,\"conditions\":\"Clear\",\"icon\":\"clear-night\"},{\"datetime\":\"01:00:00\",\"datetimeEpoch\":1649836800,\"temp\":1.7,\"feelslike\":1.7,\"humidity\":87.83,\"dew\":-0.1,\"precip\":0.0,\"precipprob\":0.0,\"snow\":0.0,\"snowdepth\":0.0,\"preciptype\":null,\"windgust\":4.0,\"windspeed\":1.1,\"winddir\":358.0,\"pressure\":1015.8,\"visibility\":14.4,\"cloudcover\":0.0,\"solarradiation\":0.0,\"solarenergy\":null,\"uvindex\":0.0,\"severerisk\":10.0,\"conditions\":\"Clear\",\"icon\":\"clear-night\"},{\"datetime\":\"02:00:00\",\"datetimeEpoch\":1649840400,\"temp\":1.2,\"feelslike\":1.2,\"humidity\":90.22,\"dew\":-0.2,\"precip\":0.0,\"precipprob\":0.0,\"snow\":0.0,\"snowdepth\":0.0,\"preciptype\":null,\"windgust\":8.6,\"windspeed\":1.7,\"winddir\":345.3,\"pressure\":1015.5,\"visibility\":14.7,\"cloudcover\":7.6,\"solarradiation\":0.0,\"solarenergy\":null,\"uvindex\":0.0,\"severerisk\":10.0,\"conditions\":\"Clear\",\"icon\":\"clear-night\"}]},{\"datetime\":\"2022-04-14\",\"datetimeEpoch\":1649919600,\"tempmax\":9.0,\"tempmin\":1.2,\"temp\":4.2,\"feelslikemax\":7.7,\"feelslikemin\":-1.1,\"feelslike\":2.4,\"dew\":0.6,\"humidity\":80.1,\"precip\":0.1,\"precipprob\":19.0,\"precipcover\":null,\"preciptype\":[\"rain\"],\"snow\":0.0,\"snowdepth\":0.0,\"windgust\":30.2,\"windspeed\":20.5,\"winddir\":208.6,\"pressure\":1013.6,\"cloudcover\":94.7,\"visibility\":21.1,\"solarradiation\":224.8,\"solarenergy\":19.4,\"uvindex\":7.0,\"severerisk\":10.0,\"sunrise\":\"06:22:04\",\"sunriseEpoch\":1649942524,\"sunset\":\"19:57:56\",\"sunsetEpoch\":1649991476,\"moonphase\":0.47,\"conditions\":\"Overcast\",\"description\":\"Cloudyskiesthroughouttheday.\",\"icon\":\"cloudy\",\"hours\":[{\"datetime\":\"00:00:00\",\"datetimeEpoch\":1649919600,\"temp\":2.0,\"feelslike\":0.3,\"humidity\":97.18,\"dew\":1.6,\"precip\":0.0,\"precipprob\":0.0,\"snow\":0.0,\"snowdepth\":0.0,\"preciptype\":null,\"windgust\":5.8,\"windspeed\":5.8,\"winddir\":185.8,\"pressure\":1015.0,\"visibility\":12.4,\"cloudcover\":100.0,\"solarradiation\":0.0,\"solarenergy\":null,\"uvindex\":0.0,\"severerisk\":10.0,\"conditions\":\"Overcast\",\"icon\":\"cloudy\"},{\"datetime\":\"01:00:00\",\"datetimeEpoch\":1649923200,\"temp\":1.8,\"feelslike\":0.2,\"humidity\":97.88,\"dew\":1.5,\"precip\":0.0,\"precipprob\":0.0,\"snow\":0.0,\"snowdepth\":0.0,\"preciptype\":[\"rain\"],\"windgust\":9.0,\"windspeed\":5.4,\"winddir\":195.8,\"pressure\":1014.0,\"visibility\":12.2,\"cloudcover\":100.0,\"solarradiation\":0.0,\"solarenergy\":null,\"uvindex\":0.0,\"severerisk\":10.0,\"conditions\":\"Overcast\",\"icon\":\"cloudy\"},{\"datetime\":\"02:00:00\",\"datetimeEpoch\":1649926800,\"temp\":1.8,\"feelslike\":1.8,\"humidity\":95.79,\"dew\":1.2,\"precip\":0.0,\"precipprob\":0.0,\"snow\":0.0,\"snowdepth\":0.0,\"preciptype\":[\"rain\"],\"windgust\":4.0,\"windspeed\":3.2,\"winddir\":250.4,\"pressure\":1014.0,\"visibility\":13.6,\"cloudcover\":100.0,\"solarradiation\":0.0,\"solarenergy\":null,\"uvindex\":0.0,\"severerisk\":10.0,\"conditions\":\"Overcast\",\"icon\":\"cloudy\"}]},{\"datetime\":\"2022-04-15\",\"datetimeEpoch\":1650006000,\"tempmax\":11.5,\"tempmin\":1.5,\"temp\":5.8,\"feelslikemax\":11.5,\"feelslikemin\":-0.8,\"feelslike\":4.7,\"dew\":0.2,\"humidity\":72.1,\"precip\":0.5,\"precipprob\":42.9,\"precipcover\":null,\"preciptype\":[\"rain\"],\"snow\":0.0,\"snowdepth\":0.0,\"windgust\":19.4,\"windspeed\":12.6,\"winddir\":181.7,\"pressure\":1016.0,\"cloudcover\":89.3,\"visibility\":18.7,\"solarradiation\":270.1,\"solarenergy\":23.3,\"uvindex\":9.0,\"severerisk\":10.0,\"sunrise\":\"06:20:11\",\"sunriseEpoch\":1650028811,\"sunset\":\"19:59:21\",\"sunsetEpoch\":1650077961,\"moonphase\":0.49,\"conditions\":\"Partiallycloudy\",\"description\":\"Partlycloudythroughouttheday.\",\"icon\":\"partly-cloudy-day\",\"hours\":[{\"datetime\":\"00:00:00\",\"datetimeEpoch\":1650006000,\"temp\":2.9,\"feelslike\":1.2,\"humidity\":92.47,\"dew\":1.8,\"precip\":0.0,\"precipprob\":4.8,\"snow\":0.0,\"snowdepth\":0.0,\"preciptype\":null,\"windgust\":9.7,\"windspeed\":6.1,\"winddir\":240.4,\"pressure\":1015.0,\"visibility\":15.2,\"cloudcover\":76.0,\"solarradiation\":0.0,\"solarenergy\":null,\"uvindex\":0.0,\"severerisk\":10.0,\"conditions\":\"Partiallycloudy\",\"icon\":\"partly-cloudy-night\"},{\"datetime\":\"01:00:00\",\"datetimeEpoch\":1650009600,\"temp\":1.9,\"feelslike\":-0.4,\"humidity\":96.48,\"dew\":1.4,\"precip\":0.0,\"precipprob\":4.8,\"snow\":0.0,\"snowdepth\":0.0,\"preciptype\":null,\"windgust\":12.2,\"windspeed\":7.6,\"winddir\":184.8,\"pressure\":1015.0,\"visibility\":13.0,\"cloudcover\":100.0,\"solarradiation\":0.0,\"solarenergy\":null,\"uvindex\":0.0,\"severerisk\":10.0,\"conditions\":\"Overcast\",\"icon\":\"cloudy\"},{\"datetime\":\"02:00:00\",\"datetimeEpoch\":1650013200,\"temp\":2.0,\"feelslike\":-0.4,\"humidity\":97.18,\"dew\":1.6,\"precip\":0.0,\"precipprob\":4.8,\"snow\":0.0,\"snowdepth\":0.0,\"preciptype\":null,\"windgust\":15.5,\"windspeed\":7.9,\"winddir\":167.9,\"pressure\":1016.0,\"visibility\":12.5,\"cloudcover\":100.0,\"solarradiation\":0.0,\"solarenergy\":null,\"uvindex\":0.0,\"severerisk\":10.0,\"conditions\":\"Overcast\",\"icon\":\"cloudy\"}]}],\"currentConditions\":{\"datetime\":\"14:57:18\",\"datetimeEpoch\":1649887038,\"temp\":7.8,\"feelslike\":6.7,\"humidity\":53.4,\"dew\":-1.1,\"precip\":0.0,\"precipprob\":null,\"snow\":0.0,\"snowdepth\":0.0,\"preciptype\":null,\"windgust\":13.0,\"windspeed\":6.6,\"winddir\":85.0,\"pressure\":1016.0,\"visibility\":16.0,\"cloudcover\":100.0,\"solarradiation\":533.0,\"solarenergy\":1.9,\"uvindex\":5.0,\"conditions\":\"Overcast\",\"icon\":\"cloudy\",\"sunrise\":\"06:23:59\",\"sunriseEpoch\":1649856239,\"sunset\":\"19:56:31\",\"sunsetEpoch\":1649904991,\"moonphase\":0.44}}"));
        Observable<Dto_RX> getDtoFromMock = weatherService.getDto("Seattle", "metric", "UDR74JLWCB3CRZBZQSTL3AVQH", "json");
        getDtoFromMock.test().assertValue(dto_rx ->
                dto_rx.address().equals("Seattle")
        );*/
    }

    @Test
    public void testGET_WithParam() throws Exception {
        mockWebServer.setDispatcher(dispatcher);   //dispatcher见下文
        Call<PersonTest> call = service.fetchPersonById(5);
        PersonTest person = call.execute().body();

        assertNotNull(person);
        assertEquals("A", person.getName());
        assertEquals(11, person.getAge());
        assertEquals("GET", mockWebServer.takeRequest().getMethod());
    }

}