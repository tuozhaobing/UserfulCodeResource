package com.baidu.platform.core.b;

import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;

public interface f {
    void a();

    void a(OnGetGeoCoderResultListener onGetGeoCoderResultListener);

    boolean a(GeoCodeOption geoCodeOption);

    boolean a(ReverseGeoCodeOption reverseGeoCodeOption);
}
