package com.example.android.rxweather.util;

import com.example.android.rxweather.RequestParam;

public class AppConstants {
    //These are weather API url parameters
    public static final RequestParam PARAM =
            new RequestParam("Seattle",
                    "metric",
                    "UDR74JLWCB3CRZBZQSTL3AVQH",
                    "json");

    //This is weather API baseUrl
    public static final String baseUrl = "https://weather.visualcrossing.com/";

    //These are weather icons(.png format,64X64) parameter
    public static final String iconBaseUrl = "https://raw.githubusercontent.com/visualcrossing/WeatherIcons/main/PNG/";
    public static final String first_set_monochrome = "1st%20Set%20-%20Monochrome/";
    public static final String first_set_color = "1st%20Set%20-%20Color/";
    public static final String second_set_monochrome = "2nd%20Set%20-%20Monochrome/";
    public static final String second_set_color = "2nd%20Set%20-%20Color/";
    public static final String third_set_monochrome = "3rd%20Set%20-%20Monochrome/";
    public static final String third_set_color = "3rd%20Set%20-%20Color/";
    public static final String fourth_set_monochrome = "4th%20Set%20-%20Monochrome/";
    public static final String fourth_set_color = "4th%20Set%20-%20Color/";
}
