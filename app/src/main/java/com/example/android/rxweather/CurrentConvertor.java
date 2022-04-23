package com.example.android.rxweather;

import com.example.android.rxweather.datamodel.CurrentConditions;
import com.example.android.rxweather.roomdatabean.CurrentCondition;

public class CurrentConvertor {
    public static CurrentCondition convert(CurrentConditions conditionFromDto) {
        return new CurrentCondition(
                conditionFromDto.datetime_current(),
                conditionFromDto.datetimeEpoch_current(),
                conditionFromDto.temp_current(),
                conditionFromDto.icon_current());
    }
}