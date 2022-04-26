package com.example.android.rxweather.util;

import android.annotation.SuppressLint;

import com.example.android.rxweather.datamodel.CurrentConditions_RX;
import com.example.android.rxweather.datamodel.Dto_RX;
import com.example.android.rxweather.roomdatabean.CurrentCondition;
import com.example.android.rxweather.roomdatabean.DayModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Convertor {
    public static String unixTimeConvertTo12HourFormat(long datetimeEpoch_hourly) {
        Calendar calendar = Calendar.getInstance();
        long timeInput = datetimeEpoch_hourly * 1000;
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh aa");
        calendar.setTimeInMillis(timeInput);
        return dateFormat.format(calendar.getTime());
    }

    public static String unixTimeConvertToWeekday(long datetimeEpoch_daily) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date dateFormat = new Date(datetimeEpoch_daily * 1000);
        return sdf.format(dateFormat);
    }

    public static CurrentCondition convertToCurrentCondition(CurrentConditions_RX conditionFromDto) {
        return new CurrentCondition(
                conditionFromDto.datetime_current(),
                conditionFromDto.datetimeEpoch_current(),
                conditionFromDto.temp_current(),
                conditionFromDto.icon_current());
    }

    public static List<DayModel> convertorToDayModelList(Dto_RX dtoRX, int obj_id) {
        return dtoRX
                .weather_list_by_days()
                .stream()
                .map(day ->
                        new DayModel(day.datetime_daily(),
                                day.datetimeEpoch_daily(),
                                day.temp_max(),
                                day.temp_min(),
                                day.icon_daily(),
                                obj_id))
                .collect(Collectors.toList());
    }
}
