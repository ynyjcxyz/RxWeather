package com.example.android.rxweather.roomdatabean;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import java.util.List;

@Dao
public interface DayModelWithHourModelsDao {
    @Transaction
    @Query("SELECT * FROM days")
    List<DayModelWithHourModels> getAllDayList();

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertData(List<DayModelWithHourModels> insertData);

    @Transaction
    @Query("DELETE FROM days")
    void deleteAllDayModels();
}