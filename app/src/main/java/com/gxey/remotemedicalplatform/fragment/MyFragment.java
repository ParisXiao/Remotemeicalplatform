package com.gxey.remotemedicalplatform.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.inter.ActionBarClickListener;
import com.gxey.remotemedicalplatform.utils.PreferenceUtils;
import com.gxey.remotemedicalplatform.widget.TranslucentActionBar;
import com.gxey.remotemedicalplatform.widget.TranslucentScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xusongsong on 2016/12/21.
 */

public class MyFragment extends BaseFragment implements ActionBarClickListener, TranslucentScrollView.TranslucentChangedListener,View.OnClickListener {


    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.text_zcdl)
    TextView textZcdl;
    @BindView(R.id.img_dingdan)
    ImageView imgDingdan;
    @BindView(R.id.re_dingdan)
    RelativeLayout reDingdan;
    @BindView(R.id.img_quanzi)
    ImageView imgQuanzi;
    @BindView(R.id.re_quanzi)
    RelativeLayout reQuanzi;
    @BindView(R.id.img_ziliao)
    ImageView imgZiliao;
    @BindView(R.id.re_ziliao)
    RelativeLayout reZiliao;
    @BindView(R.id.img_guanyu)
    ImageView imgGuanyu;
    @BindView(R.id.re_guanyu)
    RelativeLayout reGuanyu;
    @BindView(R.id.img_bangzhu)
    ImageView imgBangzhu;
    @BindView(R.id.re_bangzhu)
    RelativeLayout reBangzhu;
    @BindView(R.id.img_xiaoxi)
    ImageView imgXiaoxi;
    @BindView(R.id.re_xiaoxi)
    RelativeLayout reXiaoxi;
    @BindView(R.id.img_fankui)
    ImageView imgFankui;
    @BindView(R.id.re_fankui)
    RelativeLayout reFankui;
    @BindView(R.id.btn_logout)
    Button btnLogout;
    public TranslucentScrollView transcrollview;
    public TranslucentActionBar tranactionbar;
    Unbinder unbinder;
    public RelativeLayout headZoom;

    public static MyFragment newInstance(String param1) {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public MyFragment() {

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView(View view) {
        init(view);
    }

    public void init(View view) {
        textZcdl.setText(PreferenceUtils.getInstance(getActivity()).getString("UserName",""));
//        tvPersonalInformation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getActivity(), PersonalInformationActivity.class));
//            }
//        });
//        circle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(getActivity(), WebCircleActivity.class);
//                startActivity(intent);
//            }
//        });
//        fankui.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(getActivity(), WebOpinionActivity.class);
//                startActivity(intent);
//            }
//        });
//        yongyao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(getActivity(), WebMedicationActivity.class);
//                startActivity(intent);
//            }
//        });
//        zhengliao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(getActivity(), WebMyDiagnosisActivity.class);
//                startActivity(intent);
//            }
//        });
//        help.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(getActivity(), WebHelpActivity.class);
//                startActivity(intent);
//            }
//        });
//        index.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(getActivity(), WebIndexActivity.class);
//                startActivity(intent);
//            }
//        });
        //初始actionBar
        tranactionbar= (TranslucentActionBar) view.findViewById(R.id.actionbar);
        transcrollview= (TranslucentScrollView) view.findViewById(R.id.transcrollview);
        tranactionbar.setData("个人中心", 0, null, 0, null, null);
        //开启渐变
        tranactionbar.setNeedTranslucent();
        //设置状态栏高度
        tranactionbar.setStatusBarHeight(getStatusBarHeight());

        //设置透明度变化监听
        transcrollview.setTranslucentChangedListener(this);
        //关联需要渐变的视图
        transcrollview.setTransView(tranactionbar);
        //设置ActionBar键渐变颜色
        transcrollview.setTransColor(getResources().getColor(R.color.background_green));
        headZoom= (RelativeLayout) view.findViewById(R.id.head_zoom);
        //关联伸缩的视图
        transcrollview.setPullZoomView(headZoom);
    }

    @Override
    protected void initData() {

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
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onTranslucentChanged(int transAlpha) {
        tranactionbar.tvActionbarTitle.setVisibility(transAlpha > 48 ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}
