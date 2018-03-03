package com.gxey.remotemedicalplatform.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by simple on 16/12/16.
 */

public class ToastUtils {

    public static void s(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void l(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
