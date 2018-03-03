package com.gxey.remotemedicalplatform.activity.secondactivity;

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
import com.gxey.remotemedicalplatform.adapter.HealthBGAdapter;
import com.gxey.remotemedicalplatform.bean.HealthBGBean;
import com.gxey.remotemedicalplatform.utils.ScreenUtils;
import com.gxey.remotemedicalplatform.widget.EmptyLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.gxey.remotemedicalplatform.R.id.toolbar_left_btn;

/**
 * Created by Administrator on 2018/3/2 0002.
 */

public class ActivityHealthBaoGao extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.toolbar_mid)
    TextView toolbarMid;
    @BindView(R.id.toolbar_left_btn)
    ImageButton toolbarLeftBtn;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView_healthbaogao)
    RecyclerView recyclerViewHealthbaogao;
    @BindView(R.id.emptyLayout_healthbaogao)
    EmptyLayout emptyLayoutHealthbaogao;
    @BindView(R.id.swipe_healthbaogao)
    SwipeRefreshLayout swipeHealthbaogao;
    private HealthBGAdapter adapter;
    private List<HealthBGBean> healthBGBeen;
    private Handler handler = new Handler();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_health_baogao;
    }

    @Override
    protected void initView() {
        toolbarLeftBtn.setVisibility(View.VISIBLE);
        toolbarLeftBtn.setOnClickListener(this);
        toolbarMid.setText(R.string.jkbaogaoliebiao);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ScreenUtils.setStatusBarLightMode(this, R.color.black);
        healthBGBeen = new ArrayList<>();
        loadData();

        recyclerViewHealthbaogao.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewHealthbaogao.setAdapter(adapter = new HealthBGAdapter(this, healthBGBeen));
        //绑定
        emptyLayoutHealthbaogao.bindView(recyclerViewHealthbaogao);
        emptyLayoutHealthbaogao.setOnButtonClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //重新加载数据
                loadData();
            }
        });
        swipeHealthbaogao.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
        swipeHealthbaogao.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                swipeHealthbaogao.setRefreshing(false);

            }
        });
    }

    @Override
    protected void initData() {
        initLoadMoreListener();
        adapter.setOnItemClickListener(new HealthBGAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, HealthBGAdapter.ViewName viewName, int position) {

            }
        });
    }

    private void loadData() {
        //模拟加载数据
        emptyLayoutHealthbaogao.showLoading("正在加载，请稍后");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                为了防止重复调用
                handler.removeCallbacks(this);
                Random r = new Random();
                int res = r.nextInt(10);

                if (res % 2 == 0) {
                    // 失败
                    emptyLayoutHealthbaogao.showError("加载失败，点击重新加载"); // 显示失败
                } else {
                    // 成功
                    emptyLayoutHealthbaogao.showSuccess();
                    for (int i = 0; i < 10; i++) {
                        healthBGBeen.add(new HealthBGBean("15454451215", "张三", "肖某某", "重庆大药房", "2018-03-02"));
                    }
                }
                adapter.notifyDataSetChanged();
//                }
            }
        }, 3000);
    }

    private void initLoadMoreListener() {
        adapter.changeMoreStatus(adapter.NO_LOAD_MORE);
        recyclerViewHealthbaogao.setOnScrollListener(new RecyclerView.OnScrollListener() {
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
                            List<HealthBGBean> healthBGBeen = new ArrayList<HealthBGBean>();
                            for (int i = 0; i < 10; i++) {
                                healthBGBeen.add(new HealthBGBean("15454451215", "张三", "肖某某", "重庆大药房", "2018-03-02"));
                            }
                            adapter.AddFooterItem(healthBGBeen);
                            //设置回到上拉加载更多
                            adapter.changeMoreStatus(adapter.PULLUP_LOAD_MORE);
                            Toast.makeText(ActivityHealthBaoGao.this, "更新了 " + healthBGBeen.size() + " 条数据", Toast.LENGTH_SHORT).show();
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
