package com.example.android.rxweather.roomdatabean;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CityEntity)) return false;

        CityEntity that = (CityEntity) o;

        if (datetimeEpochCurrent != that.datetimeEpochCurrent) return false;
        if (Double.compare(that.tempCurrent, tempCurrent) != 0) return false;
        if (!address.equals(that.address)) return false;
        if (!Objects.equals(datetimeCurrent, that.datetimeCurrent))
            return false;
        return Objects.equals(iconCurrent, that.iconCurrent);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = address.hashCode();
        result = 31 * result + (datetimeCurrent != null ? datetimeCurrent.hashCode() : 0);
        result = 31 * result + (int) (datetimeEpochCurrent ^ (datetimeEpochCurrent >>> 32));
        temp = Double.doubleToLongBits(tempCurrent);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (iconCurrent != null ? iconCurrent.hashCode() : 0);
        return result;
    }
}