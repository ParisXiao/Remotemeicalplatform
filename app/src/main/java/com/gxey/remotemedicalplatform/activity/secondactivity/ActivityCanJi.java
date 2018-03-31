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
import com.gxey.remotemedicalplatform.adapter.CanJiAdapter;
import com.gxey.remotemedicalplatform.adapter.CanJiAdapter;
import com.gxey.remotemedicalplatform.bean.CanJiBean;
import com.gxey.remotemedicalplatform.bean.GuoMingBean;
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

public class ActivityCanJi extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.toolbar_mid)
    TextView toolbarMid;
    @BindView(R.id.toolbar_left_btn)
    ImageButton toolbarLeftBtn;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView_yichuan)
    RecyclerView recyclerViewYichuan;
    @BindView(R.id.emptyLayout_yichuan)
    EmptyLayout emptyLayoutYichuan;
    @BindView(R.id.swipe_yichuan)
    SwipeRefreshLayout swipeYichuan;
    private CanJiAdapter adapter;
    private List<CanJiBean> guoMingBeen;
    private Handler handler = new Handler();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guoming;
    }

    @Override
    protected void initView() {
        toolbarLeftBtn.setVisibility(View.VISIBLE);
        toolbarLeftBtn.setOnClickListener(this);
        toolbarMid.setText("残疾情况");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ScreenUtils.setStatusBarLightMode(ActivityCanJi.this, R.color.black);
        guoMingBeen = new ArrayList<>();
        initLoad();
        recyclerViewYichuan.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewYichuan.setAdapter(adapter = new CanJiAdapter(this, guoMingBeen));

    }
    private void initLoad(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        }, 2000);
        //绑定
        swipeYichuan.post(new Runnable() {
            @Override
            public void run() {
                swipeYichuan.setRefreshing(true);
            }
        });
        emptyLayoutYichuan.showLoading(this);
        emptyLayoutYichuan.bindView(recyclerViewYichuan);
        emptyLayoutYichuan.setOnButtonClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emptyLayoutYichuan.showLoading(ActivityCanJi.this);
                //重新加载数据
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData();
                    }
                }, 2000);
            }
        });
        swipeYichuan.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
        swipeYichuan.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                emptyLayoutYichuan.showLoading(ActivityCanJi.this);
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
        adapter.changeMoreStatus(adapter.NO_LOAD_MORE);
//        initLoadMoreListener();

        adapter.setOnItemClickListener(new CanJiAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, CanJiAdapter.ViewName viewName, int position) {

            }
        });
    }

    String msg;

    private void getData() {
        guoMingBeen.clear();
        Observable.create(new Observable.OnSubscribe<Integer>() {

            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (MyHttpHelper.isConllection(ActivityCanJi.this)) {
                    String[] key = new String[]{};
                    Map<String, String> map = new HashMap<String, String>();
                    String result = MyHttpHelper.GetMessage(ActivityCanJi.this, UrlConfig.SelDisability, key, map);
                    if (!MyStrUtil.isEmpty(result)) {
                        JSONObject jsonObject;
                        try {
                            jsonObject = new JSONObject(result);
                            String code = jsonObject.getString("code");
                            msg = jsonObject.getString("desc");
                            if (code.equals("0")) {
//                                成功
                                JSONArray jsonArray = new JSONArray(jsonObject.getString("result"));
                                if (jsonArray.length()>0) {
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        CanJiBean GuoMingBean =new CanJiBean();
                                        JSONObject temp = (JSONObject) jsonArray.get(i);
                                        GuoMingBean.setId(temp.getString("Id"));
                                        GuoMingBean.setDisability(temp.getString("Disability"));
                                        GuoMingBean.setPutInStorageTime(temp.getString("PutInStorageTime"));
                                        GuoMingBean.setAddUserId(temp.getString("AddUserId"));
                                        GuoMingBean.setUserId(temp.getString("UserId"));
                                        guoMingBeen.add(GuoMingBean);

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
                        swipeYichuan.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                        emptyLayoutYichuan.showEmpty("暂无数据！");
                        break;
                    case 1:
                        swipeYichuan.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                        emptyLayoutYichuan.showSuccess();
                        break;
                    case 2:
                        swipeYichuan.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                        emptyLayoutYichuan.showError("加载出错！");
                        ToastUtils.s(ActivityCanJi.this, msg);
                        break;
                    case 3:
                        swipeYichuan.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                        emptyLayoutYichuan.showError("网络无连接！");
                        break;
                    case 4:
                        swipeYichuan.setRefreshing(false);
                        ToastUtils.s(ActivityCanJi.this, msg);
                        Intent intent = new Intent(ActivityCanJi.this, LoginActivity.class);
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
