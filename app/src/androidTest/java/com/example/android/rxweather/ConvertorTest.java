package com.example.android.rxweather;

import android.os.Parcel;
import com.example.android.rxweather.datamodel.CurrentConditions_RX;
import com.example.android.rxweather.datamodel.Day_RX;
import com.example.android.rxweather.datamodel.Dto_RX;
import com.example.android.rxweather.datamodel.Hours_RX;
import com.example.android.rxweather.roomdatabean.DateEntity;
import com.example.android.rxweather.roomdatabean.HourEntity;
import com.example.android.rxweather.util.Convertor;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class ConvertorTest {
    Dto_RX dto_rx;

    @Before
    public void setUp() throws Exception {
        Hours_RX hours_rx1_1 = new Hours_RX() {
            @Override
            public long datetimeEpoch_hourly() {
                return 1649833200;
            }

            @Override
            public double temp_hourly() {
                return 2.2;
            }

            @Override
            public String icon_hourly() {
                return "clear-night";
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int flags) {
                parcel.writeLong(this.datetimeEpoch_hourly());
                parcel.writeDouble(this.temp_hourly());
                parcel.writeString(this.icon_hourly());
            }
        };
        Hours_RX hours_rx1_2 = new Hours_RX() {
            @Override
            public long datetimeEpoch_hourly() {
                return 1649836800;
            }

            @Override
            public double temp_hourly() {
                return 1.7;
            }

            @Override
            public String icon_hourly() {
                return "clear-night";
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int flags) {
                parcel.writeLong(this.datetimeEpoch_hourly());
                parcel.writeDouble(this.temp_hourly());
                parcel.writeString(this.icon_hourly());
            }
        };
        Hours_RX hours_rx1_3 = new Hours_RX() {
            @Override
            public long datetimeEpoch_hourly() {
                return 1649840400;
            }

            @Override
            public double temp_hourly() {
                return 1.2;
            }

            @Override
            public String icon_hourly() {
                return "clear-night";
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int flags) {
                parcel.writeLong(this.datetimeEpoch_hourly());
                parcel.writeDouble(this.temp_hourly());
                parcel.writeString(this.icon_hourly());
            }
        };

        Hours_RX hours_rx2_1 = new Hours_RX() {
            @Override
            public long datetimeEpoch_hourly() {
                return 1649919600;
            }

            @Override
            public double temp_hourly() {
                return 2.0;
            }

            @Override
            public String icon_hourly() {
                return "cloudy";
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int flags) {
                parcel.writeLong(this.datetimeEpoch_hourly());
                parcel.writeDouble(this.temp_hourly());
                parcel.writeString(this.icon_hourly());
            }
        };
        Hours_RX hours_rx2_2 = new Hours_RX() {
            @Override
            public long datetimeEpoch_hourly() {
                return 1649923200;
            }

            @Override
            public double temp_hourly() {
                return 1.8;
            }

            @Override
            public String icon_hourly() {
                return "cloudy";
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int flags) {
                parcel.writeLong(this.datetimeEpoch_hourly());
                parcel.writeDouble(this.temp_hourly());
                parcel.writeString(this.icon_hourly());
            }
        };
        Hours_RX hours_rx2_3 = new Hours_RX() {
            @Override
            public long datetimeEpoch_hourly() {
                return 1649926800;
            }

            @Override
            public double temp_hourly() {
                return 1.8;
            }

            @Override
            public String icon_hourly() {
                return "cloudy";
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int flags) {
                parcel.writeLong(this.datetimeEpoch_hourly());
                parcel.writeDouble(this.temp_hourly());
                parcel.writeString(this.icon_hourly());
            }
        };

        Hours_RX hours_rx3_1 = new Hours_RX() {
            @Override
            public long datetimeEpoch_hourly() {
                return 1650006000;
            }

            @Override
            public double temp_hourly() {
                return 2.9;
            }

            @Override
            public String icon_hourly() {
                return "partly-cloudy-night";
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int flags) {
                parcel.writeLong(this.datetimeEpoch_hourly());
                parcel.writeDouble(this.temp_hourly());
                parcel.writeString(this.icon_hourly());
            }
        };
        Hours_RX hours_rx3_2 = new Hours_RX() {
            @Override
            public long datetimeEpoch_hourly() {
                return 1650009600;
            }

            @Override
            public double temp_hourly() {
                return 1.9;
            }

            @Override
            public String icon_hourly() {
                return "cloudy";
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int flags) {
                parcel.writeLong(this.datetimeEpoch_hourly());
                parcel.writeDouble(this.temp_hourly());
                parcel.writeString(this.icon_hourly());
            }
        };
        Hours_RX hours_rx3_3 = new Hours_RX() {
            @Override
            public long datetimeEpoch_hourly() {
                return 1650013200;
            }

            @Override
            public double temp_hourly() {
                return 2.0;
            }

            @Override
            public String icon_hourly() {
                return "cloudy";
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int flags) {
                parcel.writeLong(this.datetimeEpoch_hourly());
                parcel.writeDouble(this.temp_hourly());
                parcel.writeString(this.icon_hourly());
            }
        };

        Day_RX day_1 = new Day_RX() {
            @Override
            public String datetime_daily() {
                return "2022-04-13";
            }

            @Override
            public long datetimeEpoch_daily() {
                return 1649833200;
            }

            @Override
            public double temp_max() {
                return 7.6;
            }

            @Override
            public double temp_min() {
                return 1.0;
            }

            @Override
            public String icon_daily() {
                return "partly-cloudy-day";
            }

            @Override
            public List<Hours_RX> hourlyList() {
                return List.of(hours_rx1_1,hours_rx1_2,hours_rx1_3);
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int flags) {
                parcel.writeString(this.datetime_daily());
                parcel.writeLong(this.datetimeEpoch_daily());
                parcel.writeDouble(this.temp_max());
                parcel.writeDouble(this.temp_min());
                parcel.writeString(this.icon_daily());
                parcel.writeParcelableList(this.hourlyList(),flags);
            }
        };
        Day_RX day_2 = new Day_RX() {
            @Override
            public String datetime_daily() {
                return "2022-04-14";
            }

            @Override
            public long datetimeEpoch_daily() {
                return 1649919600;
            }

            @Override
            public double temp_max() {
                return 9.0;
            }

            @Override
            public double temp_min() {
                return 1.2;
            }

            @Override
            public String icon_daily() {
                return "cloudy";
            }

            @Override
            public List<Hours_RX> hourlyList() {
                return List.of(hours_rx2_1,hours_rx2_2,hours_rx2_3);
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int flags) {
                parcel.writeString(this.datetime_daily());
                parcel.writeLong(this.datetimeEpoch_daily());
                parcel.writeDouble(this.temp_max());
                parcel.writeDouble(this.temp_min());
                parcel.writeString(this.icon_daily());
                parcel.writeParcelableList(this.hourlyList(),flags);
            }
        };
        Day_RX day_3 = new Day_RX() {
            @Override
            public String datetime_daily() {
                return "2022-04-15";
            }

            @Override
            public long datetimeEpoch_daily() {
                return 1650006000;
            }

            @Override
            public double temp_max() {
                return 11.5;
            }

            @Override
            public double temp_min() {
                return 1.5;
            }

            @Override
            public String icon_daily() {
                return "partly-cloudy-day";
            }

            @Override
            public List<Hours_RX> hourlyList() {
                return List.of(hours_rx3_1,hours_rx3_2,hours_rx3_3);
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int flags) {
                parcel.writeString(this.datetime_daily());
                parcel.writeLong(this.datetimeEpoch_daily());
                parcel.writeDouble(this.temp_max());
                parcel.writeDouble(this.temp_min());
                parcel.writeString(this.icon_daily());
                parcel.writeParcelableList(this.hourlyList(),flags);
            }
        };

        dto_rx = new Dto_RX() {
            @Override
            public String address() {
                return "Seattle";
            }

            @Override
            public List<Day_RX> weather_list_by_days() {
                return List.of(day_1,day_2,day_3);
            }

            @Override
            public CurrentConditions_RX currentConditions() {
                return new CurrentConditions_RX() {
                    @Override
                    public String datetime_current() {
                        return "14:57:18";
                    }

                    @Override
                    public long datetimeEpoch_current() {
                        return 1649887038;
                    }

                    @Override
                    public double temp_current() {
                        return 7.8;
                    }

                    @Override
                    public String icon_current() {
                        return "cloudy";
                    }

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel parcel, int i) {
                        parcel.writeString(this.datetime_current());
                        parcel.writeLong(this.datetimeEpoch_current());
                        parcel.writeDouble(this.temp_current());
                        parcel.writeString(this.icon_current());
                    }
                };
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int flags) {
                parcel.writeString(this.address());
                parcel.writeParcelableList(this.weather_list_by_days(),flags);
            }
        };
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void unixTimeConvertTo12HourFormat() {
        Assert.assertEquals("09 AM", Convertor.unixTimeConvertTo12HourFormat(1467649596));
        Assert.assertEquals("01 PM", Convertor.unixTimeConvertTo12HourFormat(1000239996));
    }

    @Test
    public void unixTimeConvertToWeekday() {
        Assert.assertEquals("Tuesday", Convertor.unixTimeConvertToWeekday(1000239996));
        Assert.assertEquals("Wednesday", Convertor.unixTimeConvertToWeekday(1341433596));
    }

    @Test
    public void convertToDayList() {
        List<DateEntity> result = Convertor.convertToDayList(dto_rx,"Seattle");
        DateEntity date_1 = new DateEntity("2022-04-13",1649833200,7.6,1.0,"partly-cloudy-day","Seattle");
        DateEntity date_2 = new DateEntity("2022-04-14",1649919600,9.0,1.2,"cloudy","Seattle");
        DateEntity date_3 = new DateEntity("2022-04-15",1650006000,11.5,1.5,"partly-cloudy-day","Seattle");
        Assert.assertEquals(result.size(),List.of(date_1,date_2,date_3).size());
        Assert.assertTrue(result.containsAll(List.of(date_1,date_2,date_3)));
        Assert.assertTrue(List.of(date_1,date_2,date_3).containsAll(result));
    }

    @Test
    public void convertToHourList() {
        List<HourEntity> resultList = Convertor.convertToHourList(dto_rx,"2022-04-13");
        HourEntity hour_1 = new HourEntity(1649833200,2.2,"clear-night","2022-04-13");
        HourEntity hour_2 = new HourEntity(1649836800,1.7,"clear-night","2022-04-13");
        HourEntity hour_3 = new HourEntity(1649840400,1.2,"clear-night","2022-04-13");
        Assert.assertEquals(resultList.size(),List.of(hour_1,hour_2,hour_3).size());
        Assert.assertTrue(resultList.containsAll(List.of(hour_1,hour_2,hour_3)));
        Assert.assertTrue(List.of(hour_1,hour_2,hour_3).containsAll(resultList));
    }
}