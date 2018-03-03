package com.gxey.remotemedicalplatform.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.model.LocationConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xusongsong on 2016/12/21.
 */

public class FragmentPage3 extends BaseFragment {

    private  WebView wView;
    private  TextView mTVTitle;

    public static FragmentPage3 newInstance(String param1) {
        FragmentPage3 fragment = new FragmentPage3();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentPage3() {

    }
    private Map<String,String> extraHeaders;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment2;
    }

    @Override
    protected void initView(View view) {
        wView = (WebView)view.findViewById(R.id.wv);
        mTVTitle = (TextView)view.findViewById(R.id.tv_title_name);
        //加载网页  加载网址
        String UserGUID="";
        extraHeaders = new HashMap<String, String>();
        if (TextUtils.isEmpty(LocationConfig.getInstance().getUserGUID())) {
            UserGUID="0";
        }else {
            UserGUID=LocationConfig.getInstance().getUserGUID();
        }
        extraHeaders.put("UserGUID", UserGUID);
        extraHeaders.put("Platform", "1");
        extraHeaders.put("Versions","1.0");
        extraHeaders.put("DeviceID",LocationConfig.getInstance().getDeviceId());
        //wView.loadUrl("http://baidu.com",extraHeaders);
        wView.loadUrl("http://wxapi.gxwdyf.com/",extraHeaders);
        wView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                System.out.println("url:"+url);
                if(url!=""){
                    view.loadUrl(url);   //在当前的webview中跳转到新的url

                }
                return true;
            }
        });

        wView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //get the newProgress and refresh progress bar
            }
        });

//        wView.setWebChromeClient(new WebChromeClient(){
//            @Override
//            public void onReceivedTitle(WebView view, String title) {
//                mTVTitle.setText(title);//a textview
//            }
//        });
//
//        ivBreak.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                    getActivity().finish();
//
//            }
//        });
    }

    @Override
    protected void initData() {

    }
    public String getMessageHead(){
        Gson gson = new Gson();
        String msg =  gson.toJson(extraHeaders);
        return msg;
    }



}
