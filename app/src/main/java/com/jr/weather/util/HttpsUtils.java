package com.jr.weather.util;

import android.net.Uri;

import com.jr.weather.base.ICallBack;
import com.jr.weather.bean.RequestParams;

import java.io.File;
import java.util.Map;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 网络请求
 * 可以设置不同的请求超时等
 * Created by Administrator on 2016-11-11.
 */

public class HttpsUtils {
    private static final long DEFAULT_MILLISECONDS = 10 * 1000l;
    private volatile OkHttpClient okHttpClient;
    private static HttpsUtils mInstance;
    private static int cacheSize = 10 * 1024 * 1024;
    private static Cache cache = new Cache(new File("bzh.bmp"), cacheSize);

    public HttpsUtils(OkHttpClient okHttpClient) {
        if (okHttpClient == null) {
            this.okHttpClient = new OkHttpClient.Builder().cache(cache).build();
        } else {
            this.okHttpClient = okHttpClient;
        }
    }


    public static HttpsUtils getInstance() {
        return initHttpsUtils(null);
    }

    private static HttpsUtils initHttpsUtils(OkHttpClient okHttpClient) {
        if (mInstance == null) {
            synchronized (HttpsUtils.class) {
                if (mInstance == null) {
                    mInstance = new HttpsUtils(okHttpClient);
                }
            }
        }
        return mInstance;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    //get方法
    //先想用法HttpsUtils.get(url,params,callback);
    public static void get(String url, RequestParams params, ICallBack callBack) {

        Request request = new Request.Builder().url(url).get().build();

    }

}
