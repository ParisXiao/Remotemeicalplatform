package com.gxey.remotemedicalplatform.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.activity.LoginActivity;
import com.gxey.remotemedicalplatform.adapter.WaiShangShiAdapter;
import com.gxey.remotemedicalplatform.bean.JiWangShiBean;
import com.gxey.remotemedicalplatform.mynetwork.MyHttpHelper;
import com.gxey.remotemedicalplatform.newconfig.UrlConfig;
import com.gxey.remotemedicalplatform.utils.MyStrUtil;
import com.gxey.remotemedicalplatform.utils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018-03-08.
 */

public class FragmentWaiShang extends BaseFragment {
    @BindView(R.id.RecyclerView)
    android.support.v7.widget.RecyclerView recyclerView;
    @BindView(R.id.EmptyLayout)
    com.gxey.remotemedicalplatform.widget.EmptyLayout emptyLayout;
    @BindView(R.id.SwipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;
    private WaiShangShiAdapter adapter;
    private List<JiWangShiBean.TraumaBean> list;



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tiwen;
    }

    @Override
    protected void initView(View view) {
        list = new ArrayList<>();
        initLoad();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter = new WaiShangShiAdapter(getActivity(), list));
    }

    private void initLoad() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        }, 2000);
        //绑定
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
//        emptyLayout.showLoading();
        emptyLayout.bindView(recyclerView);
        emptyLayout.setOnButtonClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emptyLayout.showLoading(getActivity());
                //重新加载数据
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData();
                    }
                }, 2000);
            }
        });
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                emptyLayout.showLoading(getActivity());
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData();
                    }
                }, 2000);

            }
        });
    }
    String msg;

    private void getData() {
        list.clear();
        Observable.create(new Observable.OnSubscribe<Integer>() {

            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (MyHttpHelper.isConllection( getActivity())) {
                    String[] key = new String[]{};
                    Map<String, String> map = new HashMap<String, String>();
                    String result = MyHttpHelper.GetMessage( getActivity(), UrlConfig.SelHistoryOfJW, key, map);
                    if (!MyStrUtil.isEmpty(result)) {
                        JSONObject jsonObject;
                        try {
                            jsonObject = new JSONObject(result);
                            String code = jsonObject.getString("code");
                            msg = jsonObject.getString("desc");
                            if (code.equals("0")) {
//                                成功
                                JSONObject jsonObject1 = new JSONObject(jsonObject.getString("result"));
                                JSONArray jsonArray = new JSONArray(jsonObject1.getString("Trauma"));
                                if (jsonArray.length()>0) {

                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JiWangShiBean.TraumaBean Bean =new JiWangShiBean.TraumaBean();
                                        JSONObject temp = (JSONObject) jsonArray.get(i);
                                        Bean.setId(temp.getString("Id"));
                                        Bean.setNameoftrauma(temp.getString("Nameoftrauma"));
                                        Bean.setTraumaTime(temp.getString("TraumaTime"));
                                        Bean.setDes(temp.getString("Des"));
                                        Bean.setStorageTime(temp.getString("StorageTime"));
                                        Bean.setUserId(temp.getString("UserId"));
                                        Bean.setAddUserId(temp.getString("AddUserId"));
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
                        swipeRefreshLayout.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                        emptyLayout.showEmpty("暂无数据！");
                        break;
                    case 1:
                        swipeRefreshLayout.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                        emptyLayout.showSuccess();
                        break;
                    case 2:
                        swipeRefreshLayout.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                        emptyLayout.showError("加载出错！");
                        ToastUtils.s( getActivity(), msg);
                        break;
                    case 3:
                        swipeRefreshLayout.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                        emptyLayout.showError("网络无连接！");
                        break;
                    case 4:
                        swipeRefreshLayout.setRefreshing(false);
                        ToastUtils.s( getActivity(), msg);
                        Intent intent = new Intent( getActivity(), LoginActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
        adapter.changeMoreStatus(adapter.NO_LOAD_MORE);
//        initLoadMoreListener();

        adapter.setOnItemClickListener(new WaiShangShiAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, WaiShangShiAdapter.ViewName viewName, int position) {

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
}
