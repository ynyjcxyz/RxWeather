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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DateEntity)) return false;

        DateEntity that = (DateEntity) o;

        if (datetimeEpochDay != that.datetimeEpochDay) return false;
        if (Double.compare(that.tempMaxDay, tempMaxDay) != 0) return false;
        if (Double.compare(that.tempMinDay, tempMinDay) != 0) return false;
        if (!dayId.equals(that.dayId)) return false;
        if (iconDay != null ? !iconDay.equals(that.iconDay) : that.iconDay != null) return false;
        return cityName != null ? cityName.equals(that.cityName) : that.cityName == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = dayId.hashCode();
        result = 31 * result + (int) (datetimeEpochDay ^ (datetimeEpochDay >>> 32));
        temp = Double.doubleToLongBits(tempMaxDay);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(tempMinDay);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (iconDay != null ? iconDay.hashCode() : 0);
        result = 31 * result + (cityName != null ? cityName.hashCode() : 0);
        return result;
    }
}