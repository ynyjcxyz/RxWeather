package com.example.android.rxweather.roomdatabean;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "days")
public class DayModel {
    @PrimaryKey
    @ColumnInfo(name = "datetime")
    @NonNull
    public String datetime_day;

    public long datetimeEpoch_day;
    public double temp_max_day;
    public double temp_min_day;
    public String icon_day;

    @Ignore
    public List<HourModel> hours;

    @ColumnInfo(name = "weather_obj_id")
    public int weather_obj_id;

    public DayModel(String datetime_day, long datetimeEpoch_day, double temp_max_day, double temp_min_day, String icon_day, int weather_obj_id) {
        this.datetime_day = datetime_day;
        this.datetimeEpoch_day = datetimeEpoch_day;
        this.temp_max_day = temp_max_day;
        this.temp_min_day = temp_min_day;
        this.icon_day = icon_day;
        this.weather_obj_id = weather_obj_id;
    }
}