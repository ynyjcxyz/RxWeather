package com.example.android.rxweather.roomdatabean;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

import io.reactivex.Observable;

@Dao
public interface HourDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<HourEntity> hours);

    @Query("SELECT * FROM hour")
    List<HourEntity> getAllHours();

    @Query("SELECT * FROM hour WHERE associated_date_id = :systemCurrentDate")
    Observable<List<HourEntity>> observe(String systemCurrentDate);

    @Query("DELETE FROM hour")
    void deleteAll();
}