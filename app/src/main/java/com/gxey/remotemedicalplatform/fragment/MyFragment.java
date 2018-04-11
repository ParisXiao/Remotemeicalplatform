package com.gxey.remotemedicalplatform.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.activity.ActivityDrugStore;
import com.gxey.remotemedicalplatform.activity.LoginActivity;
import com.gxey.remotemedicalplatform.activity.MainActivity;
import com.gxey.remotemedicalplatform.activity.PersonalInformationActivity;
import com.gxey.remotemedicalplatform.activity.WebCircleActivity;
import com.gxey.remotemedicalplatform.activity.WebHelpActivity;
import com.gxey.remotemedicalplatform.activity.WebIndexActivity;
import com.gxey.remotemedicalplatform.activity.WebOpinionActivity;
import com.gxey.remotemedicalplatform.db.MySQLiteOpenHelper;
import com.gxey.remotemedicalplatform.inter.ActionBarClickListener;
import com.gxey.remotemedicalplatform.utils.PreferenceUtils;
import com.gxey.remotemedicalplatform.widget.TranslucentActionBar;
import com.gxey.remotemedicalplatform.widget.TranslucentScrollView;

import org.json.JSONArray;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fr.pchab.webrtcclient.SignalaUtils;

/**
 * Created by xusongsong on 2016/12/21.
 */

public class MyFragment extends BaseFragment implements ActionBarClickListener, TranslucentScrollView.TranslucentChangedListener, View.OnClickListener {


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
    //    @BindView(R.id.img_xiaoxi)
//    ImageView imgXiaoxi;
//    @BindView(R.id.re_xiaoxi)
//    RelativeLayout reXiaoxi;
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
        textZcdl.setText(PreferenceUtils.getInstance(getActivity()).getString("UserName", ""));
        //初始actionBar
        tranactionbar = (TranslucentActionBar) view.findViewById(R.id.actionbar);
        transcrollview = (TranslucentScrollView) view.findViewById(R.id.transcrollview);
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
        headZoom = (RelativeLayout) view.findViewById(R.id.head_zoom);
        //关联伸缩的视图
        transcrollview.setPullZoomView(headZoom);
    }

    @Override
    protected void initData() {
        reBangzhu.setOnClickListener(this);
        reDingdan.setOnClickListener(this);
        reFankui.setOnClickListener(this);
        reGuanyu.setOnClickListener(this);
        reQuanzi.setOnClickListener(this);
//        reXiaoxi.setOnClickListener(this);
        reZiliao.setOnClickListener(this);
        btnLogout.setOnClickListener(this);

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
        switch (v.getId()) {
            case R.id.re_dingdan:
                Intent intent0 = new Intent(getActivity(), ActivityDrugStore.class);
                startActivity(intent0);
                break;
            case R.id.re_ziliao:
                startActivity(new Intent(getActivity(), PersonalInformationActivity.class));
                break;
            case R.id.re_quanzi:
                Intent intent = new Intent(getActivity(), WebCircleActivity.class);
                startActivity(intent);
                break;
            case R.id.re_bangzhu:
                Intent intent1 = new Intent(getActivity(), WebHelpActivity.class);
                startActivity(intent1);
                break;
            case R.id.re_guanyu:
                Intent intent2 = new Intent(getActivity(), WebIndexActivity.class);
                startActivity(intent2);
                break;
            case R.id.re_fankui:
                Intent intent3 = new Intent(getActivity(), WebOpinionActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_logout:
                SharedPreferences sp = getActivity().getSharedPreferences("saveUserNamePwd", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();
                MySQLiteOpenHelper sqLiteOpenHelper = new MySQLiteOpenHelper(getActivity());
                sqLiteOpenHelper.deleteAll();
                JSONArray jsonArray = new JSONArray();
                jsonArray.put(mConfig.getRoomID());
                SignalaUtils.getInstance(getActivity()).sendMessage("sendLeave", jsonArray);
                SignalaUtils.getInstance(getActivity()).close();
                getActivity().finish();
                System.exit(0);
                break;

        }
    }
}
