package com.example.android.rxweather.retrofit;

import static com.example.android.rxweather.retrofit.GetRetrofitObj.retrofitService;
import com.example.android.rxweather.RequestParam;
import com.example.android.rxweather.datamodel.Dto;
import io.reactivex.Observable;

public class DtoRepository {
    public static Observable<Dto> getDto(RequestParam requestParam) {
        return retrofitService().getDto(
                requestParam.cityName,
                requestParam.unitGroup,
                requestParam.key,
                requestParam.contentType);
    }
}
