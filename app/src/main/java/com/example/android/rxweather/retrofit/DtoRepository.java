package com.example.android.rxweather.retrofit;

import static com.example.android.rxweather.retrofit.GetRetrofitObj.retrofitService;
import com.example.android.rxweather.RequestParam;
import com.example.android.rxweather.datamodel.Dto_RX;
import io.reactivex.Observable;

public class DtoRepository {
    public static Observable<Dto_RX> getDto(RequestParam requestParam) {
        return retrofitService().getDto(
                requestParam.cityName,
                requestParam.unitGroup,
                requestParam.key,
                requestParam.contentType);
    }
}
