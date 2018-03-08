package com.gxey.remotemedicalplatform.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2018-03-08.
 */

public class TimeUtils {
    public static String ms2Date(long _ms) {
        Date date = new Date(_ms);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return format.format(date);
    }

    public static String ms2DateOnlyDay(long _ms) {
        Date date = new Date(_ms);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return format.format(date);
    }

    public static long Date2ms(String _data) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = format.parse(_data);
            return date.getTime();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 转成月天
     */
    public static String MyDateMD(String s) {
        Date date = new Date(Date2ms(s));
        SimpleDateFormat format = new SimpleDateFormat("MM-dd", Locale.getDefault());
        return format.format(date);
    }
}
