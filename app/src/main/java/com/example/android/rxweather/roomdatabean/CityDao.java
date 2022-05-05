package com.example.android.rxweather.roomdatabean;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import java.util.List;
import io.reactivex.Observable;

@Dao
public interface CityDao {
    @Transaction
    @Query("SELECT * FROM city")
    List<CityEntity> getAllWeatherList();

    @Query("SELECT * FROM city WHERE city_name = :cityName")
    Observable<CityEntity> getWeatherObj(String cityName);

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertWeather(CityEntity cityEntity);

    @Transaction
    @Query("DELETE FROM city")
    void deleteAllWeather();
}