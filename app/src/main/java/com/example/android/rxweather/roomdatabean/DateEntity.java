package com.example.android.rxweather.roomdatabean;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "date",
        foreignKeys = @ForeignKey(
                entity = CityEntity.class,
                parentColumns = CityEntity.WEATHER_PRIMARY_KEY,
                childColumns = DateEntity.ASSOCIATED_CITY_NAME,
                onDelete = ForeignKey.CASCADE)
        )
public class DateEntity {
    public static final String ASSOCIATED_CITY_NAME = "associated_city_name";
    @PrimaryKey
    @ColumnInfo(name = "day_id")
    @NonNull
    public String dayId;

    @ColumnInfo(name = "datetime_epoch")
    public long datetimeEpochDay;

    @ColumnInfo(name = "temp_max")
    public double tempMaxDay;

    @ColumnInfo(name = "temp_min")
    public double tempMinDay;

    @ColumnInfo(name = "icon")
    public String iconDay;

    @ColumnInfo(name = ASSOCIATED_CITY_NAME)
    public String cityName;

    public DateEntity(@NonNull String dayId,
                      long datetimeEpochDay,
                      double tempMaxDay,
                      double tempMinDay,
                      String iconDay,
                      String cityName) {
        this.dayId = dayId;
        this.datetimeEpochDay = datetimeEpochDay;
        this.tempMaxDay = tempMaxDay;
        this.tempMinDay = tempMinDay;
        this.iconDay = iconDay;
        this.cityName = cityName;
    }
}