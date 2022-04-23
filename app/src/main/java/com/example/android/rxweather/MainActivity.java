package com.example.android.rxweather;

import static com.example.android.rxweather.retrofit.DtoRepository.getDto;
import static com.uber.autodispose.AutoDispose.autoDisposable;
import static com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider.from;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.android.rxweather.datamodel.Dto;
import com.example.android.rxweather.roomdatabean.DayModel;
import com.example.android.rxweather.roomdatabean.DayModelWithHourModels;
import com.example.android.rxweather.roomdatabean.DayModelWithHourModelsDao;
import com.example.android.rxweather.roomdatabean.WeatherDatabase;
import com.example.android.rxweather.roomdatabean.WeatherObj;
import com.example.android.rxweather.roomdatabean.WeatherObjWithDays;
import com.example.android.rxweather.roomdatabean.WeatherObjWithDaysDao;
import com.example.android.rxweather.util.AppConstants;
import java.util.List;
import java.util.stream.Collectors;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        weatherObjWithDaysDao = WeatherDatabase.getDatabase(this).getWeatherObjDao();
        dayModelWithHourModelsDao = WeatherDatabase.getDatabase(this).getDayModelDao();

        loadRxJavaData();
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

    private void onSuccess(Dto dto) {
        WeatherObj weatherObj = new WeatherObj
                (weather_obj_id, dto.address(), CurrentConvertor.convert(dto.currentConditions()));
        daysList = DayModelListConvertor.convertor(dto,weather_obj_id);
        weatherObjWithDays = new WeatherObjWithDays(weatherObj,daysList);

        List<DayModelWithHourModels> dayModelWithHoursList = convertor(dto);
    }

    private List<DayModelWithHourModels> convertor(Dto dto) {
        return dto
                .weather_list_by_days()
                .stream()
                .map(day -> new DayModelWithHourModels(DayToDayModel.dayToDayModel(day,weather_obj_id),))
                .collect(Collectors.toList());
    }

    private void insertWeatherObjToRoom(WeatherObjWithDays weatherObjWithDays,
                                        List<DayModelWithHourModels> dayModelWithHourModelsList){
        WeatherDatabase.databaseWriteExecutor.execute(() ->
                weatherObjWithDaysDao.insertWeatherObjWithDays(weatherObjWithDays));
        WeatherDatabase.databaseWriteExecutor.execute(() ->
                dayModelWithHourModelsDao.insertData(dayModelWithHourModelsList));
    }

    private void onError(Throwable throwable) {
        throwable.printStackTrace();
        throw new RuntimeException(throwable);
    }
}