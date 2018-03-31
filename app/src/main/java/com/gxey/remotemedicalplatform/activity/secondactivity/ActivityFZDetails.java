package com.gxey.remotemedicalplatform.activity.secondactivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.activity.BaseActivity;
import com.gxey.remotemedicalplatform.bean.FenZhenBean;
import com.gxey.remotemedicalplatform.utils.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.gxey.remotemedicalplatform.R.id.toolbar_left_btn;

/**
 * Created by Administrator on 2018-03-03.
 */

public class ActivityFZDetails extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.toolbar_mid)
    TextView toolbarMid;
    @BindView(R.id.toolbar_left_btn)
    ImageButton toolbarLeftBtn;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.zz_sqdh)
    TextView zzSqdh;
//    @BindView(R.id.zz_chuzhen)
//    TextView zzChuzhen;
    @BindView(R.id.zz_zhenzhuang)
    TextView zzZhenzhuang;
    @BindView(R.id.zz_zhuanchu)
    TextView zzZhuanchu;
    @BindView(R.id.zz_zhuanru)
    TextView zzZhuanru;
    @BindView(R.id.zz_keshi)
    TextView zzKeshi;
    @BindView(R.id.zz_jztime)
    TextView zzJztime;
    @BindView(R.id.zz_yisheng)
    TextView zzYisheng;
    @BindView(R.id.zz_shenhe)
    TextView zzShenhe;
    @BindView(R.id.zz_shijian)
    TextView zzShijian;
    private FenZhenBean fenZhenBean=new FenZhenBean();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zhuanzhendetails;
    }

    @Override
    protected void initView() {
        toolbarLeftBtn.setVisibility(View.VISIBLE);
        toolbarLeftBtn.setOnClickListener(this);
        toolbarMid.setText(R.string.zhuanzhendan);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ScreenUtils.setStatusBarLightMode(this, R.color.black);
    }

    @Override
    protected void initData() {
        fenZhenBean= (FenZhenBean) getIntent().getSerializableExtra("FenZhen");
        zzSqdh.setText(fenZhenBean.getApplyFor());
        zzZhenzhuang.setText(fenZhenBean.getSickness());
        zzZhuanchu.setText(fenZhenBean.getRollout());
        zzZhuanru.setText(fenZhenBean.getTahospital());
        zzKeshi.setText(fenZhenBean.getOffice());
        zzYisheng.setText(fenZhenBean.getDoctor());
        zzJztime.setText(fenZhenBean.getClinicTime());
        if (fenZhenBean.getAuditState().equals("0")){
            zzShenhe.setText("未审核");
        }else  if(fenZhenBean.getAuditState().equals("1")){
            zzShenhe.setText("审核通过");
        }else  if(fenZhenBean.getAuditState().equals("2")){
            zzShenhe.setText("审核不通过");
        }
        zzShijian.setText(fenZhenBean.getRegisterTime());

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
