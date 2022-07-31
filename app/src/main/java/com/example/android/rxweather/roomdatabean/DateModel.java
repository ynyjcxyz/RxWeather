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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    DateModel dateModel = (DateModel) o;

    if (dateEntity != null ? !dateEntity.equals(dateModel.dateEntity)
        : dateModel.dateEntity != null) {
      return false;
    }
    return list != null ? list.equals(dateModel.list) : dateModel.list == null;
  }

  @Override
  public int hashCode() {
    int result = dateEntity != null ? dateEntity.hashCode() : 0;
    result = 31 * result + (list != null ? list.hashCode() : 0);
    return result;
  }
}