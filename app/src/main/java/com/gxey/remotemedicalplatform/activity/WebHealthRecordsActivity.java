package com.gxey.remotemedicalplatform.activity;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.common.ApiConstant;
import com.gxey.remotemedicalplatform.model.LocationConfig;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by lanluo on 2017/1/8.
 * web健康
 */

public class WebHealthRecordsActivity extends BaseActivity {
    @BindView(R.id.wv)
    WebView wView;

    @BindView(R.id.tv_title_name)
    TextView mTVTitle;

    @BindView(R.id.back)
    ImageView ivBreak;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_demo;
    }

    private Map<String,String> extraHeaders;
    @Override
    protected void initView() {
        WebSettings wSet = wView.getSettings();
        wSet.setJavaScriptEnabled(true);
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
        wView.loadUrl(ApiConstant.H5BASE+ApiConstant.HEADINFO,extraHeaders);
        wView.addJavascriptInterface(this, "userHead");
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

        wView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                mTVTitle.setText(title);//a textview
            }
        });

        ivBreak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( wView.canGoBack()){
                    wView.goBack();
                }else{
                    finish();
                }
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_BACK && wView.canGoBack()){
            wView.goBack();
            return true;
        }else{
            finish();
        }
        return true;
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
