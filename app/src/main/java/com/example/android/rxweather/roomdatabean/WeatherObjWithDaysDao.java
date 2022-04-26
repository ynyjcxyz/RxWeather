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
    List<WeatherObj> getAllWeatherObj();

    @Transaction
    @Query("SELECT * FROM weather_table WHERE id = 0")
    Observable <WeatherObj> getWeatherObjWithDays();

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertWeatherObj(WeatherObj WeatherObj);

    @Transaction
    @Query("DELETE FROM weather_table")
    void deleteAllWeatherObj();     // delete all weatherObjWithDays
}