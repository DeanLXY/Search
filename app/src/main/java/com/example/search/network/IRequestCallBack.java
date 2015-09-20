package com.example.search.network;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by wxj on 2015-9-20.
 */
public interface IRequestCallBack {

    /**
     * 请求失败
     *
     * @param
     * @param
     */
    void onFailure(int requestCode, String message);

    /**
     * 请求成功
     */
    void onResponse(Object t);
}
