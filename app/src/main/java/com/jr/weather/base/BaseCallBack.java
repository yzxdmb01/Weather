package com.jr.weather.base;

import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Administrator on 2016-11-11.
 */

public abstract class BaseCallBack<T> {
    public Type type;

    /**
     * 用于获取泛型的类型
     * @param subclass
     * @return
     */
    static Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            return String.class;
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }

    public BaseCallBack() {
        this.type = getSuperclassTypeParameter(getClass());
    }

    /**
     * 默认的CALLBACK
     */
    public static BaseCallBack CALLBACK_DEFAULT = new BaseCallBack() {
        @Override
        public void onResponse(Object o) {

        }

        @Override
        public void onError(Exception e) {

        }
    };

    /**
     * 请求成功的回调
     * @param t
     * @throws Exception    Gson解析异常
     */
    public abstract void onResponse(T t);

    /**
     * 请求失败回调
     * @param e
     */
    public abstract void onError(Exception e);
}
