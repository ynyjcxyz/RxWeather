package com.example.android.rxweather.roomdatabean;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {CityEntity.class, DateEntity.class, HourEntity.class}, version = 1)
public abstract class WeatherDatabase extends RoomDatabase {
    private static WeatherDatabase INSTANCE;

    public abstract CityDao getCityDao();
    public abstract DateDao getDateDao();
    public abstract HourDao getHourDao();

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static WeatherDatabase getDatabase(final Context context) {
        if (INSTANCE == null){
            synchronized (WeatherDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room
                            .databaseBuilder(context.getApplicationContext(),
                                    WeatherDatabase.class,
                                    "weather_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}