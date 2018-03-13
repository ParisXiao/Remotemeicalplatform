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
import com.gxey.remotemedicalplatform.activity.WebBannerbenActgvity;
import com.gxey.remotemedicalplatform.adapter.ZCNewsAdapter;
import com.gxey.remotemedicalplatform.adapter.ZCNewsAdapter;
import com.gxey.remotemedicalplatform.bean.JiaZuBean;
import com.gxey.remotemedicalplatform.fragment.HomeFragment;
import com.gxey.remotemedicalplatform.javaben.HomeNewsBen;
import com.gxey.remotemedicalplatform.mynetwork.MyHttpHelper;
import com.gxey.remotemedicalplatform.network.HttpClientHelper;
import com.gxey.remotemedicalplatform.network.HttpSubseiber;
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

public class ActivityZCNews extends BaseActivity implements View.OnClickListener {

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
    private ZCNewsAdapter adapter;
    private List<HomeNewsBen> list;
    private Handler handler = new Handler();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_yichuan;
    }

    @Override
    protected void initView() {
        toolbarLeftBtn.setVisibility(View.VISIBLE);
        toolbarLeftBtn.setOnClickListener(this);
        toolbarMid.setText("政策解读");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ScreenUtils.setStatusBarLightMode(ActivityZCNews.this, R.color.black);
        list = new ArrayList<>();
        initLoad();
        recyclerViewYichuan.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


    }
    private void initLoad(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              getZCNews("");
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
                emptyLayoutYichuan.showLoading(ActivityZCNews.this);
                //重新加载数据
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getZCNews("");
                    }
                }, 2000);
            }
        });
        swipeYichuan.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
        swipeYichuan.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                emptyLayoutYichuan.showLoading(ActivityZCNews.this);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getZCNews("");
                    }
                }, 2000);

            }
        });
    }

    @Override
    protected void initData() {

//        initLoadMoreListener();


    }

    private void getZCNews(String PageIndex) {
        String ZCType = "1";
        String ZCPageSize = "";
        HttpClientHelper.getInstance().GetNews(ZCType, PageIndex, ZCPageSize, new HttpSubseiber.ResponseHandler<List<HomeNewsBen>>() {
            @Override
            public void onSucceed(List<HomeNewsBen> data) {
                emptyLayoutYichuan.showSuccess();
                swipeYichuan.setRefreshing(false);
                list = data;
                recyclerViewYichuan.setAdapter(adapter = new ZCNewsAdapter(ActivityZCNews.this, list));
                adapter.changeMoreStatus(adapter.NO_LOAD_MORE);
                adapter.notifyDataSetChanged();
                adapter.setOnItemClickListener(new ZCNewsAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onClick(View view, ZCNewsAdapter.ViewName viewName, int position) {
                        Intent intent = new Intent(ActivityZCNews.this, WebBannerbenActgvity.class);
                        intent.putExtra("url", list.get(position).getLikeUrl());
                        startActivity(intent);
                    }
                });
            }


            @Override
            public void onFail(String msg) {

                swipeYichuan.setRefreshing(false);
                adapter.notifyDataSetChanged();
                emptyLayoutYichuan.showError("加载出错！");
                ToastUtils.s(ActivityZCNews.this, msg);
            }
        });
    }

    private void initLoadMoreListener() {
        adapter.changeMoreStatus(adapter.NO_LOAD_MORE);
        recyclerViewYichuan.setOnScrollListener(new RecyclerView.OnScrollListener() {
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
//                            List<YiChuanBean> list = new ArrayList<YiChuanBean>();
//                            for (int i = 0; i < 10; i++) {
////                                list.add(new YiChuanBean("神经病", "2018-03-02"));
//                            }
//                            adapter.AddFooterItem(list);
                            //设置回到上拉加载更多
                            adapter.changeMoreStatus(adapter.PULLUP_LOAD_MORE);
//                            Toast.makeText(ActivityZCNews.this, "更新了 " + list.size() + " 条数据", Toast.LENGTH_SHORT).show();
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
