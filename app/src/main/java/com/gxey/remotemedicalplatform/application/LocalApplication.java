package com.gxey.remotemedicalplatform.application;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;

import com.gxey.remotemedicalplatform.BuildConfig;
import com.gxey.remotemedicalplatform.model.LocationConfig;
import com.gxey.remotemedicalplatform.utils.PhoneList;
import com.gxey.remotemedicalplatform.utils.SystemUtil;

import org.xutils.x;


/**
 * @title Application是用来管理应用程序的全局状态的，比如载入资源文件 在应用程序启动的时候Application会首先创建
 * @author lanluo
 * @data 16/8/8
 */
public class LocalApplication extends BaseApplication{

    private static LocalApplication sInstance;
    // 当前屏幕的高宽
    public int screenW = 0;
    public int screenH = 0;

    //是否需要rtmp推流
    public boolean isRTMP=false;



    // 单例模式中获取唯一的MyApplication实例
    public static LocalApplication getInstance()
    {
        if (sInstance == null)
        {
            sInstance = new LocalApplication();
        }
        return sInstance;
    }
    //初始化网络

    //初始化数据库

    //初始化存储文件

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        // 得到屏幕的宽度和高度
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenW = dm.widthPixels;
        screenH = dm.heightPixels;

        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);

        TelephonyManager telephonyManager;




        telephonyManager =

                ( TelephonyManager )getSystemService( Context.TELEPHONY_SERVICE );



    /*

      * getDeviceId() function Returns the unique device ID.

     * for example,the IMEI for GSM and the MEID or ESN for CDMA phones.

     */

        LocationConfig.getInstance().setDeviceId(telephonyManager.getDeviceId());
        if (PhoneList.phoneRtmp(SystemUtil.getSystemModel())){
            isRTMP=true;
            Log.d("rtmp","isRTMP"+isRTMP);
        }

    }



}
