package com.gxey.remotemedicalplatform.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.adapter.HomeRecycleAdapter;
import com.gxey.remotemedicalplatform.javaben.Bannerben;
import com.gxey.remotemedicalplatform.javaben.HomeNewsBen;
import com.gxey.remotemedicalplatform.network.HttpClientHelper;
import com.gxey.remotemedicalplatform.network.HttpSubseiber;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xusongsong on 2016/12/21.
 */

public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.sousuo_sy)
    EditText sousuoSy;
    @BindView(R.id.xiaoxi_sy)
    ImageView xiaoxiSy;
    @BindView(R.id.recyclerView_sy)
    RecyclerView recyclerViewSy;
    @BindView(R.id.lay_refresh)
    SwipeRefreshLayout layRefresh;
    Unbinder unbinder;
    private HomeRecycleAdapter adapter;
    private List<Bannerben> HBlist=null;
    private List<Bannerben> ZClist=null;
    private List<Bannerben> NEWlist=null;
    private List<HomeNewsBen> ZCNewslist=null;
    private List<HomeNewsBen> HealthNewslist=null;

    public static HomeFragment newInstance(String param1) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        layRefresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
        layRefresh.setOnRefreshListener(this);

        recyclerViewSy.setLayoutManager(new GridLayoutManager(recyclerViewSy.getContext(), 8, GridLayoutManager.VERTICAL, false));




    }

    @Override
    protected void initData() {
        layRefresh.post(new Runnable() {
            @Override
            public void run() {
                layRefresh.setRefreshing(true);
            }
        });
        getData();


    }



    private void getData() {

        activity.showLoadDialog();
        HBlist = new ArrayList<Bannerben>();
        ZClist = new ArrayList<Bannerben>();
        NEWlist = new ArrayList<Bannerben>();
        String homebannerType = "0";

        HttpClientHelper.getInstance().homebanner(homebannerType, new HttpSubseiber.ResponseHandler<List<Bannerben>>() {
            @Override
            public void onSucceed(List<Bannerben> data) {
                HomeFragment.this.HBlist=data;
                String ZCbannerType = "1";

                HttpClientHelper.getInstance().homebanner(ZCbannerType, new HttpSubseiber.ResponseHandler<List<Bannerben>>() {
                    @Override
                    public void onSucceed(List<Bannerben> data) {
                        HomeFragment.this. ZClist = data;

                        String bannerType = "2";

                        HttpClientHelper.getInstance().homebanner(bannerType, new HttpSubseiber.ResponseHandler<List<Bannerben>>() {
                            @Override
                            public void onSucceed(List<Bannerben> data) {

                                HomeFragment.this.NEWlist=data;
                                getZCNews("1");


                            }

                            @Override
                            public void onFail(String msg) {
                                activity.dismisDialog();
                                layRefresh.setRefreshing(false);

                            }
                        });
                    }

                    @Override
                    public void onFail(String msg) {
                        activity.dismisDialog();
                        layRefresh.setRefreshing(false);

                    }
                });
            }

            @Override
            public void onFail(String msg) {
                activity.dismisDialog();
                layRefresh.setRefreshing(false);

            }
        });


    }
    private void getZCNews(String PageIndex){
        ZCNewslist=new ArrayList<>();
        String ZCType = "1";
        String ZCPageSize = "5";
        HttpClientHelper.getInstance().GetNews(ZCType, PageIndex, ZCPageSize, new HttpSubseiber.ResponseHandler<List<HomeNewsBen>>() {
            @Override
            public void onSucceed(List<HomeNewsBen> data) {


                HomeFragment.this.ZCNewslist=data;

                getHealthNews("1");
            }


            @Override
            public void onFail(String msg) {
                activity.dismisDialog();
                layRefresh.setRefreshing(false);
            }
        });
    }
    private void getHealthNews(String PageIndex){
        HealthNewslist=new ArrayList<>();
        String Type = "2";
        String PageSize = "10";


        HttpClientHelper.getInstance().GetNews(Type, PageIndex, PageSize, new HttpSubseiber.ResponseHandler<List<HomeNewsBen>>() {
            @Override
            public void onSucceed(List<HomeNewsBen> data) {

                HomeFragment.this.HealthNewslist=data;
                recyclerViewSy.setAdapter(adapter = new HomeRecycleAdapter(getActivity(),HBlist,ZClist,NEWlist,ZCNewslist,HealthNewslist));
                adapter.setType1ItemClickListener(new HomeRecycleAdapter.Type1ItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(getActivity(), "点击了" + position, Toast.LENGTH_SHORT).show();
                    }
                });
                activity.dismisDialog();
                adapter.notifyDataSetChanged();
                layRefresh.setRefreshing(false);
            }


            @Override
            public void onFail(String msg) {
                activity.dismisDialog();
                layRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRefresh() {
        getData();
    }
}
