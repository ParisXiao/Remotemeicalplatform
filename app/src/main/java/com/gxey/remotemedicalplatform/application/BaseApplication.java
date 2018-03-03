package com.gxey.remotemedicalplatform.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * @Title Application是用来管理应用程序的全局状态的，比如载入资源文件 在应用程序启动的时候Application会首先创建
 * @Author lanluo
 * @Date 2016-9-14
 */
public abstract class BaseApplication extends Application {

    public static Context applicationContext;

    // 以键值对的形式存储用户名和密码
    public SharedPreferences sharereferences;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
        // 初始化键值对存储
        sharereferences = getSharedPreferences("remotemedicalplatform", MODE_PRIVATE);
    }



}
