package com.gxey.remotemedicalplatform.activity;

import android.content.Intent;
import android.net.http.SslError;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.common.ApiConstant;
import com.gxey.remotemedicalplatform.model.LocationConfig;
import com.gxey.remotemedicalplatform.utils.AndroidUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by lanluo on 2017/1/8.
 * web圈子
 */

public class WebCircleActivity extends BaseActivity {
    @BindView(R.id.wv)
    WebView wView;

    @BindView(R.id.tv_title_name)
    TextView mTVTitle;

    @BindView(R.id.back)
    ImageView ivBreak;

    private String circleId;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_circle;
    }

    private Map<String,String> extraHeaders;
    @BindView(R.id.new_cir)
    LinearLayout mLLCir;
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
        wView.loadUrl(ApiConstant.H5BASE+ApiConstant.Circle,extraHeaders);
        wView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                System.out.println("url:"+url);
                if(!TextUtils.isEmpty(url)){
                    if(url.contains(ApiConstant.CircleId)){
                        String [] strs = url.split(ApiConstant.CircleId);
                        if(strs.length>1){
                            mLLCir.setVisibility(View.VISIBLE);
                            circleId = strs[1];
                        }

                    }else{
                        mLLCir.setVisibility(View.INVISIBLE);
                    }

                    view.loadUrl(url);   //在当前的webview中跳转到新的url

                }
                return true;
            }
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                if (error.getPrimaryError() == SslError.SSL_DATE_INVALID
                        || error.getPrimaryError() == SslError.SSL_EXPIRED
                        || error.getPrimaryError() == SslError.SSL_INVALID
                        || error.getPrimaryError() == SslError.SSL_UNTRUSTED) {
                    handler.proceed();
                } else {
                    handler.cancel();
                }

                super.onReceivedSslError(view, handler, error);
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
                    colse();
                    mLLCir.setVisibility(View.INVISIBLE);
                }else{
                    finish();
                }
            }
        });

        mLLCir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(circleId)){
                    Intent intent= new Intent(WebCircleActivity.this, CircleTopicactivity.class);
                    intent.putExtra("circleId",circleId);
                    startActivity(intent);
                }else{
                    AndroidUtil.showToast(WebCircleActivity.this,"没有获取到Id",0);
                }
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_BACK && wView.canGoBack()){
            colse();
            mLLCir.setVisibility(View.INVISIBLE);

            return true;
        }else{
            finish();
        }
        return true;
    }

    private void colse(){
        if(wView.canGoBack()){
            wView.goBack();
            colse();
        }


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
