package com.example.android.rxweather.datamodel;

import android.os.Parcelable;
import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.util.List;

@GenerateTypeAdapter
@AutoValue
public abstract class Day_RX implements Parcelable {
    @SerializedName("datetime")
    public abstract String datetime_daily();

    @SerializedName("datetimeEpoch")
    public abstract long datetimeEpoch_daily();

    @SerializedName("tempmax")
    public abstract double temp_max();

    @SerializedName("tempmin")
    public abstract double temp_min();

    @SerializedName("icon")
    public abstract String icon_daily();

    @SerializedName("hours")
    public abstract List<Hours_RX> hourlyList();
}