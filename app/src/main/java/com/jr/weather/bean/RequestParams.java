package com.jr.weather.bean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * //请求参数
 * Created by Administrator on 2016-11-11.
 */

public class RequestParams {
    List<RequestParamsBean> params = new ArrayList<>();

    public RequestParams add(String key, String val) {
        params.add(new RequestParamsBean(key, val));
        return this;
    }

    public RequestParams add(String key, File val) {
        params.add(new RequestParamsBean(key, val));
        return this;
    }

    private class RequestParamsBean {
        String key;
        Object value;

        public RequestParamsBean(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }
}
