package com.example.search.utils;

import android.app.Dialog;
import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by wxj on 2015-9-20.
 */
public class DialogUtils {
    public static Dialog showProgressDialog(Context context, String content) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .content(content)
                .progress(true, 0);
        Dialog dialog = builder.build();
        dialog.show();
        return dialog;
    }
}
