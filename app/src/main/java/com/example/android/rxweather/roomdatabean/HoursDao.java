package com.example.android.rxweather.roomdatabean;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface HoursDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<HourModel> hours);

    @Query("SELECT * FROM hours")
    List<HourModel> getAllHours();

    @Query("DELETE FROM hours")
    void deleteAll();
}