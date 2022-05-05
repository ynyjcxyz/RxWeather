package com.example.android.rxweather.roomdatabean;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "city")
public class CityEntity {
    public static final String WEATHER_PRIMARY_KEY = "city_name";
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = WEATHER_PRIMARY_KEY)
    public String address;

    @ColumnInfo(name = "datetime_current")
    public String datetimeCurrent;

    @ColumnInfo(name = "datetime_epoch_current")
    public long datetimeEpochCurrent;

    @ColumnInfo(name = "temp_current")
    public double tempCurrent;

    @ColumnInfo(name = "icon_current")
    public String iconCurrent;

    public CityEntity(@NonNull String address,
                      String datetimeCurrent,
                      long datetimeEpochCurrent,
                      double tempCurrent,
                      String iconCurrent) {
        this.address = address;
        this.datetimeCurrent = datetimeCurrent;
        this.datetimeEpochCurrent = datetimeEpochCurrent;
        this.tempCurrent = tempCurrent;
        this.iconCurrent = iconCurrent;
    }
}