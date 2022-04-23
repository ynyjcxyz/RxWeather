package com.example.android.rxweather.roomdatabean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "hours")
public class HourModel {
    @PrimaryKey
    @ColumnInfo(name = "datetimeEpoch")
    public long datetimeEpoch_hours;

    public double temp_hours;
    public String icon_hours;

    @ColumnInfo(name = "datetime_current_day")
    public String datetime_current_day;

    public HourModel(long datetimeEpoch_hours,
                     double temp_hours,
                     String icon_hours,
                     String datetime_current_day) {
        this.datetimeEpoch_hours = datetimeEpoch_hours;
        this.temp_hours = temp_hours;
        this.icon_hours = icon_hours;
        this.datetime_current_day = datetime_current_day;
    }
}