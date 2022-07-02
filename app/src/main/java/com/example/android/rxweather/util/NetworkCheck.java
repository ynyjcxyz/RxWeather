package com.example.android.rxweather.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkCheck {
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null) {
                return networkInfo.isAvailable();
            }
        }
        return false;
    }
}
