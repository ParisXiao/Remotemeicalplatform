package com.gxey.remotemedicalplatform.activity.secondactivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bruce.pickerview.popwindow.DatePickerPopWin;
import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.activity.BaseActivity;
import com.gxey.remotemedicalplatform.activity.LoginActivity;
import com.gxey.remotemedicalplatform.adapter.YiZhuAdapter;
import com.gxey.remotemedicalplatform.bean.YiZhuBean;
import com.gxey.remotemedicalplatform.mynetwork.MyHttpHelper;
import com.gxey.remotemedicalplatform.newconfig.UrlConfig;
import com.gxey.remotemedicalplatform.utils.MyStrUtil;
import com.gxey.remotemedicalplatform.utils.ScreenUtils;
import com.gxey.remotemedicalplatform.utils.TimeUtils;
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
 * Created by Administrator on 2018-02-27.
 */

public class ActivityYiZhu extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.toolbar_mid)
    TextView toolbarMid;
    @BindView(toolbar_left_btn)
    ImageButton toolbarLeftBtn;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView_yizhu)
    RecyclerView recyclerViewYizhu;
    @BindView(R.id.emptyLayout_yizhu)
    EmptyLayout emptyLayoutYizhu;
    @BindView(R.id.swipe_yizhu)
    SwipeRefreshLayout swipeYizhu;
    @BindView(R.id.toolbar_right)
    TextView toolbarRight;
    @BindView(R.id.start_time)
    TextView startTime;
    @BindView(R.id.end_time)
    TextView endTime;
    @BindView(R.id.re_time)
    RelativeLayout reTime;
    private YiZhuAdapter adapter;
    private List<YiZhuBean> yiZhuBeen;
    private boolean isShow = false;
    private String begintime = "";
    private String endtime = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_yizhu;
    }

    @Override
    protected void initView() {
        toolbarLeftBtn.setVisibility(View.VISIBLE);
        toolbarLeftBtn.setOnClickListener(this);
        toolbarRight.setVisibility(View.VISIBLE);
        toolbarRight.setText(R.string.shuaixuan);
        toolbarRight.setTextColor(getResources().getColor(R.color.background_green));
        toolbarMid.setText(R.string.yizhu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ScreenUtils.setStatusBarLightMode(this, R.color.black);
        yiZhuBeen = new ArrayList<>();
        initLoad();

        recyclerViewYizhu.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewYizhu.setAdapter(adapter = new YiZhuAdapter(this, yiZhuBeen));
    }

    private void initLoad() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getData(begintime, endtime);
            }
        }, 2000);
        //绑定
        swipeYizhu.post(new Runnable() {
            @Override
            public void run() {
                swipeYizhu.setRefreshing(true);
            }
        });
        emptyLayoutYizhu.showLoading(this);
        emptyLayoutYizhu.bindView(recyclerViewYizhu);
        emptyLayoutYizhu.setOnButtonClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emptyLayoutYizhu.showLoading(ActivityYiZhu.this);
                //重新加载数据
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData(begintime, endtime);
                    }
                }, 2000);
            }
        });
        swipeYizhu.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
        swipeYizhu.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                emptyLayoutYizhu.showLoading(ActivityYiZhu.this);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData(begintime, endtime);
                    }
                }, 2000);

            }
        });
    }

    String msg;

    private void getData(final String begin, final String end) {
        yiZhuBeen.clear();
        Observable.create(new Observable.OnSubscribe<Integer>() {

            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (MyHttpHelper.isConllection(ActivityYiZhu.this)) {
                    String[] key = new String[]{"begtime", "endtime"};
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("begtime", begin);
                    map.put("endtime", end);
                    String result = MyHttpHelper.GetMessage(ActivityYiZhu.this, UrlConfig.SelExhort, key, map);
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
                                        YiZhuBean Bean = new YiZhuBean();
                                        JSONObject temp = (JSONObject) jsonArray.get(i);
                                        Bean.setId(temp.getString("id"));
                                        Bean.setNumber(temp.getString("number"));
                                        Bean.setTermofvalidity(temp.getString("termofvalidity"));
                                        Bean.setThestarttime(temp.getString("thestarttime"));
                                        Bean.setContent(temp.getString("content"));
                                        Bean.setFrequency(temp.getString("frequency"));
                                        Bean.setThepatientid(temp.getString("thepatientid"));
                                        Bean.setDoctorid(temp.getString("doctorid"));
                                        Bean.setOpentoldtime(temp.getString("opentoldtime"));
                                        Bean.setExecutableunit(temp.getString("executableunit"));
                                        Bean.setDoctorname(temp.getString("doctorname"));
                                        Bean.setOrganizename(temp.getString("organizename"));
                                        Bean.setPositionname(temp.getString("positionname"));
                                        Bean.setHeadimg(temp.getString("headimg"));
                                        yiZhuBeen.add(Bean);

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
                        swipeYizhu.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                        emptyLayoutYizhu.showEmpty("暂无数据！");
                        break;
                    case 1:
                        swipeYizhu.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                        emptyLayoutYizhu.showSuccess();
                        break;
                    case 2:
                        swipeYizhu.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                        emptyLayoutYizhu.showError("加载出错！");
                        ToastUtils.s(ActivityYiZhu.this, msg);
                        break;
                    case 3:
                        swipeYizhu.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                        emptyLayoutYizhu.showError("网络无连接！");
                        break;
                    case 4:
                        swipeYizhu.setRefreshing(false);
                        ToastUtils.s(ActivityYiZhu.this, msg);
                        Intent intent = new Intent(ActivityYiZhu.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
        startTime.setText(TimeUtils.ms2DateOnlyDay(System.currentTimeMillis()));
        endTime.setText(TimeUtils.ms2DateOnlyDay(System.currentTimeMillis()));
//        initLoadMoreListener();
        adapter.changeMoreStatus(adapter.NO_LOAD_MORE);

        adapter.setOnItemClickListener(new YiZhuAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, YiZhuAdapter.ViewName viewName, int position) {
                if (viewName == YiZhuAdapter.ViewName.ITEM) {
                    Intent intent = new Intent(ActivityYiZhu.this, ActivityYiZhuDetails.class);
                    intent.putExtra("YiZhu",yiZhuBeen.get(position));
                    startActivity(intent);
                }

            }
        });
        toolbarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShow) {
                    if (MyStrUtil.isEmpty(begintime)){
                        begintime=TimeUtils.ms2DateOnlyDay(System.currentTimeMillis());
                    }
                    if (MyStrUtil.isEmpty(endtime)){
                        endtime=TimeUtils.ms2DateOnlyDay(System.currentTimeMillis());
                    }
                    if (!TimeUtils.isDateOneBigger(begintime, endtime)) {
                        isShow = false;
                        toolbarRight.setText("筛选");
                        reTime.setVisibility(View.GONE);
                        emptyLayoutYizhu.showLoading(ActivityYiZhu.this);

                        //重新加载数据
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getData(begintime, endtime);
                            }
                        }, 2000);
                    } else {
                        ToastUtils.s(ActivityYiZhu.this, "结束时间不能比开始时间早！");
                    }

                } else {
                    isShow = true;
                    toolbarRight.setText("确定");
                    reTime.setVisibility(View.VISIBLE);
                }
            }
        });
        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerPopWin pickerPopWin = new DatePickerPopWin.Builder(ActivityYiZhu.this, new DatePickerPopWin.OnDatePickedListener() {
                    @Override
                    public void onDatePickCompleted(int year, int month, int day, String dateDesc) {
                        begintime = dateDesc;
                        startTime.setText(dateDesc);
                    }
                }).textConfirm("确定") //text of confirm button
                        .textCancel("取消") //text of cancel button
                        .btnTextSize(16) // button text size
                        .viewTextSize(25) // pick view text size
                        .colorCancel(Color.parseColor("#999999")) //color of cancel button
                        .colorConfirm(Color.parseColor("#009900"))//color of confirm button
                        .minYear(1999) //min year in loop
                        .maxYear(2030) // max year in loop
                        .dateChose(TimeUtils.ms2DateOnlyDay(System.currentTimeMillis())) // date chose when init popwindow
                        .build();
                pickerPopWin.showPopWin(ActivityYiZhu.this);
            }
        });
        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerPopWin pickerPopWin = new DatePickerPopWin.Builder(ActivityYiZhu.this, new DatePickerPopWin.OnDatePickedListener() {
                    @Override
                    public void onDatePickCompleted(int year, int month, int day, String dateDesc) {
                        endtime = dateDesc;
                        endTime.setText(dateDesc);
                    }
                }).textConfirm("确定") //text of confirm button
                        .textCancel("取消") //text of cancel button
                        .btnTextSize(16) // button text size
                        .viewTextSize(25) // pick view text size
                        .colorCancel(Color.parseColor("#999999")) //color of cancel button
                        .colorConfirm(Color.parseColor("#009900"))//color of confirm button
                        .minYear(1999) //min year in loop
                        .maxYear(2030) // max year in loop
                        .dateChose(TimeUtils.ms2DateOnlyDay(System.currentTimeMillis())) // date chose when init popwindow
                        .build();
                pickerPopWin.showPopWin(ActivityYiZhu.this);
            }
        });

    }


    private void initLoadMoreListener() {
        adapter.changeMoreStatus(adapter.NO_LOAD_MORE);
        recyclerViewYizhu.setOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                adapter.changeMoreStatus(adapter.PULLUP_LOAD_MORE);
                //判断RecyclerView的状态 是空闲时，同时，是最后一个可见的ITEM时才加载
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {

                    //设置正在加载更多
                    adapter.changeMoreStatus(adapter.LOADING_MORE);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
//                            List<YiZhuBean> yiZhuBeen = new ArrayList<YiZhuBean>();
//                            for (int i = 0; i < 10; i++) {
//                                yiZhuBeen.add(new YiZhuBean("", "肖某某", "主治医生,帅哥医生,资深老中医", "编号：001", "妹啊表达对啊实打实读书读", "2018-03-02"));
//                            }
//                            adapter.AddFooterItem(yiZhuBeen);
                            //设置回到上拉加载更多
                            adapter.changeMoreStatus(adapter.PULLUP_LOAD_MORE);
                            Toast.makeText(ActivityYiZhu.this, "更新了 " + yiZhuBeen.size() + " 条数据", Toast.LENGTH_SHORT).show();
                        }
                    }, 3000);


                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //最后一个可见的ITEM
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
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
