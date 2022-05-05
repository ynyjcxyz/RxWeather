package com.example.android.rxweather.roomdatabean;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import java.util.List;
import io.reactivex.Observable;

@Dao
public interface DateDao {
    @Transaction
    @Query("SELECT * FROM date")
    Observable <List<DateEntity>> getAllDayList();

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertData(List<DateEntity> insertData);

    @Transaction
    @Query("DELETE FROM date")
    void deleteAllDay();
}
