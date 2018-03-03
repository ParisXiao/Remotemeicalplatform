package com.gxey.remotemedicalplatform.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

import com.gxey.remotemedicalplatform.R;


/**
 * The Class AndroidUtil.
 */

/**
 * 管理Activity栈
 * Created by lanluo on 2015/9/17.
 */
public class AndroidUtil {



    /**
     * Activity 跳转
     *
     * @param activity
     */
    public static void activity_In(Activity activity) {
        activity.overridePendingTransition(R.anim.push_left_in_a, R.anim.push_right_out_a);
    }


    /**
     * Activity 退出
     *
     * @param activity
     */
    public static void activity_Out(Activity activity) {
        activity.overridePendingTransition(R.anim.push_right_in_a, R.anim.push_left_out_a);
    }


    /**
     * 检测网络是否可用
     *
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

    /**
     * 判断是否是电话号码
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile) {
        /*
        移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		联通：130、131、132、152、155、156、185、186
		电信：133、153、180、189、（1349卫通）
		总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		*/
        String telRegex = "[1][34578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobile)) {
            return false;
        } else {
            return mobile.matches(telRegex);
        }
    }


    /**
     * 拨号
     * @param context      上下文
     * @param mobilenumber 号码
     * @param disabledBack true为销毁启动这次拨号的组件，false为不销毁
     */
    public static void skipToCall(Context context, String mobilenumber, boolean disabledBack) {
        Uri uri = Uri.parse(String.format("tel:%s", mobilenumber));
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        if (disabledBack && context instanceof Activity) {
            ((Activity) context).finish();
        }
    }





    /**
     * 使用Toast输出文本
     * @param context  上下文
     * @param resid    资源标识
     * @param duration
     */
    public static void showToast(Context context, int resid, int duration) {
        showToast(context, context.getString(resid), duration);
    }

    /**
     * 使用Toast输出文本
     *
     * @param context  上下文对象
     * @param msg      文本
     * @param duration
     */
    public static void showToast(Context context, String msg, int duration) {
        Toast toast = Toast.makeText(context, msg, duration);
        toast.show();
    }


    /**
     * 获取PackageInfo
     * @param context 上下文
     * @return PackageInfo
     */
    public static PackageInfo getPackageInfo(Context context) {
        PackageManager manager = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
        } catch (PackageManager.NameNotFoundException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        return info;
    }



    


}
