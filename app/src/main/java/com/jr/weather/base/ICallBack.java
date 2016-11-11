package com.jr.weather.base;

/**
 * Created by Administrator on 2016-11-11.
 */

public interface ICallBack<T> {
    void onSuccess(T t);

    void onError(T t);
}
