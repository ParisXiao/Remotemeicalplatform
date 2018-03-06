package com.gxey.remotemedicalplatform.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.adapter.HomeRecycleAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.gxey.remotemedicalplatform.utils.KeyboardUtil.hideInputMethod;

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
        sousuoSy.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                Log.d("Edit", "" + actionId + "," + event);
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_SEARCH)) {
                    String search = sousuoSy.getText().toString();
                    if (!TextUtils.isEmpty(search)) {

                        hideInputMethod(getActivity(), v);
                    }
                    //do something;
                    return true;
                }
                return false;
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
