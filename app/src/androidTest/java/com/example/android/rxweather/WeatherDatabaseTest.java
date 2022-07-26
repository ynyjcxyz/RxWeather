package com.example.android.rxweather;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.example.android.rxweather.roomdatabean.CityDao;
import com.example.android.rxweather.roomdatabean.CityEntity;
import com.example.android.rxweather.roomdatabean.DateDao;
import com.example.android.rxweather.roomdatabean.DateEntity;
import com.example.android.rxweather.roomdatabean.HourDao;
import com.example.android.rxweather.roomdatabean.HourEntity;
import com.example.android.rxweather.roomdatabean.WeatherDatabase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.List;

import io.reactivex.observers.TestObserver;

@RunWith(AndroidJUnit4.class)
public class WeatherDatabaseTest {
    private CityDao cityDao;
    private DateDao dateDao;
    private HourDao hourDao;
    private WeatherDatabase db;
    List<CityEntity> targetCityEntityList;
    List<DateEntity> targetDateEntityList;
    List<List<HourEntity>> targetHourEntityList;


    @Before
    public void setUp() throws Exception {
        db = Room
                .inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), WeatherDatabase.class)
                .allowMainThreadQueries()
                .build();
        cityDao = db.getCityDao();
        dateDao = db.getDateDao();
        hourDao = db.getHourDao();

        CityEntity e1 = new CityEntity("Los Angeles","22:47:20",1656827240,17.4,"Los Angeles-clear-day");
        CityEntity e2 = new CityEntity("Seattle","22:47:20",1656827240,15.1,"Seattle-clear-night");
        CityEntity e3 = new CityEntity("San Francisco","22:47:20",1656827240,10.5,"San Francisco-clear-night");
        CityEntity e4 = new CityEntity("Portland","22:47:20",1656827240,20.7,"Portland-partly-cloudy-day");
        CityEntity e5 = new CityEntity("San Diego","22:47:20",1656827240,22.8,"San Diego-clear-day");
        targetCityEntityList = List.of(e1,e2,e3,e4,e5);
        for(int n = 0; n < targetCityEntityList.size(); n++){
            cityDao.insertWeather(targetCityEntityList.get(n));
        }

        DateEntity d1 = new DateEntity("2022-06-27",1656745200,17.3,10.4,"Los Angeles-clear-day",cityDao.getAllWeatherList().get(0).address);
        DateEntity d2 = new DateEntity("2022-06-28",1656745200,16.4,11.4,"Los Angeles-night",cityDao.getAllWeatherList().get(0).address);
        DateEntity d3 = new DateEntity("2022-06-29",1656745200,14.3,12.4,"Los Angeles-clear-night",cityDao.getAllWeatherList().get(0).address);
        DateEntity d4 = new DateEntity("2022-06-30",1656745200,16.3,10.4,"Los Angeles-cloudy-day",cityDao.getAllWeatherList().get(0).address);
        DateEntity d5 = new DateEntity("2022-07-01",1656745200,19.3,9.4,"Los Angeles-clear-day",cityDao.getAllWeatherList().get(0).address);
        targetDateEntityList = List.of(d1,d2,d3,d4,d5);
        dateDao.insertData(targetDateEntityList);

        HourEntity h1 = new HourEntity(1658127600,10.4,"clear-night","2022-06-27");
        HourEntity h2 = new HourEntity(1658131200,11.4,"clear-night","2022-06-27");
        HourEntity h3 = new HourEntity(1658134800,12.4,"clear-night","2022-06-27");
        HourEntity h4 = new HourEntity(1658138400,13.4,"clear-night","2022-06-27");
        HourEntity h5 = new HourEntity(1658142000,14.4,"clear-night","2022-06-27");
        List<HourEntity> targetHourEntityList_day_1 = List.of(h1,h2,h3,h4,h5);
        HourEntity m1 = new HourEntity(1658143533,10.5,"clear_night","2022-06-28");
        HourEntity m2 = new HourEntity(1658131212,11.6,"clear_night","2022-06-28");
        HourEntity m3 = new HourEntity(1658134123,12.7,"clear_night","2022-06-28");
        HourEntity m4 = new HourEntity(1658123321,13.8,"clear_night","2022-06-28");
        HourEntity m5 = new HourEntity(1658141232,14.3,"clear_night","2022-06-28");
        List<HourEntity> targetHourEntityList_day_2 = List.of(m1,m2,m3,m4,m5);
        HourEntity a1 = new HourEntity(1658345355,12.5,"clear_night","2022-06-29");
        HourEntity a2 = new HourEntity(1658133453,15.6,"clear_night","2022-06-29");
        HourEntity a3 = new HourEntity(1658134535,18.7,"clear_night","2022-06-29");
        HourEntity a4 = new HourEntity(1658435322,13.8,"clear_night","2022-06-29");
        HourEntity a5 = new HourEntity(1658123442,12.3,"clear_night","2022-06-29");
        List<HourEntity> targetHourEntityList_day_3 = List.of(a1,a2,a3,a4,a5);
        HourEntity b1 = new HourEntity(1658435523,10.5,"clear_night","2022-06-30");
        HourEntity b2 = new HourEntity(1658131242,11.6,"clear_night","2022-06-30");
        HourEntity b3 = new HourEntity(1658134183,12.7,"clear_night","2022-06-30");
        HourEntity b4 = new HourEntity(1658123311,13.8,"clear_night","2022-06-30");
        HourEntity b5 = new HourEntity(1658141202,14.3,"clear_night","2022-06-30");
        List<HourEntity> targetHourEntityList_day_4 = List.of(b1,b2,b3,b4,b5);
        HourEntity c1 = new HourEntity(1653535355,6.5,"clear_night","2022-07-01");
        HourEntity c2 = new HourEntity(1658133434,15.6,"clear_night","2022-07-01");
        HourEntity c3 = new HourEntity(1654534343,17.7,"clear_night","2022-07-01");
        HourEntity c4 = new HourEntity(1645345347,12.8,"clear_night","2022-07-01");
        HourEntity c5 = new HourEntity(1642873833,18.3,"clear_night","2022-07-01");
        List<HourEntity> targetHourEntityList_day_5 = List.of(c1,c2,c3,c4,c5);

        targetHourEntityList = List.of(targetHourEntityList_day_1,targetHourEntityList_day_2,targetHourEntityList_day_3,targetHourEntityList_day_4,targetHourEntityList_day_5);
        for(int p = 0; p < 5; p++){
            hourDao.insert(targetHourEntityList.get(p));
        }
    }

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @After
    public void tearDown() throws Exception {
        db.close();
    }

    @Test
    public void getCityDao() {
        Assert.assertNotEquals(0, cityDao.getAllWeatherList().size());
        for (int i = 0; i < targetCityEntityList.size(); i++) {
            Assert.assertEquals(targetCityEntityList.get(i), cityDao.getAllWeatherList().get(i));
        }
        cityDao.getWeatherObj(targetCityEntityList.get(0).address)
                .test()
                .assertValue(cityEntity ->
                        cityEntity.equals(targetCityEntityList.get(0)));
        cityDao.getWeatherObj(targetCityEntityList.get(1).address)
                .test()
                .assertValue(cityEntity ->
                        cityEntity.equals(targetCityEntityList.get(1)));
        cityDao.getWeatherObj(targetCityEntityList.get(2).address)
                .test()
                .assertValue(cityEntity ->
                        cityEntity.equals(targetCityEntityList.get(2)));
        cityDao.getWeatherObj(targetCityEntityList.get(3).address)
                .test()
                .assertValue(cityEntity ->
                        cityEntity.equals(targetCityEntityList.get(3)));
        cityDao.getWeatherObj(targetCityEntityList.get(4).address)
                .test()
                .assertValue(cityEntity ->
                        cityEntity.equals(targetCityEntityList.get(4)));
        cityDao.deleteAllWeather();
        Assert.assertEquals(0,cityDao.getAllWeatherList().size());


/**            Assert.assertEquals(targetCityEntityList.get(i).address, cityDao.getAllWeatherList().get(i).address);
            Assert.assertEquals(targetCityEntityList.get(i).datetimeCurrent, cityDao.getAllWeatherList().get(i).datetimeCurrent);
            Assert.assertEquals(targetCityEntityList.get(i).datetimeEpochCurrent, cityDao.getAllWeatherList().get(i).datetimeEpochCurrent);
            Assert.assertEquals(targetCityEntityList.get(i).tempCurrent, cityDao.getAllWeatherList().get(i).tempCurrent,0);
            Assert.assertEquals(targetCityEntityList.get(i).iconCurrent, cityDao.getAllWeatherList().get(i).iconCurrent);*/

/**        Assert.assertEquals(targetCityEntityList.get(0).address,cityDao.getAllWeatherList().get(0).address);
 Assert.assertEquals(targetCityEntityList.get(0).datetimeCurrent,cityDao.getAllWeatherList().get(0).datetimeCurrent);
 Assert.assertEquals(targetCityEntityList.get(0).datetimeEpochCurrent,cityDao.getAllWeatherList().get(0).datetimeEpochCurrent);
 Assert.assertEquals(targetCityEntityList.get(0).tempCurrent,cityDao.getAllWeatherList().get(0).tempCurrent,0);
 Assert.assertEquals(targetCityEntityList.get(0).iconCurrent,cityDao.getAllWeatherList().get(0).iconCurrent);*/

//        List<String> cityList = List.of("Los Angeles","Seattle","San Francisco","Portland","San Diego");
//        for(int j = 0; j < cityList.size(); j++){

//        TestObserver<CityEntity> los_angeles = cityDao.getWeatherObj("Los Angeles").test();
//        los_angeles.assertNoErrors().onSuccess(targetCityEntityList.get(0));
//        Assert.assertEquals(targetCityEntityList.get(0).address, los_angeles.values().get(0).address);

/*            testObserver.assertValueCount(1);
            List<CityEntity> resultValues = testObserver.values();
            CityEntity testValue = resultValues.get(0);
            assertThat(testValue).isEqualTo(targetCityEntityList.get(0));*/

//            testObserver.onSuccess(targetCityEntityList.get(j));
//            Assert.assertEquals(targetCityEntityList.get(j).address, testObserver.values().get(0).address);
//            Assert.assertEquals(targetCityEntityList.get(j).datetimeCurrent, testObserver.values().get(0).datetimeCurrent);
//            Assert.assertEquals(targetCityEntityList.get(j).datetimeEpochCurrent, testObserver.values().get(0).datetimeEpochCurrent);
//            Assert.assertEquals(targetCityEntityList.get(j).tempCurrent, testObserver.values().get(0).tempCurrent,0);
//            Assert.assertEquals(targetCityEntityList.get(j).iconCurrent, testObserver.values().get(0).iconCurrent);
//        }

//        List<List<Object>> events = los_angeles.getEvents();
//        List<Object> objects = events.get(0);

//        cityDao.deleteAllWeather();
//        Assert.assertEquals(0,cityDao.getAllWeatherList().size());
//
//        targetCityEntityList.clear();



/**        TestObserver<List<DateEntity>> dateTestObserver = dateDao.getAllDayList().test();
        dateTestObserver.assertNoErrors();
        dateTestObserver.onSuccess(targetDateEntityList);
        for(int k = 0; k < targetDateEntityList.size(); k++){
            Assert.assertEquals(targetDateEntityList.get(k).dayId, dateTestObserver.values().get(0).get(k).dayId);
            Assert.assertEquals(targetDateEntityList.get(k).datetimeEpochDay, dateTestObserver.values().get(0).get(k).datetimeEpochDay);
            Assert.assertEquals(targetDateEntityList.get(k).tempMaxDay, dateTestObserver.values().get(0).get(k).tempMaxDay,0);
            Assert.assertEquals(targetDateEntityList.get(k).tempMinDay, dateTestObserver.values().get(0).get(k).tempMinDay,0);
            Assert.assertEquals(targetDateEntityList.get(k).iconDay, dateTestObserver.values().get(0).get(k).iconDay);
            Assert.assertEquals(targetDateEntityList.get(k).cityName, dateTestObserver.values().get(0).get(k).cityName);
        }*/


/*        String testDate = "2022-06-27";
        TestObserver<List<HourEntity>> hourTestObserver = hourDao.observe(testDate).test();
        hourTestObserver.assertNoErrors();
        hourTestObserver.onSuccess(targetHourEntityList.get(0));

        Assert.assertEquals(targetHourEntityList.get(0).get(0).datetimeEpochHours, hourTestObserver.values().get(0).get(0).datetimeEpochHours);
        Assert.assertEquals(targetHourEntityList.get(0).get(0).tempHours, hourTestObserver.values().get(0).get(0).tempHours,0);
        Assert.assertEquals(targetHourEntityList.get(0).get(0).iconHours, hourTestObserver.values().get(0).get(0).iconHours);
        Assert.assertEquals(targetHourEntityList.get(0).get(0).associatedDateId, hourTestObserver.values().get(0).get(0).associatedDateId);*/
    }

    @Test
    public void getDateDao() {
        Assert.assertNotNull(dateDao.getAllDayList());
        dateDao.getAllDayList()
                .test()
                .assertValue(dateEntityList ->
                        dateEntityList.get(0).equals(targetDateEntityList.get(0)) &&
                                dateEntityList.get(1).equals(targetDateEntityList.get(1)) &&
                                dateEntityList.get(2).equals(targetDateEntityList.get(2)) &&
                                dateEntityList.get(3).equals(targetDateEntityList.get(3)) &&
                                dateEntityList.get(4).equals(targetDateEntityList.get(4)) );

        dateDao.deleteAllDay();
        dateDao.getAllDayList()
                .test()
                .assertValue(dateEntityList -> dateEntityList.size() == 0);
/**        DateEntity d1 = new DateEntity("2022-06-27",1656745200,17.3,10.4,"Los Angeles-clear-day",cityDao.getAllWeatherList().get(0).address);
        DateEntity d2 = new DateEntity("2022-06-28",1656745200,16.4,11.4,"Los Angeles-night",cityDao.getAllWeatherList().get(0).address);
        DateEntity d3 = new DateEntity("2022-06-29",1656745200,14.3,12.4,"Los Angeles-clear-night",cityDao.getAllWeatherList().get(0).address);
        DateEntity d4 = new DateEntity("2022-06-30",1656745200,16.3,10.4,"Los Angeles-cloudy-day",cityDao.getAllWeatherList().get(0).address);
        DateEntity d5 = new DateEntity("2022-07-01",1656745200,19.3,9.4,"Los Angeles-clear-day",cityDao.getAllWeatherList().get(0).address);
        List<DateEntity> targetDateEntityList = Stream.of(d1,d2,d3,d4,d5).collect(Collectors.toList());
        dateDao.insertData(targetDateEntityList);

        TestObserver<List<DateEntity>> dateObserver = dateDao.getAllDayList().test();
        dateObserver.assertNoErrors();*/
//        dateObserver.onSuccess(targetDateEntityList);
//        Assert.assertEquals(targetDateEntityList.get(0).dayId, targetDateEntityList.get(0).dayId);
    }

    @Test
    public void getHourDao() {
        Assert.assertTrue(hourDao.getAllHours().size() != 0);
        for(int i = 0; i < 5; i++){
            for(int j = 4; j >= 0; j--){
                Assert.assertTrue(hourDao.getAllHours().contains(targetHourEntityList.get(i).get(j)));
            }
        }

        hourDao
                .observe("2022-06-27")
                .test()
                .assertValue(hourEntityList ->
                        hourEntityList.containsAll(targetHourEntityList.get(0)));
        hourDao
                .observe("2022-06-28")
                .test()
                .assertValue(hourEntityList ->
                        hourEntityList.containsAll(targetHourEntityList.get(1)));
        hourDao
                .observe("2022-06-29")
                .test()
                .assertValue(hourEntityList ->
                        hourEntityList.containsAll(targetHourEntityList.get(2)));
        hourDao
                .observe("2022-06-30")
                .test()
                .assertValue(hourEntityList ->
                        hourEntityList.containsAll(targetHourEntityList.get(3)));
        hourDao
                .observe("2022-07-01")
                .test()
                .assertValue(hourEntityList ->
                        hourEntityList.containsAll(targetHourEntityList.get(4)));

        hourDao.deleteAll();
        Assert.assertEquals(0,hourDao.getAllHours().size());
    }
}