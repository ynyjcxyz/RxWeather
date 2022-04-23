package com.example.android.rxweather.recyclerview;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.android.rxweather.R;
import com.example.android.rxweather.datamodel.Day;
import com.example.android.rxweather.util.Convertor;

public class DaysListViewHolder extends RecyclerView.ViewHolder{
    RelativeLayout parent_layout;
    TextView monday_to_sunday_days,temperature_max_days,temperature_min_days;
    ImageView image_days;

    public DaysListViewHolder(@NonNull View itemView) {
        super(itemView);

        parent_layout = itemView.findViewById(R.id.list_item_days);
        monday_to_sunday_days = itemView.findViewById(R.id.monday_to_sunday_days);
        temperature_max_days = itemView.findViewById(R.id.temperature_max_days);
        temperature_min_days = itemView.findViewById(R.id.temperature_min_days);
        image_days = itemView.findViewById(R.id.image_days);
    }
    @SuppressLint("SetTextI18n")
    public void bindDaysData(Day daysItem){
        monday_to_sunday_days.setText(Convertor.unixTimeConvertToWeekday((daysItem.datetimeEpoch_daily())));
        temperature_max_days.setText(daysItem.temp_max() +"°");
        temperature_min_days.setText(daysItem.temp_min() +"°");
        Glide.with(itemView.getContext()).load(daysItem.icon_daily()).into(image_days);
    }

}