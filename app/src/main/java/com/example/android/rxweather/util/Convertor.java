package com.example.android.rxweather.util;

import android.annotation.SuppressLint;
import com.example.android.rxweather.datamodel.Day_RX;
import com.example.android.rxweather.datamodel.Dto_RX;
import com.example.android.rxweather.datamodel.Hours_RX;
import com.example.android.rxweather.roomdatabean.DateEntity;
import com.example.android.rxweather.roomdatabean.HourEntity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
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

    public static List<DateEntity> convertToDayList(Dto_RX dtoRX, String cityName) {
        return dtoRX
                .weather_list_by_days()
                .stream()
                .map(day ->
                        new DateEntity(day.datetime_daily(),
                                day.datetimeEpoch_daily(),
                                day.temp_max(),
                                day.temp_min(),
                                day.icon_daily(),
                                cityName))
                .collect(Collectors.toList());
    }

    public static List<HourEntity> convertToHourList(Dto_RX dtoRx,String dateId){
        return dtoRx
                .weather_list_by_days()
                .stream()
                .filter(day -> day.datetime_daily().equals(dateId))
                .map(Day_RX::hourlyList)
                .map(list -> hourList(list, dateId))
                .findFirst()
                .orElse(Collections.emptyList());
    }

    private static List<HourEntity> hourList(List<Hours_RX> list, String datetime_current_day) {
        return list
                .stream()
                .map(rx -> new HourEntity(
                        rx.datetimeEpoch_hourly(),
                        rx.temp_hourly(),
                        rx.icon_hourly(),
                        datetime_current_day))
                .collect(Collectors.toList());
    }
}