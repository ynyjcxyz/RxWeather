package com.example.android.rxweather.roomdatabean;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class CurrentCondition {
    @PrimaryKey
    @ColumnInfo(name = "datetime")
    public String datetime_current;

    public long datetimeEpoch_current;
    public double temp_current;
    public String icon_current;

    public CurrentCondition(String datetime_current,
                            long datetimeEpoch_current,
                            double temp_current,
                            String icon_current) {
        this.datetime_current = datetime_current;
        this.datetimeEpoch_current = datetimeEpoch_current;
        this.temp_current = temp_current;
        this.icon_current = icon_current;
    }
}