package com.example.android.rxweather.roomdatabean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

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
}