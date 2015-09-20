package com.example.search.network;

import android.app.Dialog;
import android.content.Context;

import com.example.search.utils.DialogUtils;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Map;

/**
 * Created by wxj on 2015-9-20.
 */
public class HttpManager {


    private final Context context;
    private final IRequestCallBack callBack;
    private final boolean showDialog;
    private Dialog progressDialog;

    public HttpManager(Context context, IRequestCallBack callBack, boolean showDialog) {
        this.context = context;
        this.callBack = callBack;
        this.showDialog = showDialog;
    }

    private void prepareDialog() {
        if (showDialog) {
            progressDialog = DialogUtils.showProgressDialog(this.context, "正在加载");
        }
    }

    private void dismissDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public HttpManager(Context context, IRequestCallBack callBack) {
        this(context, callBack, true);
    }

    public <T> void post(String url, Map<String, String> params, Class<T> clazz) {
        prepareDialog();
        try {
            HttpUtils.post(url, HttpUtils.parse2Json(params), new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    dismissDialog();
                    if (callBack != null) {
                        callBack.onFailure(-1, e.getMessage());
                    }
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    dismissDialog();
                    if (response.isSuccessful()) {
                        response.body().string();
                    } else {
                        throw new IOException("Unexpected code " + response);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> void get(String url, Map<String, String> params, Class<T> clazz) {
        try {
            HttpUtils.get(HttpUtils.attachHttpGetparams(url, params), callBack, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
