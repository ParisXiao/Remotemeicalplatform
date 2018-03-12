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
import com.gxey.remotemedicalplatform.adapter.XGYZdapter;
import com.gxey.remotemedicalplatform.adapter.XGYZdapter;
import com.gxey.remotemedicalplatform.bean.XiangGuanYZBean;
import com.gxey.remotemedicalplatform.bean.XiangGuanYZBean;
import com.gxey.remotemedicalplatform.mynetwork.MyHttpHelper;
import com.gxey.remotemedicalplatform.newconfig.UrlConfig;
import com.gxey.remotemedicalplatform.utils.MyStrUtil;
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
 * Created by Administrator on 2018/3/2 0002.
 */

public class ActivityXGYZ extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.toolbar_mid)
    TextView toolbarMid;
    @BindView(toolbar_left_btn)
    ImageButton toolbarLeftBtn;
    @BindView(R.id.toolbar_right)
    TextView toolbarRight;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView_fenzhen)
    RecyclerView recyclerViewFenzhen;
    @BindView(R.id.emptyLayout_fenzhen)
    EmptyLayout emptyLayoutFenzhen;
    @BindView(R.id.swipe_fenzhen)
    SwipeRefreshLayout swipeFenzhen;
    private XGYZdapter adapter;
    private List<XiangGuanYZBean> list;
    private Handler handler=new Handler();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_fenzhen;
    }

    @Override
    protected void initView() {
        toolbarLeftBtn.setVisibility(View.VISIBLE);
        toolbarLeftBtn.setOnClickListener(this);
        toolbarMid.setText("相关医院医嘱");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ScreenUtils.setStatusBarLightMode(this, R.color.black);
        list=new ArrayList<>();
        initLoad();
        recyclerViewFenzhen.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewFenzhen.setAdapter(adapter = new XGYZdapter(this,list));
        //绑定
    }
    private void initLoad() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        }, 1000);
        //绑定
        swipeFenzhen.post(new Runnable() {
            @Override
            public void run() {
                swipeFenzhen.setRefreshing(true);
            }
        });
        emptyLayoutFenzhen.showLoading(this);
        emptyLayoutFenzhen.bindView(recyclerViewFenzhen);
        emptyLayoutFenzhen.setOnButtonClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emptyLayoutFenzhen.showLoading(ActivityXGYZ.this);
                //重新加载数据
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData();
                    }
                }, 1000);
            }
        });
        swipeFenzhen.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
        swipeFenzhen.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                emptyLayoutFenzhen.showLoading(ActivityXGYZ.this);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData();
                    }
                }, 1000);

            }
        });
    }

    String msg;

    private void getData() {

        Observable.create(new Observable.OnSubscribe<Integer>() {

            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (MyHttpHelper.isConllection(ActivityXGYZ.this)) {
                    String[] key = new String[]{"type"};
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("type","1");
                    String result = MyHttpHelper.GetMessage(ActivityXGYZ.this, UrlConfig.SelCorrelationHospitalExhort, key, map);
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
                                        XiangGuanYZBean Bean = new XiangGuanYZBean();
                                        JSONObject temp = (JSONObject) jsonArray.get(i);
                                        Bean.setID(temp.getString("ID"));
                                        Bean.setCaseNumber(temp.getString("CaseNumber"));
                                        Bean.setThePatientId(temp.getString("ThePatientId"));
                                        Bean.setDoctorId(temp.getString("DoctorId"));
                                        Bean.setMainSuit(temp.getString("MainSuit"));
                                        Bean.setHistoryOfPresentIllness(temp.getString("HistoryOfPresentIllness"));
                                        Bean.setHistoryOfPastIllness(temp.getString("HistoryOfPastIllness"));
                                        Bean.setHandlingSuggestion(temp.getString("HandlingSuggestion"));
                                        Bean.setPersonalHistory(temp.getString("PersonalHistory"));
                                        Bean.setFHx(temp.getString("FHx"));
                                        Bean.setPhysicalExamination(temp.getString("PhysicalExamination"));
                                        Bean.setDiagnosis(temp.getString("Diagnosis"));
//                                        Bean.setPeriod(temp.getInt("Period"));
                                        Bean.setSpecial(temp.getString("Special"));
                                        Bean.setRemarks(temp.getString("Remarks"));
                                        Bean.setBoardingTime(temp.getString("BoardingTime"));
                                        Bean.setAdministrativeOrTechnicalOfficesid(temp.getString("AdministrativeOrTechnicalOfficesid"));
                                        list.add(Bean);

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
                        swipeFenzhen.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                        emptyLayoutFenzhen.showEmpty("暂无数据！");
                        break;
                    case 1:
                        swipeFenzhen.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                        emptyLayoutFenzhen.showSuccess();
                        break;
                    case 2:
                        swipeFenzhen.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                        emptyLayoutFenzhen.showError("加载出错！");
                        ToastUtils.s(ActivityXGYZ.this, msg);
                        break;
                    case 3:
                        swipeFenzhen.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                        emptyLayoutFenzhen.showError("网络无连接！");
                        break;
                    case 4:
                        swipeFenzhen.setRefreshing(false);
                        ToastUtils.s(ActivityXGYZ.this, msg);
                        Intent intent = new Intent(ActivityXGYZ.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
//        initLoadMoreListener();
        adapter.changeMoreStatus(adapter.NO_LOAD_MORE);
        adapter.setOnItemClickListener(new XGYZdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, XGYZdapter.ViewName viewName, int position) {
                if (viewName== XGYZdapter.ViewName.ITEM){
                    Intent intent=new Intent(ActivityXGYZ.this,ActivityXGYZDetails.class);
                    intent.putExtra("XGYZ",list.get(position));
                    startActivity(intent);

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
