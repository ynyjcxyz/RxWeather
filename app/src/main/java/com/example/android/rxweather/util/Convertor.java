package com.example.android.rxweather.util;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
}
