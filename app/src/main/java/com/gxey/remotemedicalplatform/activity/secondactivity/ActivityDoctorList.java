package com.gxey.remotemedicalplatform.activity.secondactivity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.activity.BaseActivity;
import com.gxey.remotemedicalplatform.adapter.DoctorAdapter;
import com.gxey.remotemedicalplatform.bean.DoctorBean;
import com.gxey.remotemedicalplatform.utils.ScreenUtils;
import com.gxey.remotemedicalplatform.widget.EmptyLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.gxey.remotemedicalplatform.R.id.toolbar_left_btn;

/**
 * Created by Administrator on 2018-03-02.
 */

public class ActivityDoctorList extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.toolbar_mid)
    TextView toolbarMid;
    @BindView(toolbar_left_btn)
    ImageButton toolbarLeftBtn;
    @BindView(R.id.toolbar_right)
    TextView toolbarRight;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.sousuo_doctor)
    EditText sousuoDoctor;
    @BindView(R.id.text_all)
    TextView textAll;
    @BindView(R.id.text_online)
    TextView textOnline;
    @BindView(R.id.text_outline)
    TextView textOutline;
    @BindView(R.id.recyclerView_doctor)
    RecyclerView recyclerViewDoctor;
    @BindView(R.id.lay_refresh_doctor)
    SwipeRefreshLayout layRefreshDoctor;
    @BindView(R.id.emptyLayout_doctor)
    EmptyLayout emptyLayoutDoctor;
    private DoctorAdapter adapter;
    private List<DoctorBean> doctorBeen;
    private Handler handler=new Handler();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_doctorlist;
    }

    @Override
    protected void initView() {
        toolbarLeftBtn.setVisibility(View.VISIBLE);
        toolbarLeftBtn.setOnClickListener(this);
        toolbarRight.setVisibility(View.VISIBLE);
        toolbarRight.setText(R.string.shuaixuan);
        toolbarRight.setTextColor(getResources().getColor(R.color.background_green));
        toolbarMid.setText(R.string.yisheng);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ScreenUtils.setStatusBarLightMode(this,R.color.black);
        doctorBeen=new ArrayList<>();
        loadData();

        recyclerViewDoctor.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewDoctor.setAdapter(adapter = new DoctorAdapter(this,doctorBeen));
        //绑定
        emptyLayoutDoctor.bindView(recyclerViewDoctor);
        emptyLayoutDoctor.setOnButtonClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //重新加载数据
                loadData();
            }
        });
        layRefreshDoctor.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
        layRefreshDoctor.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                layRefreshDoctor.setRefreshing(false);

            }
        });
    }

    @Override
    protected void initData() {
        initLoadMoreListener();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    private void loadData() {
        //模拟加载数据
        emptyLayoutDoctor.showLoading("正在加载，请稍后");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                为了防止重复调用
                handler.removeCallbacks(this);
                Random r = new Random();
                int res = r.nextInt(10);

                if (res % 2 == 0) {
                    // 失败
                    emptyLayoutDoctor.showError("加载失败，点击重新加载"); // 显示失败
                } else {
                    // 成功
                    emptyLayoutDoctor.showSuccess();
                    for (int i = 0; i < 10; i++) {
                        doctorBeen.add(new DoctorBean("","肖某某","主治医生,帅哥医生,资深老中医","科室：外科","妹啊表达对啊实打实读书读","10"));
                    }
                    }
                    adapter.notifyDataSetChanged();
//                }
            }
        }, 3000);
    }
    private void initLoadMoreListener() {
        adapter.changeMoreStatus(adapter.NO_LOAD_MORE);
        recyclerViewDoctor.setOnScrollListener(new RecyclerView.OnScrollListener() {
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
                            List<DoctorBean> doctorBeen=new ArrayList<DoctorBean>();
                            for (int i = 0; i < 10; i++) {
                                doctorBeen.add(new DoctorBean("","肖某某","主治医生,帅哥医生,资深老中医","科室：外科","妹啊表达对啊实打实读书读","10"));
                            }
                            adapter.AddFooterItem(doctorBeen);
                            //设置回到上拉加载更多
                            adapter.changeMoreStatus(adapter.PULLUP_LOAD_MORE);
                            Toast.makeText(ActivityDoctorList.this, "更新了 "+doctorBeen.size()+" 条数据", Toast.LENGTH_SHORT).show();
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
    public void onClick(View v) {
        switch (v.getId()) {
            case toolbar_left_btn:
                finish();
                break;
        }
    }
}
