package com.gxey.remotemedicalplatform.activity.secondactivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.activity.BaseActivity;
import com.gxey.remotemedicalplatform.activity.LoginActivity;
import com.gxey.remotemedicalplatform.adapter.XGDetailsAdapter;
import com.gxey.remotemedicalplatform.bean.XiangGuanYZBean;
import com.gxey.remotemedicalplatform.mynetwork.MyHttpHelper;
import com.gxey.remotemedicalplatform.newconfig.UrlConfig;
import com.gxey.remotemedicalplatform.newconfig.UserConfig;
import com.gxey.remotemedicalplatform.utils.MyStrUtil;
import com.gxey.remotemedicalplatform.utils.PreferenceUtils;
import com.gxey.remotemedicalplatform.utils.ScreenUtils;
import com.gxey.remotemedicalplatform.utils.ToastUtils;
import com.gxey.remotemedicalplatform.widget.EmptyLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.gxey.remotemedicalplatform.R.id.toolbar_left_btn;

/**
 * Created by Administrator on 2018-03-03.
 */

public class ActivityXGYZDetails extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.toolbar_mid)
    TextView toolbarMid;
    @BindView(toolbar_left_btn)
    ImageButton toolbarLeftBtn;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.emptyLayout)
    EmptyLayout emptyLayout;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.xg_name)
    TextView xgName;
    @BindView(R.id.xg_yiyuan)
    TextView xgYiyuan;
    @BindView(R.id.xg_keshi)
    TextView xgKeshi;
    @BindView(R.id.xg_beizhu)
    TextView xgBeizhu;
    @BindView(R.id.xg_djsj)
    TextView xgDjsj;

    private XiangGuanYZBean dianZiBLBean = new XiangGuanYZBean();
    private List<String> urls = null;
    private XGDetailsAdapter xgyZdapter = null;
    private String id;

    @Override
    protected int getLayoutId() {
        return R.layout.activityxgdetails;
    }

    @Override
    protected void initView() {
        toolbarLeftBtn.setVisibility(View.VISIBLE);
        toolbarLeftBtn.setOnClickListener(this);
        toolbarMid.setText("相关详情");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ScreenUtils.setStatusBarLightMode(this, R.color.black);
    }

    @Override
    protected void initData() {

        dianZiBLBean = (XiangGuanYZBean) getIntent().getSerializableExtra("XGYZ");
        id = dianZiBLBean.getID();
        xgName.setText(dianZiBLBean.getThePatientId());
        xgYiyuan.setText(dianZiBLBean.getHospitalid());
        xgKeshi.setText(dianZiBLBean.getAdministrativeOrTechnicalOfficesid());
        xgBeizhu.setText(dianZiBLBean.getRemarks());
        xgDjsj.setText(dianZiBLBean.getBoardingTime());
        urls = new ArrayList<>();
        initLoad();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(xgyZdapter = new XGDetailsAdapter(this, urls));
        xgyZdapter.changeMoreStatus(xgyZdapter.NO_LOAD_MORE);
    }

    private void initLoad() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getData(id);
            }
        }, 1000);
        //绑定
        swipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                swipeRefresh.setRefreshing(true);
            }
        });
        emptyLayout.showLoading(this);
        emptyLayout.bindView(recyclerView);
        emptyLayout.setOnButtonClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emptyLayout.showLoading(ActivityXGYZDetails.this);
                //重新加载数据
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData(id);
                    }
                }, 1000);
            }
        });
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                emptyLayout.showLoading(ActivityXGYZDetails.this);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData(id);
                    }
                }, 1000);

            }
        });
    }

    String msg;

    private void getData(final String id) {
        urls.clear();
        Observable.create(new Observable.OnSubscribe<Integer>() {

            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (MyHttpHelper.isConllection(ActivityXGYZDetails.this)) {
                    String[] key = new String[]{"id"};

                    Map<String, String> map = new HashMap<String, String>();
                    map.put("id", id);
                    String result = MyHttpHelper.GetMessage(ActivityXGYZDetails.this, UrlConfig.SelCorrelationHospitalExhort_Img, key, map);
                    if (!MyStrUtil.isEmpty(result)) {
                        JSONObject jsonObject;
                        try {
                            jsonObject = new JSONObject(result);
                            String code = jsonObject.getString("code");
                            msg = jsonObject.getString("desc");
                            if (code.equals("0")) {
//                                成功
                                JSONArray jsonArray = new JSONArray(jsonObject.getString("result"));
                                if (jsonArray.length() > 0) {
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject temp = (JSONObject) jsonArray.get(i);
                                        urls.add(temp.getString("Imgurl"));

                                    }
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
                        swipeRefresh.setRefreshing(false);
                        xgyZdapter.notifyDataSetChanged();
                        emptyLayout.showEmpty("暂无数据！");
                        break;
                    case 1:
                        swipeRefresh.setRefreshing(false);
                        xgyZdapter.notifyDataSetChanged();
                        emptyLayout.showSuccess();
                        break;
                    case 2:
                        swipeRefresh.setRefreshing(false);
                        xgyZdapter.notifyDataSetChanged();
                        emptyLayout.showError("加载出错！");
                        ToastUtils.s(ActivityXGYZDetails.this, msg);
                        break;
                    case 3:
                        swipeRefresh.setRefreshing(false);
                        xgyZdapter.notifyDataSetChanged();
                        emptyLayout.showError("网络无连接！");
                        break;
                    case 4:
                        swipeRefresh.setRefreshing(false);
                        ToastUtils.s(ActivityXGYZDetails.this, msg);
                        Intent intent = new Intent(ActivityXGYZDetails.this, LoginActivity.class);
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
