package com.example.android.rxweather.datamodel;

import android.os.Parcelable;
import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;

@GenerateTypeAdapter
@AutoValue
public abstract class CurrentConditions_RX implements Parcelable {
    @SerializedName("datetime")
    public abstract String datetime_current();

    @SerializedName("datetimeEpoch")
    public abstract long datetimeEpoch_current();

    @SerializedName("temp")
    public abstract double temp_current();

    @SerializedName("icon")
    public abstract String icon_current();
}
