package com.example.android.rxweather.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.rxweather.R;
import com.example.android.rxweather.roomdatabean.HourEntity;
import java.util.List;

public class HoursListAdapter extends RecyclerView.Adapter<HoursListViewHolder> {
    private List<HourEntity> itemList;

    public HoursListAdapter(List<HourEntity> itemList) {
        this.itemList = itemList;
    }

    public void setHoursListAdapterData(List<HourEntity> dataList) {
        itemList = dataList;
//        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HoursListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HoursListViewHolder
                (LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.list_item_hourly, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HoursListViewHolder holder, int position) {
        holder.bindHoursData(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        if (itemList == null) {
            return 0;
        } else {
            return itemList.size();
        }
    }
}
