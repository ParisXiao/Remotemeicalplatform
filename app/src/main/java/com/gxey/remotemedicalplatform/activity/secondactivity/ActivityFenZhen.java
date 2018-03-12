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
import android.widget.Toast;

import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.activity.BaseActivity;
import com.gxey.remotemedicalplatform.activity.LoginActivity;
import com.gxey.remotemedicalplatform.adapter.FenZhenAdapter;
import com.gxey.remotemedicalplatform.bean.FenZhenBean;
import com.gxey.remotemedicalplatform.bean.YiZhuBean;
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
import java.util.Random;

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

public class ActivityFenZhen extends BaseActivity implements View.OnClickListener {
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
    private FenZhenAdapter adapter;
    private List<FenZhenBean> list;
    private Handler handler=new Handler();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_fenzhen;
    }

    @Override
    protected void initView() {
        toolbarLeftBtn.setVisibility(View.VISIBLE);
        toolbarLeftBtn.setOnClickListener(this);
        toolbarMid.setText(R.string.fenzhenzhuanzhen);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ScreenUtils.setStatusBarLightMode(this, R.color.black);
        list=new ArrayList<>();
        initLoad();
        recyclerViewFenzhen.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewFenzhen.setAdapter(adapter = new FenZhenAdapter(this,list));
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
                emptyLayoutFenzhen.showLoading(ActivityFenZhen.this);
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
                emptyLayoutFenzhen.showLoading(ActivityFenZhen.this);
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
                if (MyHttpHelper.isConllection(ActivityFenZhen.this)) {
                    String[] key = new String[]{};
                    Map<String, String> map = new HashMap<String, String>();
                    String result = MyHttpHelper.GetMessage(ActivityFenZhen.this, UrlConfig.SelApply, key, map);
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
                                        FenZhenBean Bean = new FenZhenBean();
                                        JSONObject temp = (JSONObject) jsonArray.get(i);
                                        Bean.setApplyFor(temp.getString("ApplyFor"));
                                        Bean.setAuditPerson(temp.getString("AuditPerson"));
                                        Bean.setAuditState(temp.getString("AuditState"));
                                        Bean.setAuditTime(temp.getString("AuditTime"));
                                        Bean.setClinicTime(temp.getString("ClinicTime"));
                                        Bean.setDoctor(temp.getString("Doctor"));
                                        Bean.setOffice(temp.getString("Office"));
                                        Bean.setOpinion(temp.getString("Opinion"));
                                        Bean.setReadState(temp.getString("ReadState"));
                                        Bean.setRegisterTime(temp.getString("RegisterTime"));
                                        Bean.setRegiStrant(temp.getString("RegiStrant"));
                                        Bean.setRemark(temp.getString("Remark"));
                                        Bean.setRollout(temp.getString("Rollout"));
                                        Bean.setRuleId(temp.getString("RuleId"));
                                        Bean.setServiceCharge(temp.getString("ServiceCharge"));
                                        Bean.setSickness(temp.getString("Sickness"));
                                        Bean.setTahospital(temp.getString("Tahospital"));
                                        Bean.setUserId(temp.getString("UserId"));
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
                        ToastUtils.s(ActivityFenZhen.this, msg);
                        break;
                    case 3:
                        swipeFenzhen.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                        emptyLayoutFenzhen.showError("网络无连接！");
                        break;
                    case 4:
                        swipeFenzhen.setRefreshing(false);
                        ToastUtils.s(ActivityFenZhen.this, msg);
                        Intent intent = new Intent(ActivityFenZhen.this, LoginActivity.class);
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
        adapter.setOnItemClickListener(new FenZhenAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, FenZhenAdapter.ViewName viewName, int position) {
                if (viewName== FenZhenAdapter.ViewName.ITEM){
                    Intent intent=new Intent(ActivityFenZhen.this,ActivityFZDetails.class);
                    intent.putExtra("FenZhen",list.get(position));
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
