package com.jr.weather.util;

import com.google.gson.Gson;
import com.jr.weather.base.BaseApplication;
import com.jr.weather.base.BaseCallBack;
import com.jr.weather.bean.RequestParams;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 网络请求
 * 可以设置不同的请求超时等
 * Created by Administrator on 2016-11-11.
 */

public class HttpsUtils {
    private static final long CONNECT_TIMEOUT = 10 * 1000l;
    private static OkHttpClient okHttpClient;
    private static HttpsUtils mInstance;
    private static int cacheSize = 10 * 1024 * 1024;
    private static Cache cache = new Cache(BaseApplication.getContext().getCacheDir(), cacheSize);

    public HttpsUtils(OkHttpClient okHttpClient) {
        if (okHttpClient == null) {
            this.okHttpClient = new OkHttpClient.Builder().cache(cache).
                    connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS).build();
        } else {
            this.okHttpClient = okHttpClient;
        }
    }


    public static HttpsUtils getInstance() {
        return initHttpsUtils(null);
    }

    private static HttpsUtils initHttpsUtils(OkHttpClient okHttpClient) {
        /*正常的单例写法*/
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
    public static void get(String url, RequestParams params, BaseCallBack callBack) {

        url = params.append2Url(url);
        Request request = new Request.Builder().url(url).get().build();
        if (callBack == null) {
            callBack = BaseCallBack.CALLBACK_DEFAULT;
        }
        BaseCallBack finalCallBack = callBack;
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                finalCallBack.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (finalCallBack.type == String.class) {
                    finalCallBack.onResponse(response.body().string());
                } else {
                    try {
                        Object o = parseResponse(response.body().string(), finalCallBack.type);
                        finalCallBack.onResponse(o);
                    } catch (Exception e) {
                        e.printStackTrace();
                        finalCallBack.onError(e);
                    }

                }
            }
        });
    }

    private static Object parseResponse(String response, Type type) throws Exception {
        Gson gson = new Gson();
        Object o = gson.fromJson(response, type);
        return o;
    }


}
