package com.example.search.network;

import com.example.search.utils.JsonUtils;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by wxj on 2015-9-20.
 */
public class HttpUtils {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String CHARSET_NAME = "UTF-8";

    private static OkHttpClient httpClient;

    static {
        httpClient = new OkHttpClient();
        httpClient.setConnectTimeout(30, TimeUnit.SECONDS);
    }


    public static <T> void get(String url, final IRequestCallBack callBack, final Class<T> clazz) throws IOException {
        Request request = new Request.Builder().url(url).build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                if (callBack != null) {
                    callBack.onFailure(-1, e.getMessage());
                }
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    T t = null;
                    List<T> tList = null;
                    if (json.startsWith("{")) {
                        t = JsonUtils.fromJson(json, clazz);
                    } else if (json.startsWith("[")) {
                        tList = JsonUtils.fromJson2List(json, clazz);
                    }
                    if (callBack != null) {
                        if (t != null) {
                            callBack.onResponse(t);
                        } else {
                            callBack.onResponse(tList);
                        }

                    }
                } else {
                    throw new IOException("Unexpected code " + response);
                }
            }
        });

    }

    public static <T> void post(String url, String params, final Callback callBack) throws IOException {
        RequestBody body = RequestBody.create(JSON, params);
        Request request = new Request.Builder().url(url).post(body).build();
        httpClient.newCall(request).enqueue(callBack);
    }

    /**
     * 这里使用了HttpClinet的API。只是为了方便
     *
     * @param params
     * @return
     */

    public static String formatParams(List<BasicNameValuePair> params) {
        return URLEncodedUtils.format(params, CHARSET_NAME);
    }


    public static String attachHttpGetparams(String url, Map<String, String> params) {
        Set<Map.Entry<String, String>> entries = params.entrySet();
        StringBuilder sBuilder = new StringBuilder(url);
        if (params == null || params.size() == 0) {
            return sBuilder.toString();
        }
        sBuilder.append("?");
        for (Map.Entry<String, String> entry : entries) {
            String key = entry.getKey();
            String value = entry.getValue();
            sBuilder.append(key).append("=").append(value).append("&");
        }
        sBuilder.deleteCharAt(sBuilder.length() - 1);
        return sBuilder.toString();
    }

    /**
     * 为HttpGet 的 url 方便的添加多个name value 参数。
     *
     * @param url
     * @param params
     * @return
     */

    public static String attachHttpGetParams(String url, List<BasicNameValuePair> params) {
        return url + "?" + formatParams(params);
    }

    /**
     * 为HttpGet 的 url 方便的添加1个name value 参数。
     *
     * @param url
     * @param name
     * @param value
     * @return
     */

    public static String attachHttpGetParam(String url, String name, String value) {
        return url + "?" + name + "=" + value;
    }

    public static String parse2Json(Map<String, String> params) {
        Set<Map.Entry<String, String>> entries = params.entrySet();
        StringBuilder sBuilder = new StringBuilder("{");
        for (Map.Entry<String, String> entry : entries) {
            String key = entry.getKey();
            String value = entry.getValue();
            sBuilder.append(key).append(":").append(value).append(",");
        }
        sBuilder.deleteCharAt(sBuilder.length() - 1);
        sBuilder.append("}");
        return sBuilder.toString();
    }
}
