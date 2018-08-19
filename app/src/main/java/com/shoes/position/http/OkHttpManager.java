package com.shoes.position.http;


import android.app.Activity;
import android.content.Context;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;

import com.shoes.position.utils.GsonUtils;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 在这个类中提供了网络去求的访问方式；
 * <p>post和get两种；
 * <p>
 * <p>
 */

public class OkHttpManager {
    private static OkHttpClient mOkHttpClient = null;

    public static void build() {
        if (null == mOkHttpClient) {
            mOkHttpClient = new OkHttpClient();
            mOkHttpClient.networkInterceptors().add(new LoggingInterceptor());
            mOkHttpClient.setConnectTimeout(10, java.util.concurrent.TimeUnit.SECONDS);
            mOkHttpClient.setReadTimeout(10, java.util.concurrent.TimeUnit.SECONDS);
            mOkHttpClient.setWriteTimeout(10, java.util.concurrent.TimeUnit.SECONDS);
        }
    }

    /**
     * get请求方式
     **/
    public static void setGetRequest(Context context, String url, String token, HashMap<String, String> params, final OnResultCallbackListener listener) {
        //API 14以下的处理
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        String get_url = url;
        if (params == null) {
            params = new HashMap<>();
        } else {
            //拼接url
            if (params != null && params.size() > 0) {
                int i = 0;
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    if (i++ == 0) {
                        get_url = get_url + "?" + entry.getKey() + "=" + entry.getValue();
                    } else {
                        get_url = get_url + "&" + entry.getKey() + "=" + entry.getValue();
                    }
                }
            }
        }

        //请求体
        Request request;
        if (TextUtils.isEmpty(token)) {
            request = new Request.Builder()
                    .url(get_url)
                    .get()
                    .build();
        } else {
            request = new Request.Builder()
                    .url(get_url)
                    .get()
                    .header("Authorization", token)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();
        }

        if (NetUtils.isOpenNetwork(context)) {
            getNetWorkData(context, request, listener);
        } else {
            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (listener != null) {
                        listener.onOffNet("网络错误，请检查你的网络");
                    }
                }
            });
        }
    }

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * post请求方式
     **/
    public static void setPostRequest(Context context, String url, String token, HashMap<String, String> params, final OnResultCallbackListener listener) {
        //API 14以下的处理
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        if (params == null) {
            params = new HashMap<>();
        }
        RequestBody requestBody = null;
        FormEncodingBuilder builder = new FormEncodingBuilder();
        for (Map.Entry<String, String> map : params.entrySet()) {
            String key = map.getKey().toString();
            String value = null;

            //判断值是否是空的
            if (map.getValue() == null) {
                value = "";
            } else {
                value = map.getValue();
            }

//     把key和value添加到formbody中
            builder.add(key, value);
        }
        requestBody = builder.build();
        Request request;
        if (token == null) {
            request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
        } else {
            request = new Request.Builder()
                    .url(url)
                    .header("Authorization", token)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .post(requestBody)
                    .build();
        }

        if (NetUtils.isOpenNetwork(context)) {
            getNetWorkData(context, request, listener);
        } else {
            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (listener != null) {
                        listener.onOffNet("网络错误，请检查你的网络");
                    }
                }
            });
        }
    }

    /**
     * post带内容请求方式
     **/
    public static void setPostRequest(Context context, String url, String token, String params, final OnVideoResultCallbackListener listener) {
        //API 14以下的处理
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        if (params == null) {
            params = "";
        }
        RequestBody requestBody1 = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"), params);
        Request request;
        if (token == null) {
            request = new Request.Builder()
                    .url(url)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .post(requestBody1)
                    .build();
        } else {
            request = new Request.Builder()
                    .url(url)
                    .header("Authorization", token)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .post(requestBody1)
                    .build();
        }

        if (NetUtils.isOpenNetwork(context)) {
            getNetWorkVideoData(context, request, listener);
        } else {
            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (listener != null) {
                        listener.onOffNet("网络错误，请检查你的网络");
                    }
                }
            });
        }
    }

    public static void setPutRequest(Context context, String url, String token, HashMap<String, String> params, final OnResultCallbackListener listener) {
        //API 14以下的处理
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        if (params == null) {
            params = new HashMap<>();
        }
        RequestBody requestBody = null;
        FormEncodingBuilder builder = new FormEncodingBuilder();
        for (Map.Entry<String, String> map : params.entrySet()) {
            String key = map.getKey().toString();
            String value = null;

//     判断值是否是空的
            if (map.getValue() == null) {
                value = "";
            } else {
                value = map.getValue();
            }

//     把key和value添加到formbody中
            builder.add(key, value);
        }
        requestBody = builder.build();
        String json = GsonUtils.toJson(params);
        RequestBody requestBody1 = RequestBody.create(JSON, json);
        Request request;
        if (token == null) {
            request = new Request.Builder()
                    .url(url)
                    .header("Content-Type", "application/json")
                    .put(requestBody1)
                    .build();
        } else {
            request = new Request.Builder()
                    .url(url)
                    .header("Authorization", token)
                    .addHeader("Content-Type", "application/json")
                    .put(requestBody1)
                    .build();
        }

        if (NetUtils.isOpenNetwork(context)) {
            getNetWorkData(context, request, listener);
        } else {
            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (listener != null) {
                        listener.onOffNet("网络错误，请检查你的网络");
                    }
                }
            });
        }
    }

    //返回结果
    private static void getNetWorkData(final Context context, Request request, final OnResultCallbackListener listener) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Request request, final IOException e) {
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (listener != null) {
                            listener.onError("网络请求数据失败！");
                        }
                    }
                });
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                {
                    final String result = response.body().string();
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                {
                                    if (listener != null) {
                                        listener.onResponse(result);
                                    }
                                }
                            } catch (Exception x) {
                                Log.e("response",x.getMessage());
                                listener.onError("数据解析错误！");
                            }
                        }
                    });
                }
            }
        });
    }

    //返回视频结果
    private static void getNetWorkVideoData(final Context context, Request request, final OnVideoResultCallbackListener listener) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Request request, final IOException e) {
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (listener != null) {
                            listener.onError("网络请求数据失败！");
                        }
                    }
                });
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                {
                    final String result = response.body().string();
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (listener != null) {
                                listener.onResponse(result);
                            }
                        }
                    });
                }
            }
        });
    }

    //返回接口
    public interface OnResultCallbackListener {

        void onOffNet(String s);

        void onError(String e);

        void onResponse(String response);
    }

    //返回xml视频源接口
    public interface  OnVideoResultCallbackListener{
        void onOffNet(String s);

        void onError(String e);

        void onResponse(String response);
    }
}
