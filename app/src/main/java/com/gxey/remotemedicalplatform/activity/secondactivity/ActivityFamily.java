package com.gxey.remotemedicalplatform.activity.secondactivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.activity.BaseActivity;
import com.gxey.remotemedicalplatform.activity.LoginActivity;
import com.gxey.remotemedicalplatform.mynetwork.MyHttpHelper;
import com.gxey.remotemedicalplatform.newconfig.UrlConfig;
import com.gxey.remotemedicalplatform.utils.MyStrUtil;
import com.gxey.remotemedicalplatform.utils.ScreenUtils;
import com.gxey.remotemedicalplatform.utils.ToastUtils;
import com.gxey.remotemedicalplatform.widget.EmptyLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.gxey.remotemedicalplatform.R.id.toolbar_left_btn;

/**
 * Created by Administrator on 2018-03-07.
 */

public class ActivityFamily extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.toolbar_mid)
    TextView toolbarMid;
    @BindView(toolbar_left_btn)
    ImageButton toolbarLeftBtn;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.EmptyLayout_jiazu)
    EmptyLayout EmptyLayoutJiazu;
    @BindView(R.id.linear_jiazi)
    LinearLayout linearJiazi;
    @BindView(R.id.swipe_jiazu)
    SwipeRefreshLayout swipeJiazu;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_family;
    }

    @Override
    protected void initView() {
        toolbarLeftBtn.setVisibility(View.VISIBLE);
        toolbarLeftBtn.setOnClickListener(this);
        toolbarMid.setText(R.string.jiazushi);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ScreenUtils.setStatusBarLightMode(this, R.color.black);

        initLoad();
    }

    private void initLoad() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        }, 2000);
        //绑定
        swipeJiazu.post(new Runnable() {
            @Override
            public void run() {
                swipeJiazu.setRefreshing(true);
            }
        });
        EmptyLayoutJiazu.showLoading();
        EmptyLayoutJiazu.bindView(linearJiazi);
        EmptyLayoutJiazu.setOnButtonClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmptyLayoutJiazu.showLoading();
                //重新加载数据
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData();
                    }
                }, 2000);
            }
        });
        swipeJiazu.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
        swipeJiazu.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                EmptyLayoutJiazu.showLoading();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData();
                    }
                }, 2000);

            }
        });
    }

    @Override
    protected void initData() {

    }

    String msg;

    private void getData() {
        EmptyLayoutJiazu.showLoading();
        Observable.create(new Observable.OnSubscribe<Integer>() {

            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (MyHttpHelper.isConllection(ActivityFamily.this)) {
                    String[] key = new String[]{};
                    Map<String, String> map = new HashMap<String, String>();
                    String result = MyHttpHelper.GetMessage(ActivityFamily.this, UrlConfig.SelTemperature, key, map);
                    if (!MyStrUtil.isEmpty(result)) {
                        JSONObject jsonObject;
                        try {
                            jsonObject = new JSONObject(result);
                            String code = jsonObject.getString("code");
                            msg = jsonObject.getString("desc");
                            if (code.equals("0")) {
//                                成功
                                JSONObject jsonObject2 = new JSONObject(jsonObject.getString("result"));
                                if (!MyStrUtil.isEmpty(jsonObject2)) {
                                    subscriber.onNext(1);
                                } else {
                                    subscriber.onNext(0);
                                }
                            } else if (code.equals("1")) {
                                subscriber.onNext(4);
//                                离线
                            } else {
//                                失败
                                subscriber.onNext(2);
                            }

                        } catch (JSONException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                } else {
                    subscriber.onNext(3);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {

                switch (integer) {
                    case 0:
                        EmptyLayoutJiazu.showEmpty("暂无数据！");
                        swipeJiazu.setRefreshing(false);
                        break;
                    case 1:
                        EmptyLayoutJiazu.showSuccess();
                        swipeJiazu.setRefreshing(false);
                        break;
                    case 2:
                        EmptyLayoutJiazu.showError("加载出错！");
                        swipeJiazu.setRefreshing(false);
                        ToastUtils.s(ActivityFamily.this, msg);
                        break;
                    case 3:
                        EmptyLayoutJiazu.showError("网络无连接！");
                        swipeJiazu.setRefreshing(false);
                        break;
                    case 4:
                        swipeJiazu.setRefreshing(false);
                        ToastUtils.s(ActivityFamily.this, msg);
                        Intent intent = new Intent(ActivityFamily.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case toolbar_left_btn:
                finish();
                break;
        }
    }
}
