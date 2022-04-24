package com.example.android.rxweather;

import com.example.android.rxweather.datamodel.CurrentConditions_RX;
import com.example.android.rxweather.roomdatabean.CurrentCondition;

public class CurrentConvertor {
    public static CurrentCondition convert(CurrentConditions_RX conditionFromDto) {
        return new CurrentCondition(
                conditionFromDto.datetime_current(),
                conditionFromDto.datetimeEpoch_current(),
                conditionFromDto.temp_current(),
                conditionFromDto.icon_current());
    }
}