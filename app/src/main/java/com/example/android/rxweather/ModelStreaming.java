package com.example.android.rxweather;

import androidx.annotation.NonNull;
import com.example.android.rxweather.roomdatabean.DateDao;
import com.example.android.rxweather.roomdatabean.DateEntity;
import com.example.android.rxweather.roomdatabean.DateModel;
import com.example.android.rxweather.roomdatabean.HourDao;
import com.example.android.rxweather.roomdatabean.HourEntity;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ModelStreaming {

  private final DateDao dateDao;
  private final HourDao hourDao;

  public ModelStreaming(DateDao dateDao, HourDao hourDao) {
    this.dateDao = dateDao;
    this.hourDao = hourDao;
  }

  public Observable<List<DateModel>> modelStreaming() {
    return loadingSignal()
        .switchMap(aLong -> dateDao.getAllDayList())
        .flatMap(this::dateModelList)
        .distinctUntilChanged()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }


  private Observable<List<DateModel>> dateModelList(List<DateEntity> dateList) {
    return hourDao.observe(currentDay()).map(hourList -> model(hourList, dateList));
  }

  private List<DateModel> model(List<HourEntity> hourList, List<DateEntity> dateList) {
    List<DateModel> result = new ArrayList<>();
    for (int i = 0; i < dateList.size(); i++) {
      result.add(new DateModel(dateList.get(i), i == 0 ? hourList : null));
    }
    return result;

  }

  private Observable<Long> loadingSignal() {
    return Observable.interval(0, 3, TimeUnit.SECONDS);
  }


  @NonNull
  private static String currentDay() {
    return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
  }
}
