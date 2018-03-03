package com.gxey.remotemedicalplatform.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gxey.remotemedicalplatform.activity.BaseActivity;
import com.gxey.remotemedicalplatform.model.LocationConfig;
import com.gxey.remotemedicalplatform.network.HttpClientHelper;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by simple on 16/12/16.
 */

public abstract class BaseFragment extends android.app.Fragment{
    public LocationConfig mConfig;
    public HttpClientHelper mHttpHelper;
    Unbinder unbinder;
    BaseActivity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        activity = (BaseActivity) getActivity();
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        mConfig = LocationConfig.getInstance();
        mHttpHelper = HttpClientHelper.getInstance();
        initView(view);
        initData();
        return view;
    }



    /**
     * 初始化layouy
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化布局
     */
    protected abstract void initView(View view);


    /**
     * 参数设置
     */
    protected abstract void initData();
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
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
