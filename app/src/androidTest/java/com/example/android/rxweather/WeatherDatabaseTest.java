package com.example.android.rxweather;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.example.android.rxweather.roomdatabean.CityDao;
import com.example.android.rxweather.roomdatabean.CityEntity;
import com.example.android.rxweather.roomdatabean.DateDao;
import com.example.android.rxweather.roomdatabean.HourDao;
import com.example.android.rxweather.roomdatabean.WeatherDatabase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

@RunWith(AndroidJUnit4.class)
public class WeatherDatabaseTest {
    private CityDao cityDao;
    private DateDao dateDao;
    private HourDao hourDao;
    private WeatherDatabase db;

    @Before
    public void setUp() throws Exception {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, WeatherDatabase.class).build();
        cityDao = db.getCityDao();
        dateDao = db.getDateDao();
        hourDao = db.getHourDao();
    }

    @After
    public void tearDown() throws Exception {
        db.close();
    }


    @SuppressLint("CheckResult")
    @Test
    public void getCityDao() {
        CityEntity e1 = new CityEntity("Los Angeles","2022-06-27",1656572400,17.4,"Los Angeles-clear-day");
        CityEntity e2 = new CityEntity("Seattle","2022-06-30",1656572401,15.1,"Seattle-clear-night");
        CityEntity e3 = new CityEntity("San Francisco","2022-06-30",1656572402,10.5,"San Francisco-clear-night");
        CityEntity e4 = new CityEntity("Portland","2022-06-30",1656572403,20.7,"Portland-partly-cloudy-day");
        CityEntity e5 = new CityEntity("San Diego","2022-06-30",1656572404,22.8,"San Diego-clear-day");
        List<CityEntity> targetCityEntityList = Stream.of(e1,e2,e3,e4,e5).collect(Collectors.toList());
        cityDao.insertWeather(e1);
        cityDao.insertWeather(e2);
        cityDao.insertWeather(e3);
        cityDao.insertWeather(e4);
        cityDao.insertWeather(e5);
        for (int i = 0; i < 5; i++) {
            Assert.assertEquals(targetCityEntityList.get(i).address,cityDao.getAllWeatherList().get(i).address);
            Assert.assertEquals(targetCityEntityList.get(i).datetimeCurrent,cityDao.getAllWeatherList().get(i).datetimeCurrent);
            Assert.assertEquals(targetCityEntityList.get(i).datetimeEpochCurrent,cityDao.getAllWeatherList().get(i).datetimeEpochCurrent);
            Assert.assertEquals(targetCityEntityList.get(i).tempCurrent,cityDao.getAllWeatherList().get(i).tempCurrent,0);
            Assert.assertEquals(targetCityEntityList.get(i).iconCurrent,cityDao.getAllWeatherList().get(i).iconCurrent);
        }

/**        Assert.assertEquals(targetCityEntityList.get(0).address,cityDao.getAllWeatherList().get(0).address);
 Assert.assertEquals(targetCityEntityList.get(0).datetimeCurrent,cityDao.getAllWeatherList().get(0).datetimeCurrent);
 Assert.assertEquals(targetCityEntityList.get(0).datetimeEpochCurrent,cityDao.getAllWeatherList().get(0).datetimeEpochCurrent);
 Assert.assertEquals(targetCityEntityList.get(0).tempCurrent,cityDao.getAllWeatherList().get(0).tempCurrent,0);
 Assert.assertEquals(targetCityEntityList.get(0).iconCurrent,cityDao.getAllWeatherList().get(0).iconCurrent);*/

        List<String> cityList = List.of("Los Angeles","Seattle","San Francisco","Portland","San Diego");
        for(int j = 0; j < 5; j++){
            TestObserver<CityEntity> testObserver = cityDao.getWeatherObj(cityList.get(j)).test();
            testObserver.assertNoErrors();
            testObserver.onSuccess(targetCityEntityList.get(j));
            Assert.assertEquals(targetCityEntityList.get(j).address, testObserver.values().get(0).address);
            Assert.assertEquals(targetCityEntityList.get(j).datetimeCurrent, testObserver.values().get(0).datetimeCurrent);
            Assert.assertEquals(targetCityEntityList.get(j).datetimeEpochCurrent, testObserver.values().get(0).datetimeEpochCurrent);
            Assert.assertEquals(targetCityEntityList.get(j).tempCurrent, testObserver.values().get(0).tempCurrent,0);
            Assert.assertEquals(targetCityEntityList.get(j).iconCurrent, testObserver.values().get(0).iconCurrent);
        }

//        List<List<Object>> events = los_angeles.getEvents();
//        List<Object> objects = events.get(0);
//        cityDao.deleteAllWeather();

        targetCityEntityList.clear();
    }

    @Test
    public void getDateDao() {

    }

    @Test
    public void getHourDao() {

    }

    @Test
    public void getDatabase() {

    }
}