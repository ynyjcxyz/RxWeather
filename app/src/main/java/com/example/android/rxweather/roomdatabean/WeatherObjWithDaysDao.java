package com.example.android.rxweather.roomdatabean;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import java.util.List;
import io.reactivex.Observable;

@Dao
public interface WeatherObjWithDaysDao {
    @Transaction
    @Query("SELECT * FROM weather_table")
    List<WeatherObjWithDays> getAllWeatherObj();

    @Transaction
    @Query("SELECT * FROM weather_table WHERE id = 0")
    Observable <WeatherObjWithDays> getWeatherObjWithDays();

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertWeatherObjWithDays(WeatherObjWithDays weatherObjWithDays);

    @Transaction
    @Query("DELETE FROM weather_table")
    void deleteAllWeatherObjWithDays();     // delete all weatherObjWithDays
}