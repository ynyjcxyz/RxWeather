package com.example.android.rxweather;

import com.example.android.rxweather.datamodel.Day_RX;
import com.example.android.rxweather.datamodel.Dto_RX;
import com.example.android.rxweather.datamodel.Hours_RX;
import com.example.android.rxweather.roomdatabean.HourModel;
import java.util.List;
import java.util.stream.Stream;

public class HourModelListConvertor {
    public static List<HourModel> hourModelListConvertor(Dto_RX dtoRX){
        Stream<String> hourlyDateList =
                dtoRX.weather_list_by_days()
                        .stream()
                        .map(Day_RX::datetime_daily);

        Stream<Hours_RX> hourList =
                dtoRX.weather_list_by_days()
                        .stream()
                        .flatMap(f -> f.hourlyList().stream());
        return null;
    }
}
