package com.example.android.rxweather.datamodel;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;

import java.util.List;

@GenerateTypeAdapter
@AutoValue
public abstract class Dto_RX implements Parcelable {
    @SerializedName("address")
    public abstract String address();

    @SerializedName("days")
    public abstract List<Day_RX> weather_list_by_days();

    @SerializedName("currentConditions")
    public abstract CurrentConditions_RX currentConditions();

    static Dto_RX create(String address,List<Day_RX> weather_list_by_days,CurrentConditions_RX currentConditions){
        return new AutoValue_Dto_RX(address, weather_list_by_days, currentConditions);
    }
}