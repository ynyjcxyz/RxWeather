package com.example.android.rxweather.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.rxweather.R;
import com.example.android.rxweather.datamodel.Hours_RX;
import com.example.android.rxweather.roomdatabean.HourModel;
import java.util.List;

public class HoursListAdapter extends RecyclerView.Adapter<HoursListViewHolder> {
    private final List<HourModel> itemList;

    public HoursListAdapter(List<HourModel> itemList) {
        this.itemList = itemList;
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
        if (itemList.size() == 0) {
            return 0;
        } else {
            return itemList.size();
        }
    }
}
