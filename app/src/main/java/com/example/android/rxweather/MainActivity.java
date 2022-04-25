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
import com.example.android.rxweather.roomdatabean.DayModelWithHourModels;
import com.example.android.rxweather.roomdatabean.DayModelWithHourModelsDao;
import com.example.android.rxweather.roomdatabean.HourModel;
import com.example.android.rxweather.roomdatabean.WeatherDatabase;
import com.example.android.rxweather.roomdatabean.WeatherObj;
import com.example.android.rxweather.roomdatabean.WeatherObjWithDays;
import com.example.android.rxweather.roomdatabean.WeatherObjWithDaysDao;
import com.example.android.rxweather.util.AppConstants;
import com.example.android.rxweather.util.Convertor;
import java.util.List;
import java.util.stream.Collectors;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    TextView location_name,current_temperature,current_condition_string,monday_to_sunday,
            temperature_max,temperature_min;
    List<DayModel> daysList;
    ImageView icon;
    RecyclerView recyclerview_today_hourly,recyclerview_days;
    private WeatherObjWithDaysDao weatherObjWithDaysDao;
    private DayModelWithHourModelsDao dayModelWithHourModelsDao;
    WeatherObjWithDays weatherObjWithDays;
    int weather_obj_id = 0;
    DaysListAdapter daysListAdapter;
    HoursListAdapter hoursListAdapter;
    List<HourModel> hourModelList;
    List<DayModel> dayModelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        weatherObjWithDaysDao = WeatherDatabase.getDatabase(this).getWeatherObjDao();
        dayModelWithHourModelsDao = WeatherDatabase.getDatabase(this).getDayModelDao();

        loadRxJavaData();

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
                (weather_obj_id, dtoRX.address(), CurrentConvertor.convert(dtoRX.currentConditions()));
        daysList = DayModelListConvertor.convertor(dtoRX,weather_obj_id);
        weatherObjWithDays = new WeatherObjWithDays(weatherObj,daysList);

        insertWeatherObjToRoom(weatherObjWithDays, convertor(dtoRX));
    }

    private List<DayModelWithHourModels> convertor(Dto_RX dtoRX) {
        return dtoRX
                .weather_list_by_days()
                .stream()
                .map(day ->
                        new DayModelWithHourModels(DayToDayModel.rxToDayModel(day,weather_obj_id),
                                DayToDayModel.rxToDayModel(day,weather_obj_id).hours)
                )
                .collect(Collectors.toList());
    }

    private void insertWeatherObjToRoom(WeatherObjWithDays weatherObjWithDays,
                                        List<DayModelWithHourModels> dayModelWithHourModelsList){
        WeatherDatabase.databaseWriteExecutor.execute(() ->
                weatherObjWithDaysDao.insertWeatherObjWithDays(weatherObjWithDays));
        WeatherDatabase.databaseWriteExecutor.execute(() ->
                dayModelWithHourModelsDao.insertData(dayModelWithHourModelsList));
    }

    private void subscribeRoomData(Observable <WeatherObjWithDays> weatherObjWithDays,
                                   Observable <List<DayModelWithHourModels>> dayListWithHours) {
        weatherObjWithDays
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(autoDisposable(from(this)))
                .subscribe(this::onSubscribeSuccess_weatherObjWithDays,
                        this::onSubscribeError_weatherObjWithDays);

        dayListWithHours
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(autoDisposable(from(this)))
                .subscribe(this::onSubscribeSuccess_dayListWithHours,
                        this::onSubscribeError_dayListWithHours);
    }

    private void onSubscribeSuccess_dayListWithHours(List<DayModelWithHourModels> dayModelWithHourModels) {
        hourModelList = dayModelWithHourModels.get(0).hourModels;
        hoursListAdapter.setHoursListAdapterData(hourModelList);
    }

    private void onSubscribeError_dayListWithHours(Throwable throwable) {
        throwable.printStackTrace();
        throw new RuntimeException(throwable);
    }

    @SuppressLint("SetTextI18n")
    private void onSubscribeSuccess_weatherObjWithDays(WeatherObjWithDays weatherObjWithDays) {
        location_name.setText(weatherObjWithDays.weatherObj.address);
        Glide.with(this)
                .load(AppConstants.iconBaseUrl +
                        AppConstants.second_set_color +
                        weatherObjWithDays.weatherObj.currentCondition.icon_current +
                        ".png")
                .into(icon);
        current_temperature
                .setText(weatherObjWithDays.weatherObj.currentCondition.temp_current + "\u2103");
        current_condition_string
                .setText(weatherObjWithDays.weatherObj.currentCondition.icon_current);
        monday_to_sunday
                .setText(Convertor.unixTimeConvertToWeekday(
                        weatherObjWithDays.days.get(0).datetimeEpoch_day));
        temperature_max.setText(weatherObjWithDays.days.get(0).temp_max_day + "\u2103 \u21E1");
        temperature_min.setText(weatherObjWithDays.days.get(0).temp_min_day + "\u2103 \u21E1");
        dayModelList = weatherObjWithDays.days;
        daysListAdapter.setDaysListAdapterData(dayModelList);
    }

    private void onSubscribeError_weatherObjWithDays(Throwable throwable) {
        throwable.printStackTrace();
        throw new RuntimeException(throwable);
    }
}