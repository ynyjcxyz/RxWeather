package com.example.android.rxweather.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.rxweather.R;
import com.example.android.rxweather.roomdatabean.DayModel;

import java.util.List;

public class DaysListAdapter extends RecyclerView.Adapter<DaysListViewHolder> {
    private final List<DayModel> itemList;

    public DaysListAdapter(List<DayModel> itemList) {
        this.itemList = itemList;
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
        if(itemList.size() == 0){
            return 0;
        } else{
            return itemList.size();
        }
    }
}
