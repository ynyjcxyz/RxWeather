package com.example.android.rxweather.recyclerview;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.rxweather.R;
import com.example.android.rxweather.roomdatabean.DateEntity;
import com.example.android.rxweather.roomdatabean.HourEntity;
import java.util.List;

public class DaysListAdapter extends RecyclerView.Adapter<DaysListViewHolder> {
    List<DateEntity> itemList;
    List<HourEntity> hourList;

    public DaysListAdapter(List<DateEntity> itemList, List<HourEntity> hourList) {
        this.itemList = itemList;
        this.hourList = hourList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setDaysListAdapterData(List<DateEntity> dataList, List<HourEntity> hourList_today) {
        itemList = dataList;
        hourList = hourList_today;
//        notifyDataSetChanged();
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
        holder.bindDaysData(itemList.get(position),hourList);

//        if(position == 0){
//            holder.image_days.setVisibility(View.GONE);
//            holder.recyclerview_today_hourly.setVisibility(View.VISIBLE);
//        }else{
//            holder.recyclerview_today_hourly.setVisibility(View.INVISIBLE);
//        }
    }

    @Override
    public int getItemCount() {
        if(itemList == null){
            return 0;
        } else{
            return itemList.size();
        }
    }
}
