package com.example.android.rxweather.roomdatabean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "days")
public class DayModel {
    @PrimaryKey
    @ColumnInfo(name = "datetime")
    public String datetime_day;

    public long datetimeEpoch_day;
    public double temp_max_day;
    public double temp_min_day;
    public String icon_day;

    @Ignore
    public List<HourModel> hours;

    @ColumnInfo(name = "weather_obj_id")
    public int weather_obj_id;

    public DayModel(String datetime_daily,
                    long datetimeEpoch_daily,
                    double temp_max,
                    double temp_min,
                    String icon_daily,
                    int weatherObjId) {
        this.datetime_day = datetime_daily;
        this.datetimeEpoch_day = datetimeEpoch_daily;
        this.temp_max_day = temp_max;
        this.temp_min_day = temp_min;
        this.icon_day = icon_daily;
        this.weather_obj_id = weatherObjId;
    }
}