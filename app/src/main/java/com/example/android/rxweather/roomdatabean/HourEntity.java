package com.example.android.rxweather.roomdatabean;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "hour",
        foreignKeys = @ForeignKey(
                entity = DateEntity.class,
                parentColumns = "day_id",
                childColumns = HourEntity.ASSOCIATED_DATE_ID,
                onDelete = ForeignKey.CASCADE)
        )
public class HourEntity {
    public static final String ASSOCIATED_DATE_ID = "associated_date_id";
    @PrimaryKey
    @ColumnInfo(name = "datetime_epoch")
    public long datetimeEpochHours;

    @ColumnInfo(name = "temp_hours")
    public double tempHours;

    @ColumnInfo(name = "icon_hours")
    public String iconHours;

    @ColumnInfo(name = ASSOCIATED_DATE_ID)
    public String associatedDateId;

    public HourEntity(long datetimeEpochHours,
                      double tempHours,
                      String iconHours,
                      String associatedDateId) {
        this.datetimeEpochHours = datetimeEpochHours;
        this.tempHours = tempHours;
        this.iconHours = iconHours;
        this.associatedDateId = associatedDateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HourEntity)) return false;

        HourEntity that = (HourEntity) o;

        if (datetimeEpochHours != that.datetimeEpochHours) return false;
        if (Double.compare(that.tempHours, tempHours) != 0) return false;
        if (!Objects.equals(iconHours, that.iconHours))
            return false;
        return Objects.equals(associatedDateId, that.associatedDateId);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (datetimeEpochHours ^ (datetimeEpochHours >>> 32));
        temp = Double.doubleToLongBits(tempHours);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (iconHours != null ? iconHours.hashCode() : 0);
        result = 31 * result + (associatedDateId != null ? associatedDateId.hashCode() : 0);
        return result;
    }
}