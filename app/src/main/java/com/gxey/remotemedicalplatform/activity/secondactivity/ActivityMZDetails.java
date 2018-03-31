package com.gxey.remotemedicalplatform.activity.secondactivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.activity.BaseActivity;
import com.gxey.remotemedicalplatform.bean.DianZiBLBean;
import com.gxey.remotemedicalplatform.bean.MZBLBean;
import com.gxey.remotemedicalplatform.utils.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.gxey.remotemedicalplatform.R.id.toolbar_left_btn;

/**
 * Created by Administrator on 2018-03-03.
 */

public class ActivityMZDetails extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.toolbar_mid)
    TextView toolbarMid;
    @BindView(toolbar_left_btn)
    ImageButton toolbarLeftBtn;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.dz_blh)
    TextView dzBlh;
    @BindView(R.id.dz_chuzhen)
    TextView dzChuzhen;
    @BindView(R.id.dz_zhenzhuang)
    TextView dzZhenzhuang;
    @BindView(R.id.dz_yisheng)
    TextView dzYisheng;
    @BindView(R.id.dz_jiwangshi)
    TextView dzJiwangshi;
    @BindView(R.id.dz_gerenshi)
    TextView dzGerenshi;
    @BindView(R.id.dz_jiazushi)
    TextView dzJiazushi;
    @BindView(R.id.dz_tigejc)
    TextView dzTigejc;
    @BindView(R.id.dz_fuzhujc)
    TextView dzFuzhujc;
    @BindView(R.id.dz_zhenduan)
    TextView dzZhenduan;
    @BindView(R.id.dz_tsbz)
    TextView dzTsbz;
    @BindView(R.id.dz_clyj)
    TextView dzClyj;
    @BindView(R.id.dz_bz)
    TextView dzBz;
    @BindView(R.id.dz_shijian)
    TextView dzShijian;
    private MZBLBean dianZiBLBean=new MZBLBean();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dzblxq;
    }

    @Override
    protected void initView() {
        toolbarLeftBtn.setVisibility(View.VISIBLE);
        toolbarLeftBtn.setOnClickListener(this);
        toolbarMid.setText("门诊病历详情");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ScreenUtils.setStatusBarLightMode(this, R.color.black);
    }

    @Override
    protected void initData() {
        dianZiBLBean= (MZBLBean) getIntent().getSerializableExtra("MZBL");
        dzBlh.setText(dianZiBLBean.getCaseNumber());
        if (dianZiBLBean.getPeriod().equals("1")){
            dzChuzhen.setText("初诊");
        }else if (dianZiBLBean.getPeriod().equals("1")){
            dzChuzhen.setText("复诊");
        }
        dzZhenzhuang.setText(dianZiBLBean.getMainSuit());
        dzYisheng.setText(dianZiBLBean.getDoctorId());
        dzJiwangshi.setText(dianZiBLBean.getHistoryOfPastIllness());
        dzGerenshi.setText(dianZiBLBean.getPersonalHistory());
        dzJiazushi.setText(dianZiBLBean.getFHx());
        dzTigejc.setText(dianZiBLBean.getPhysicalExamination());
        dzFuzhujc.setText("无");
        dzZhenduan.setText(dianZiBLBean.getDiagnosis());
        dzTsbz.setText(dianZiBLBean.getSpecial());
        dzClyj.setText(dianZiBLBean.getHandlingSuggestion());
        dzBz.setText(dianZiBLBean.getRemarks());
        dzShijian.setText(dianZiBLBean.getBoardingTime());

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
