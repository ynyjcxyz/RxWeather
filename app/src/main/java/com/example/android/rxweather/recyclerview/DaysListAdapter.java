package com.example.android.rxweather.recyclerview;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.rxweather.R;
import com.example.android.rxweather.roomdatabean.DateEntity;
import java.util.List;

public class DaysListAdapter extends RecyclerView.Adapter<DaysListViewHolder> {
    private List<DateEntity> itemList;

    public DaysListAdapter(List<DateEntity> itemList) {
        this.itemList = itemList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setDaysListAdapterData(List<DateEntity> dataList) {
        itemList = dataList;
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
        holder.bindDaysData(itemList.get(position));
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
