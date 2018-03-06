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
import com.gxey.remotemedicalplatform.adapter.FenZhenAdapter;
import com.gxey.remotemedicalplatform.bean.FenZhenBean;
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
    private List<FenZhenBean> fenZhenBeen;
    private Handler handler=new Handler();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_fenzhen;
    }

    @Override
    protected void initView() {
        toolbarLeftBtn.setVisibility(View.VISIBLE);
        toolbarLeftBtn.setOnClickListener(this);
        toolbarRight.setVisibility(View.VISIBLE);
        toolbarRight.setText(R.string.shuaixuan);
        toolbarRight.setTextColor(getResources().getColor(R.color.background_green));
        toolbarMid.setText(R.string.fenzhenzhuanzhen);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ScreenUtils.setStatusBarLightMode(this, R.color.black);
        fenZhenBeen=new ArrayList<>();
        loadData();

        recyclerViewFenzhen.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewFenzhen.setAdapter(adapter = new FenZhenAdapter(this,fenZhenBeen));
        //绑定
        emptyLayoutFenzhen.bindView(recyclerViewFenzhen);
        emptyLayoutFenzhen.setOnButtonClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //重新加载数据
                loadData();
            }
        });
        swipeFenzhen.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
        swipeFenzhen.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                swipeFenzhen.setRefreshing(false);

            }
        });
    }

    @Override
    protected void initData() {
        initLoadMoreListener();
        adapter.setOnItemClickListener(new FenZhenAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, FenZhenAdapter.ViewName viewName, int position) {

            }
        });
    }
    private void loadData() {
        //模拟加载数据
        emptyLayoutFenzhen.showLoading("正在加载，请稍后");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                为了防止重复调用
                handler.removeCallbacks(this);
                Random r = new Random();
                int res = r.nextInt(10);

                if (res % 2 == 0) {
                    // 失败
                    emptyLayoutFenzhen.showError("重新加载"); // 显示失败
                } else {
                    // 成功
                    emptyLayoutFenzhen.showSuccess();
                    for (int i = 0; i < 10; i++) {
                        fenZhenBeen.add(new FenZhenBean("大同药房","2018-03-02","仁爱医院","外科","肖某某"));
                    }
                }
                adapter.notifyDataSetChanged();
//                }
            }
        }, 3000);
    }
    private void initLoadMoreListener() {
        adapter.changeMoreStatus(adapter.NO_LOAD_MORE);
        recyclerViewFenzhen.setOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem ;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                adapter.changeMoreStatus(adapter.PULLUP_LOAD_MORE);
                //判断RecyclerView的状态 是空闲时，同时，是最后一个可见的ITEM时才加载
                if(newState==RecyclerView.SCROLL_STATE_IDLE&&lastVisibleItem+1==adapter.getItemCount()){

                    //设置正在加载更多
                    adapter.changeMoreStatus(adapter.LOADING_MORE);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            List<FenZhenBean> fenZhenBeen=new ArrayList<FenZhenBean>();
                            for (int i = 0; i < 10; i++) {
                                fenZhenBeen.add(new FenZhenBean("大同药房","2018-03-02","仁爱医院","外科","肖某某"));
                            }
                            adapter.AddFooterItem(fenZhenBeen);
                            //设置回到上拉加载更多
                            adapter.changeMoreStatus(adapter.PULLUP_LOAD_MORE);
                            Toast.makeText(ActivityFenZhen.this, "更新了 "+fenZhenBeen.size()+" 条数据", Toast.LENGTH_SHORT).show();
                        }
                    }, 3000);


                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //最后一个可见的ITEM
                lastVisibleItem=layoutManager.findLastVisibleItemPosition();
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
