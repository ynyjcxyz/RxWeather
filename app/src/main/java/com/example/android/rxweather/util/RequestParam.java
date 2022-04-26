package com.example.android.rxweather.util;

public class RequestParam {
    public final String cityName;
    public final String unitGroup;
    public final String key;
    public final String contentType;

    public RequestParam(String cityName,
                        String unitGroup,
                        String key,
                        String contentType) {
        this.cityName = cityName;
        this.unitGroup = unitGroup;
        this.key = key;
        this.contentType = contentType;
    }
}
