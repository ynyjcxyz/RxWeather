package com.example.android.rxweather.datamodel;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;

@GenerateTypeAdapter
@AutoValue
public abstract class Hours_RX implements Parcelable {
    @SerializedName("datetimeEpoch")
    public abstract long datetimeEpoch_hourly();

    @SerializedName("temp")
    public abstract double temp_hourly();

    @SerializedName("icon")
    public abstract String icon_hourly();
}