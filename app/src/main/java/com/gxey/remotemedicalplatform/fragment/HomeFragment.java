package com.gxey.remotemedicalplatform.fragment;

import android.content.Intent;
import android.os.Bundle;
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

import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.activity.ActivityDrugStore;
import com.gxey.remotemedicalplatform.activity.WebBannerbenActgvity;
import com.gxey.remotemedicalplatform.activity.WebMedicationActivity;
import com.gxey.remotemedicalplatform.activity.WebMyDiagnosisActivity;
import com.gxey.remotemedicalplatform.activity.WebSearchActivity;
import com.gxey.remotemedicalplatform.activity.secondactivity.ActivityDZBL;
import com.gxey.remotemedicalplatform.activity.secondactivity.ActivityDoctorList;
import com.gxey.remotemedicalplatform.activity.secondactivity.ActivityHealthBaoGao;
import com.gxey.remotemedicalplatform.activity.secondactivity.ActivityHealthNews;
import com.gxey.remotemedicalplatform.activity.secondactivity.ActivityHeathDianZi;
import com.gxey.remotemedicalplatform.activity.secondactivity.ActivityTiJian;
import com.gxey.remotemedicalplatform.activity.secondactivity.ActivityYiZhu;
import com.gxey.remotemedicalplatform.activity.secondactivity.ActivityZCNews;
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

import static com.gxey.remotemedicalplatform.utils.KeyboardUtil.hideInputMethod;

/**
 * Created by xusongsong on 2016/12/21.
 */

public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.sousuo_sy)
    EditText sousuoSy;
    //    @BindView(R.id.xiaoxi_sy)
//    ImageView xiaoxiSy;
    @BindView(R.id.recyclerView_sy)
    RecyclerView recyclerViewSy;
    @BindView(R.id.lay_refresh)
    SwipeRefreshLayout layRefresh;
    Unbinder unbinder;
    @BindView(R.id.sousuoaction_sy)
    ImageView sousuoactionSy;
    private HomeRecycleAdapter adapter;
    private List<Bannerben> HBlist = null;
    private List<Bannerben> ZClist = null;
    private List<Bannerben> NEWlist = null;
    private List<HomeNewsBen> ZCNewslist = null;
    private List<HomeNewsBen> HealthNewslist = null;

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
        if (layRefresh != null) {
            layRefresh.post(new Runnable() {
                @Override
                public void run() {
                    getData();
                    layRefresh.setRefreshing(true);
                }
            });
        }
        getData();


    }

    @Override
    protected void initData() {


    }


    private void getData() {
        sousuoactionSy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = sousuoSy.getText().toString();
                if (!TextUtils.isEmpty(search)) {
                    Intent intent = new Intent(getActivity(), WebSearchActivity.class);
                    intent.putExtra("search", search + "");
                    startActivity(intent);
                    hideInputMethod(getActivity(), v);
                }
            }
        });
        HBlist = new ArrayList<Bannerben>();
        ZClist = new ArrayList<Bannerben>();
        NEWlist = new ArrayList<Bannerben>();
        String homebannerType = "0";

        HttpClientHelper.getInstance().homebanner(homebannerType, new HttpSubseiber.ResponseHandler<List<Bannerben>>() {
            @Override
            public void onSucceed(List<Bannerben> data) {
                HomeFragment.this.HBlist = data;
                String ZCbannerType = "1";

                HttpClientHelper.getInstance().homebanner(ZCbannerType, new HttpSubseiber.ResponseHandler<List<Bannerben>>() {
                    @Override
                    public void onSucceed(List<Bannerben> data) {
                        HomeFragment.this.ZClist = data;

                        String bannerType = "2";

                        HttpClientHelper.getInstance().homebanner(bannerType, new HttpSubseiber.ResponseHandler<List<Bannerben>>() {
                            @Override
                            public void onSucceed(List<Bannerben> data) {

                                HomeFragment.this.NEWlist = data;
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

        sousuoSy.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                Log.d("Edit", "" + actionId + "," + event);
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_SEARCH)) {
                    String search = sousuoSy.getText().toString();
                    if (!TextUtils.isEmpty(search)) {
                        Intent intent = new Intent(getActivity(), WebSearchActivity.class);
                        intent.putExtra("search", search + "");
                        startActivity(intent);
                        hideInputMethod(getActivity(), v);
                    }
                    //do something;
                    return true;
                }
                return false;
            }
        });
    }

    private void getZCNews(String PageIndex) {
        ZCNewslist = new ArrayList<>();
        String ZCType = "1";
        String ZCPageSize = "5";
        HttpClientHelper.getInstance().GetNews(ZCType, PageIndex, ZCPageSize, new HttpSubseiber.ResponseHandler<List<HomeNewsBen>>() {
            @Override
            public void onSucceed(List<HomeNewsBen> data) {


                HomeFragment.this.ZCNewslist = data;

                getHealthNews("1");
            }


            @Override
            public void onFail(String msg) {
                activity.dismisDialog();
                layRefresh.setRefreshing(false);
            }
        });
    }

    private void getHealthNews(String PageIndex) {
        HealthNewslist = new ArrayList<>();
        String Type = "2";
        String PageSize = "10";


        HttpClientHelper.getInstance().GetNews(Type, PageIndex, PageSize, new HttpSubseiber.ResponseHandler<List<HomeNewsBen>>() {
            @Override
            public void onSucceed(List<HomeNewsBen> data) {

                HomeFragment.this.HealthNewslist = data;
                recyclerViewSy.setAdapter(adapter = new HomeRecycleAdapter(getActivity(), HBlist, ZClist, NEWlist, ZCNewslist, HealthNewslist));
                adapter.setType1ItemClickListener(new HomeRecycleAdapter.Type1ItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (position == 1) {
                            Intent intent = new Intent(getActivity(), ActivityDoctorList.class);
                            startActivity(intent);
                        } else if (position == 2) {
                            Intent intent = new Intent(getActivity(), ActivityHealthBaoGao.class);
                            startActivity(intent);
                        } else if (position == 3) {
                            Intent intent = new Intent(getActivity(), ActivityHeathDianZi.class);
                            startActivity(intent);
                        } else if (position == 4) {
                            Intent intent = new Intent(getActivity(), WebMedicationActivity.class);
                            startActivity(intent);
                        } else if (position == 5) {
                            Intent intent = new Intent(getActivity(), WebMyDiagnosisActivity.class);
                            startActivity(intent);
                        } else if (position == 6) {
                            Intent intent = new Intent(getActivity(), ActivityYiZhu.class);
                            startActivity(intent);
                        } else if (position == 7) {
                            Intent intent = new Intent(getActivity(), ActivityTiJian.class);
                            startActivity(intent);
                        } else if (position == 8) {
                            Intent intent = new Intent(getActivity(), ActivityDrugStore.class);
                            startActivity(intent);

                        }
                    }
                });
                adapter.setType2ItemClickListener(new HomeRecycleAdapter.Type2ItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), ActivityDrugStore.class);
                        startActivity(intent);
                    }
                });
                adapter.setType3ItemClickListener(new HomeRecycleAdapter.Type3ItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (position == 12) {
                            Intent intent = new Intent(getActivity(), ActivityZCNews.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(getActivity(), ActivityHealthNews.class);
                            startActivity(intent);
                        }
                    }
                });
                adapter.setType4ItemClickListener(new HomeRecycleAdapter.Type4ItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.d("Type4", position + "");
                        Intent intent = new Intent(getActivity(), WebBannerbenActgvity.class);
                        intent.putExtra("url", ZCNewslist.get(position - 14).getLikeUrl());
                        startActivity(intent);
                    }
                });
                adapter.setType5ItemClickListener(new HomeRecycleAdapter.Type5ItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.d("Type5", position + "size" + HealthNewslist.size());
                        Intent intent = new Intent(getActivity(), WebBannerbenActgvity.class);
                        intent.putExtra("url", HealthNewslist.get(position - (14 + ZCNewslist.size() + 5)).getLikeUrl());
                        startActivity(intent);
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
