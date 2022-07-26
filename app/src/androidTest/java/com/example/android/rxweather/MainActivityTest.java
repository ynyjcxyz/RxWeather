package com.example.android.rxweather;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.test.core.app.ActivityScenario;

import com.example.android.rxweather.roomdatabean.CityEntity;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void onCreate() throws Exception{
        try(ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
            scenario.onActivity(activity -> {
                TextView location_name = activity.findViewById(R.id.location_name);
                TextView current_temperature = activity.findViewById(R.id.current_temperature);
                TextView current_condition_string = activity.findViewById(R.id.current_condition_string);
                TextView monday_to_sunday = activity.findViewById(R.id.monday_to_sunday);
                ImageView icon = activity.findViewById(R.id.icon);

                CityEntity testEntity = new CityEntity("Seattle","14:57:18",1649887038,7.8,"cloudy");
                location_name.setText(testEntity.address);

                Assert.assertEquals("Seattle",location_name.getText().toString());
            });
        }
    }
}