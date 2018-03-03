package com.gxey.remotemedicalplatform.fragment;

import android.os.Bundle;
import android.os.Handler;
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
import com.gxey.remotemedicalplatform.utils.KeyboardUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xusongsong on 2016/12/21.
 */

public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{
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

        recyclerViewSy.setLayoutManager(new GridLayoutManager(recyclerViewSy.getContext(),8, GridLayoutManager.VERTICAL, false));

        recyclerViewSy.setAdapter(adapter = new HomeRecycleAdapter(getActivity()));
        adapter.setType1ItemClickListener(new HomeRecycleAdapter.Type1ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(),"点击了"+position,Toast.LENGTH_SHORT).show();
            }
        });





    }


    @Override
    protected void initData() {
        sousuoSy.setFocusable(true);
        sousuoSy.setFocusableInTouchMode(true);
        sousuoSy.requestFocus();//获取焦点 光标出现
        sousuoSy.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {
                    // 获得焦点
                    sousuoSy.setCursorVisible(true);
                    KeyboardUtil.showKeyboard(v);
                } else {
                    // 失去焦点
                    KeyboardUtil.hideKeyboard(v);
                }

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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                layRefresh.setRefreshing(false);
                adapter.notifyDataSetChanged();
            }
        }, 1000);
    }
}
