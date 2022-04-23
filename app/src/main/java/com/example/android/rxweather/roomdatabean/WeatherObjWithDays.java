package com.example.android.rxweather.roomdatabean;

import androidx.room.Embedded;
import androidx.room.Relation;
import java.util.List;

public class WeatherObjWithDays {
    @Embedded
    public WeatherObj weatherObj;

    @Relation(parentColumn = "id", entityColumn = "weather_obj_id")
    public List<DayModel> days;

    public WeatherObjWithDays(WeatherObj weatherObj, List<DayModel> days) {
        this.weatherObj = weatherObj;
        this.days = days;
    }
}