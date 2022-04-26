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
import com.bumptech.glide.Glide;
import com.example.android.rxweather.datamodel.Dto_RX;
import com.example.android.rxweather.recyclerview.DaysListAdapter;
import com.example.android.rxweather.recyclerview.HoursListAdapter;
import com.example.android.rxweather.roomdatabean.DayModel;
import com.example.android.rxweather.roomdatabean.DayModelWithHourModelsDao;
import com.example.android.rxweather.roomdatabean.HourModel;
import com.example.android.rxweather.roomdatabean.WeatherDatabase;
import com.example.android.rxweather.roomdatabean.WeatherObj;
import com.example.android.rxweather.roomdatabean.WeatherObjWithDaysDao;
import com.example.android.rxweather.util.AppConstants;
import com.example.android.rxweather.util.Convertor;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    TextView location_name,current_temperature,current_condition_string,monday_to_sunday,
            temperature_max,temperature_min;
    ImageView icon;
    RecyclerView recyclerview_today_hourly,recyclerview_days;
    private WeatherObjWithDaysDao weatherObjWithDaysDao;
    private DayModelWithHourModelsDao dayModelWithHourModelsDao;
    int weather_obj_id = 0;
    DaysListAdapter daysListAdapter;
    HoursListAdapter hoursListAdapter;
    List<HourModel> hourModelList;
    List<DayModel> dayModelList;
    WeatherDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        db = WeatherDatabase.getDatabase(this);
        weatherObjWithDaysDao = db.getWeatherObjDao();
        dayModelWithHourModelsDao = db.getDayModelDao();
        loadRxJavaData();
        subscribeRoomData(weatherObjWithDaysDao.getWeatherObjWithDays(),
                dayModelWithHourModelsDao.getAllDayList());

        setRecyclerView();
    }

    private void setRecyclerView() {
        recyclerview_today_hourly.setHasFixedSize(true);
        recyclerview_today_hourly.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        recyclerview_today_hourly.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        hoursListAdapter = new HoursListAdapter(hourModelList);
        recyclerview_today_hourly.setAdapter(hoursListAdapter);

        recyclerview_days.setLayoutManager(new LinearLayoutManager(this));
        daysListAdapter = new DaysListAdapter(dayModelList);
        recyclerview_days.setAdapter(daysListAdapter);
    }

    private void initView(){
        location_name = findViewById(R.id.location_name);
        current_temperature = findViewById(R.id.current_temperature);
        current_condition_string = findViewById(R.id.current_condition_string);
        monday_to_sunday = findViewById(R.id.monday_to_sunday);
        temperature_max = findViewById(R.id.temperature_max);
        temperature_min = findViewById(R.id.temperature_min);
        icon = findViewById(R.id.icon);

        recyclerview_today_hourly = findViewById(R.id.recyclerview_today_hourly);
        recyclerview_days = findViewById(R.id.recyclerview_days);
    }

    private void loadRxJavaData() {
        getDto(AppConstants.PARAM)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(autoDisposable(from(this)))
                .subscribe(this::onSuccess, this::onError);
    }

    private void onError(Throwable throwable) {
        throwable.printStackTrace();
        throw new RuntimeException(throwable);
    }

    private void onSuccess(Dto_RX dtoRX) {
        WeatherObj weatherObj = new WeatherObj
                (weather_obj_id, dtoRX.address(), Convertor.convertToCurrentCondition(dtoRX.currentConditions()));
        insertWeatherObjToRoom(weatherObj, Convertor.convertorToDayModelList(dtoRX,weather_obj_id));
    }

    private void insertWeatherObjToRoom(WeatherObj weatherObj,
                                        List<DayModel> dayModel){
        WeatherDatabase.databaseWriteExecutor.execute(() ->
                weatherObjWithDaysDao.insertWeatherObj(weatherObj));
        WeatherDatabase.databaseWriteExecutor.execute(() ->
                dayModelWithHourModelsDao.insertData(dayModel));
    }

    private void subscribeRoomData(Observable <WeatherObj> weatherObj,
                                   Observable <List<DayModel>> dayList) {
        weatherObj
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(autoDisposable(from(this)))
                .subscribe(this::onSubscribeSuccess_weatherObj,
                        this::onSubscribeError_weatherObj);

        dayList
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(autoDisposable(from(this)))
                .subscribe(this::onSubscribeSuccess_dayList,
                        this::onSubscribeError_dayList);
    }

    private void onSubscribeError_dayList(Throwable throwable) {
        throwable.printStackTrace();
        throw new RuntimeException(throwable);
    }

    private void onSubscribeSuccess_dayList(List<DayModel> dayModels) {
        hourModelList = dayModels.get(0).hours;
        hoursListAdapter.setHoursListAdapterData(hourModelList);
    }

    private void onSubscribeError_weatherObj(Throwable throwable) {
        throwable.printStackTrace();
        throw new RuntimeException(throwable);
    }

    @SuppressLint("SetTextI18n")
    private void onSubscribeSuccess_weatherObj(WeatherObj weatherObj) {
        location_name.setText(weatherObj.address);
        Glide.with(this)
                .load(AppConstants.iconBaseUrl +
                        AppConstants.second_set_color +
                        weatherObj.currentCondition.icon_current +
                        ".png")
                .into(icon);
        current_temperature
                .setText(weatherObj.currentCondition.temp_current + "\u2103");
        current_condition_string
                .setText(weatherObj.currentCondition.icon_current);
        monday_to_sunday
                .setText(Convertor.unixTimeConvertToWeekday(
                        weatherObj.days.get(0).datetimeEpoch_day));
        temperature_max.setText(weatherObj.days.get(0).temp_max_day + "\u2103 \u21E1");
        temperature_min.setText(weatherObj.days.get(0).temp_min_day + "\u2103 \u21E1");
        dayModelList = weatherObj.days;
        daysListAdapter.setDaysListAdapterData(dayModelList);
    }
}