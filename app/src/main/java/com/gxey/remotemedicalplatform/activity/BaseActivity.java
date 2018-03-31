package com.gxey.remotemedicalplatform.activity;

import android.graphics.Color;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;

import com.gxey.remotemedicalplatform.model.LocationConfig;
import com.gxey.remotemedicalplatform.network.HttpClientHelper;
import com.gxey.remotemedicalplatform.utils.ActivityStack;

import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by simple on 16/12/16.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public LocationConfig mConfig;
    public HttpClientHelper mHttpHelper;
    protected String mFilePath;
    /**
     * 初始化layout
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化布局
     */
    protected abstract void initView();

    /**
     * 参数设置
     */
    protected abstract void initData();
    private SweetAlertDialog mDialog;

    public void BackActivity(View v){
        finish();
    }

    private void getFilePath(){
        mFilePath = Environment.getExternalStorageDirectory().getPath();
        // 文件名
        mFilePath = mFilePath + "/" + "photo.png";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(this);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mConfig = LocationConfig.getInstance();
        mHttpHelper = HttpClientHelper.getInstance();
        getFilePath();
        initView();
        initData();

    }

    public void showLoadDialog(){
    if(mDialog==null){
        mDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        mDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        mDialog.setTitleText("加载中");
        mDialog.setCancelable(false);
    }
        if(!mDialog.isShowing()){
            mDialog.show();
            mDialog.setCanceledOnTouchOutside(true);
        }


    }

    public void dismisDialog(){
        if(mDialog!=null){
            mDialog.dismiss();
        }

    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    public int getStatusBarHeight() {
        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            return getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }
}
