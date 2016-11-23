package com.jr.weather.bean;

import android.net.Uri;
import android.util.SparseArray;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * //请求参数
 * 文件先不管
 * Created by Administrator on 2016-11-11.
 */

public class RequestParams {
    public LinkedHashMap<String, String> getParams() {
        return params;
    }

    public void setParams(LinkedHashMap<String, String> params) {
        this.params = params;
    }

    private LinkedHashMap<String, String> params = new LinkedHashMap<>();


    public RequestParams add(String key, String val) {
        params.put(key, val);
        return this;
    }


    public String append2Url(String url) {
        if (params == null || params.size() <= 0) {
            return url;
        } else {
            Uri.Builder uriBuilder = Uri.parse(url).buildUpon();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                uriBuilder.appendQueryParameter(entry.getKey(), entry.getValue());
            }
            return uriBuilder.build().toString();
        }
    }
}
