package com.example.android.rxweather.recyclerview;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.rxweather.R;
import com.example.android.rxweather.roomdatabean.DateModel;
import java.util.List;

public class DaysListAdapter extends RecyclerView.Adapter<DaysListViewHolder> {

  List<DateModel> modelList;

  public DaysListAdapter() {
  }


  @SuppressLint("NotifyDataSetChanged")
  public void setModelList(List<DateModel> modelList) {
    this.modelList = modelList;
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public DaysListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new DaysListViewHolder
        (LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.list_item_daily, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull DaysListViewHolder holder, int position) {
    DateModel dateModel = modelList.get(position);
    holder.bindDaysData(dateModel.dateEntity, dateModel.list);
  }

  @Override
  public int getItemCount() {
    if (modelList == null) {
      return 0;
    } else {
      return modelList.size();
    }
  }
}
