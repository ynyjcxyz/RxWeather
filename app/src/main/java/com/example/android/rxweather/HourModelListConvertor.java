package com.example.android.rxweather;

import com.example.android.rxweather.datamodel.Day;
import com.example.android.rxweather.datamodel.Dto;
import com.example.android.rxweather.datamodel.Hours;
import java.util.List;
import java.util.stream.Collectors;

public class HourModelListConvertor {
    public static List<String> hourModelListConvertor(Dto dto){
        List<String> hourlyDateList =
                dto.weather_list_by_days()
                        .stream()
                        .map(Day::datetime_daily)
                        .collect(Collectors.toList());

        List<Hours> hourList =
                dto.weather_list_by_days()
                        .stream()
                        .flatMap(f -> f.hourlyList().stream())
                        .collect(Collectors.toList());

        return null;
    }
}
