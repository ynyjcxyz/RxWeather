package com.example.android.rxweather;

import static com.example.android.rxweather.retrofit.DtoRepository.getDto;
import static com.uber.autodispose.AutoDispose.autoDisposable;
import static com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider.from;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.android.rxweather.datamodel.Dto_RX;
import com.example.android.rxweather.recyclerview.DaysListAdapter;
import com.example.android.rxweather.roomdatabean.CityDao;
import com.example.android.rxweather.roomdatabean.CityEntity;
import com.example.android.rxweather.roomdatabean.DateDao;
import com.example.android.rxweather.roomdatabean.DateEntity;
import com.example.android.rxweather.roomdatabean.DateModel;
import com.example.android.rxweather.roomdatabean.HourDao;
import com.example.android.rxweather.roomdatabean.HourEntity;
import com.example.android.rxweather.roomdatabean.WeatherDatabase;
import com.example.android.rxweather.util.AppConstants;
import com.example.android.rxweather.util.Convertor;
import com.example.android.rxweather.util.NetworkCheck;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
  TextView location_name,current_temperature,current_condition_string;
    ImageView icon;
    RecyclerView recyclerview_days;
    DaysListAdapter daysListAdapter;
    private CityDao cityDao;
    private DateDao dateDao;
    private HourDao hourDao;
    WeatherDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setRecyclerView();
        prepareData();
        if (NetworkCheck.isNetworkConnected(this)) {
            fetchDataFromCloudAndInsert();
            Toast.makeText(this, "Network is connected!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Network failure! Use cache data", Toast.LENGTH_SHORT).show();
        }
        observeDataFromDatabase();
    }

    private void observeDataFromDatabase() {
        observeCity();
        observeDaysAndHours();
     }


    private void observeDaysAndHours() {

        modelStreaming()
                .as(autoDisposable(from(this)))
                .subscribe(this::onSubscribeSuccess_dayList, this::onSubscribeError_dayList);

    }

  private Observable<List<DateModel>> modelStreaming() {
    return new ModelStreaming(dateDao,hourDao).modelStreaming();
  }

  private void onSubscribeError_dayList(Throwable throwable) {
        throwable.printStackTrace();
        throw new RuntimeException(throwable);
    }

    private void onSubscribeSuccess_dayList(List<DateModel> list) {
        daysListAdapter.setModelList(list);
    }

    private void observeCity() {
        Observable
                .interval(0,1800, TimeUnit.SECONDS)
                .switchMap(aLong -> cityDao.getWeatherObj("Seattle"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(autoDisposable(from(this)))
                .subscribe(this::onSubscribeSuccess_weatherObj, this::onSubscribeError_weatherObj);
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
    }

    private void fetchDataFromCloudAndInsert() {
        Observable
                .interval(0,1800, TimeUnit.SECONDS)
                .switchMap(aLong -> getDto(AppConstants.PARAM))
                .subscribeOn(Schedulers.io())
                .as(autoDisposable(from(this)))
                .subscribe(this::onSuccess, this::onError);


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
        recyclerview_days.setLayoutManager(new LinearLayoutManager(this));
        daysListAdapter = new DaysListAdapter();
        recyclerview_days.setAdapter(daysListAdapter);
    }

    private void initView(){
        location_name = findViewById(R.id.location_name);
        current_temperature = findViewById(R.id.current_temperature);
        current_condition_string = findViewById(R.id.current_condition_string);
        icon = findViewById(R.id.icon);
        recyclerview_days = findViewById(R.id.recyclerview_days);
    }

}