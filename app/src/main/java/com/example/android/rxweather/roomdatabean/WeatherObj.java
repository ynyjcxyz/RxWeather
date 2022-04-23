package com.example.android.rxweather.roomdatabean;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import java.util.List;

@Entity(tableName = "weather_table")
public class WeatherObj {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String address;

    @Ignore
    public List<DayModel> days;

    @Embedded
    public CurrentCondition currentCondition;

    public WeatherObj(int id, String address, CurrentCondition currentCondition) {
        this.id = id;
        this.address = address;
        this.currentCondition = currentCondition;
    }
}