package com.example.android.rxweather;

import static com.example.android.rxweather.retrofit.DtoRepository.getDto;
import static com.uber.autodispose.AutoDispose.autoDisposable;
import static com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider.from;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.rxweather.datamodel.Dto_RX;
import com.example.android.rxweather.recyclerview.DaysListAdapter;
import com.example.android.rxweather.recyclerview.HoursListAdapter;
import com.example.android.rxweather.roomdatabean.CityDao;
import com.example.android.rxweather.roomdatabean.CityEntity;
import com.example.android.rxweather.roomdatabean.DateDao;
import com.example.android.rxweather.roomdatabean.DateEntity;
import com.example.android.rxweather.roomdatabean.HourDao;
import com.example.android.rxweather.roomdatabean.HourEntity;
import com.example.android.rxweather.roomdatabean.WeatherDatabase;
import com.example.android.rxweather.util.AppConstants;
import com.example.android.rxweather.util.Convertor;
import com.example.android.rxweather.util.NetworkCheck;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    TextView location_name,current_temperature,current_condition_string,monday_to_sunday;
    ImageView icon;
    RecyclerView recyclerview_today_hourly,recyclerview_days;
    DaysListAdapter daysListAdapter;
    HoursListAdapter hoursListAdapter;
    List<HourEntity> hourEntityList;
    List<DateEntity> dateEntityList;
    private CityDao cityDao;
    private DateDao dateDao;
    private HourDao hourDao;
    WeatherDatabase db;
    String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setRecyclerView();
        prepareData();
        if (NetworkCheck.isNetworkConnected(this)) {
            fetchDataFromCloud();
            Toast.makeText(this, "Network is connected!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Network failure! Use cache data", Toast.LENGTH_SHORT).show();
        }
        observeDataFromDatabase();
    }

    private void observeDataFromDatabase() {
        observeCity();
        observeDays();
        observeHours();
    }

    private void observeHours() {
        Observable
                .interval(0,300, TimeUnit.SECONDS)
                .switchMap(aLong -> hourDao.observe(currentDate))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(autoDisposable(from(this)))
                .subscribe(this::onSubscribeSuccess_hourList,
                        this::onSubscribeError_hourList);

/**        hourDao.observe(currentDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(autoDisposable(from(this)))
                .subscribe(this::onSubscribeSuccess_hourList,
                        this::onSubscribeError_hourList);*/
    }

    private void onSubscribeError_hourList(Throwable throwable) {
        throwable.printStackTrace();
        throw new RuntimeException(throwable);
    }

    private void onSubscribeSuccess_hourList(List<HourEntity> hourEntities) {
        hourEntityList = hourEntities;
        hoursListAdapter.setHoursListAdapterData(hourEntityList);
    }

    private void observeDays() {
        Observable
                .interval(0,300, TimeUnit.SECONDS)
                .switchMap(aLong -> dateDao.getAllDayList())
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .as(autoDisposable(from(this)))
                .subscribe(this::onSubscribeSuccess_dayList,
                        this::onSubscribeError_dayList);

/**        dateDao.getAllDayList()
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .as(autoDisposable(from(this)))
                .subscribe(this::onSubscribeSuccess_dayList,
                        this::onSubscribeError_dayList);*/
    }


    private void onSubscribeError_dayList(Throwable throwable) {
        throwable.printStackTrace();
        throw new RuntimeException(throwable);
    }

    @SuppressLint("SetTextI18n")
    private void onSubscribeSuccess_dayList(List<DateEntity> dateEntities) {
        dateEntityList = dateEntities;
        daysListAdapter.setDaysListAdapterData(dateEntityList);
    }

    private void observeCity() {
        Observable
                .interval(0,300, TimeUnit.SECONDS)
                .switchMap(aLong -> cityDao.getWeatherObj("Seattle"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(autoDisposable(from(this)))
                .subscribe(this::onSubscribeSuccess_weatherObj, this::onSubscribeError_weatherObj);

/**        cityDao.getWeatherObj("Seattle")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(autoDisposable(from(this)))
                .subscribe(this::onSubscribeSuccess_weatherObj, this::onSubscribeError_weatherObj);*/
    }

    private void onSubscribeError_weatherObj(Throwable throwable) {
        throwable.printStackTrace();
        throw new RuntimeException(throwable);
    }

    @SuppressLint("SetTextI18n")
    private void onSubscribeSuccess_weatherObj(CityEntity cityEntity) {
        location_name.setText(cityEntity.address);
        Glide.with(this)
                .load(AppConstants.iconBaseUrl +
                        AppConstants.second_set_color +
                        cityEntity.iconCurrent +
                        ".png")
                .into(icon);
        current_temperature.setText(cityEntity.tempCurrent + "\u2103");
        current_condition_string.setText(cityEntity.iconCurrent);
        monday_to_sunday.setText(Convertor.unixTimeConvertToWeekday(cityEntity.datetimeEpochCurrent));
    }

    private void fetchDataFromCloud() {
        Observable
                .interval(0,300, TimeUnit.SECONDS)
                .switchMap(aLong -> getDto(AppConstants.PARAM))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(autoDisposable(from(this)))
                .subscribe(this::onSuccess, this::onError);

/**        getDto(AppConstants.PARAM)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(autoDisposable(from(this)))
                .subscribe(this::onSuccess, this::onError);*/
    }

    private void onError(Throwable throwable) {
        throwable.printStackTrace();
        throw new RuntimeException(throwable);
    }

    private void onSuccess(Dto_RX dtoRX) {
        String address = dtoRX.address();
        String datetimeCurrent = dtoRX.currentConditions().datetime_current();
        long datetimeEpochCurrent = dtoRX.currentConditions().datetimeEpoch_current();
        double tempCurrent = dtoRX.currentConditions().temp_current();
        String iconCurrent = dtoRX.currentConditions().icon_current();
        CityEntity cityEntity = new CityEntity(address, datetimeCurrent, datetimeEpochCurrent,
                tempCurrent, iconCurrent);
        List<DateEntity> dateEntities = Convertor.convertToDayList(dtoRX,address);
        WeatherDatabase.databaseWriteExecutor.execute(() ->
                insertData(cityEntity, dateEntities, dtoRX));
    }

    private void insertData(CityEntity cityEntity, List<DateEntity> dateEntities, Dto_RX dtoRX) {
        cityDao.deleteAllWeather();
        dateDao.deleteAllDay();
        hourDao.deleteAll();

        cityDao.insertWeather(cityEntity);
        dateDao.insertData(dateEntities);
        for(DateEntity currentDay:dateEntities){
            List<HourEntity> hourList = Convertor.convertToHourList(dtoRX,currentDay.dayId);
            hourDao.insert(hourList);
        }
    }

    private void prepareData() {
        db = WeatherDatabase.getDatabase(this);
        cityDao = db.getCityDao();
        dateDao = db.getDateDao();
        hourDao = db.getHourDao();
    }

    private void setRecyclerView() {
        recyclerview_today_hourly.setHasFixedSize(true);
        recyclerview_today_hourly.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        recyclerview_today_hourly.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        hoursListAdapter = new HoursListAdapter(hourEntityList);
        recyclerview_today_hourly.setAdapter(hoursListAdapter);

        recyclerview_days.setLayoutManager(new LinearLayoutManager(this));
        daysListAdapter = new DaysListAdapter(dateEntityList);
        recyclerview_days.setAdapter(daysListAdapter);
    }

    private void initView(){
        location_name = findViewById(R.id.location_name);
        current_temperature = findViewById(R.id.current_temperature);
        current_condition_string = findViewById(R.id.current_condition_string);
        monday_to_sunday = findViewById(R.id.monday_to_sunday);
        icon = findViewById(R.id.icon);

        recyclerview_today_hourly = findViewById(R.id.recyclerview_today_hourly);
        recyclerview_days = findViewById(R.id.recyclerview_days);
    }
}