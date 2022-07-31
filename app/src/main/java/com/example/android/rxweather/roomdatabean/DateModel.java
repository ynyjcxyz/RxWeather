package com.example.android.rxweather.roomdatabean;

import androidx.annotation.Nullable;
import java.util.List;

public class DateModel {
  public final DateEntity dateEntity;

  @Nullable
  public final List<HourEntity> list;

  public DateModel(DateEntity dateEntity, @Nullable List<HourEntity> list) {
    this.dateEntity = dateEntity;
    this.list = list;
  }
}