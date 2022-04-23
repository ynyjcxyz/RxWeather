package com.example.android.rxweather.roomdatabean;

import androidx.room.Embedded;
import androidx.room.Relation;
import java.util.List;

public class DayModelWithHourModels {
    @Embedded
    public DayModel dayModel;
    @Relation(
            parentColumn = "datetime_day",
            entityColumn = "datetime_current_day"
    )
    public List<HourModel> hourModels;

    public DayModelWithHourModels(DayModel dayModel, List<HourModel> hourModels) {
        this.dayModel = dayModel;
        this.hourModels = hourModels;
    }

    public DayModel getDayModel() {
        return dayModel;
    }

    public void setDayModel(DayModel dayModel) {
        this.dayModel = dayModel;
    }

    public List<HourModel> getHourModels() {
        return hourModels;
    }

    public void setHourModels(List<HourModel> hourModels) {
        this.hourModels = hourModels;
    }
}