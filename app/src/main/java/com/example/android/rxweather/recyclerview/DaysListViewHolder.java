package com.example.android.rxweather.recyclerview;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.android.rxweather.R;
import com.example.android.rxweather.roomdatabean.DateEntity;
import com.example.android.rxweather.roomdatabean.HourEntity;
import com.example.android.rxweather.util.AppConstants;
import com.example.android.rxweather.util.Convertor;
import java.util.List;

public class DaysListViewHolder extends RecyclerView.ViewHolder{
    LinearLayout parent_layout;
    TextView monday_to_sunday_days,temperature_max_days,temperature_min_days;
    ImageView image_days;
    RecyclerView recyclerview_today_hourly;
    HoursListAdapter hoursListAdapter;

    public DaysListViewHolder(@NonNull View itemView) {
        super(itemView);
        parent_layout = itemView.findViewById(R.id.list_item_days);
        monday_to_sunday_days = itemView.findViewById(R.id.monday_to_sunday_days);
        temperature_max_days = itemView.findViewById(R.id.temperature_max_days);
        temperature_min_days = itemView.findViewById(R.id.temperature_min_days);
        image_days = itemView.findViewById(R.id.image_days);
        recyclerview_today_hourly = itemView.findViewById(R.id.recyclerview_today_hourly);
    }

    @SuppressLint("SetTextI18n")
    public void bindDaysData(DateEntity dateEntity, List<HourEntity> hourEntityList){
        monday_to_sunday_days.setText(Convertor.unixTimeConvertToWeekday((dateEntity.datetimeEpochDay)));
        temperature_max_days.setText(dateEntity.tempMaxDay +"°");
        temperature_min_days.setText(dateEntity.tempMinDay +"°");
        Glide.with(itemView.getContext())
                .load(AppConstants.iconBaseUrl +
                        AppConstants.second_set_color +
                        dateEntity.iconDay +
                        ".png")
                .into(image_days);

        recyclerview_today_hourly.setHasFixedSize(true);
        recyclerview_today_hourly.addItemDecoration(new DividerItemDecoration(itemView.getContext(), DividerItemDecoration.HORIZONTAL));
        recyclerview_today_hourly.setLayoutManager(new LinearLayoutManager(itemView.getContext(), RecyclerView.HORIZONTAL,false));
        hoursListAdapter = new HoursListAdapter(hourEntityList);
        hoursListAdapter.setHoursListAdapterData(hourEntityList);
        recyclerview_today_hourly.setAdapter(hoursListAdapter);
    }

}