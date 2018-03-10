package com.gxey.remotemedicalplatform.activity.secondactivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.activity.BaseActivity;
import com.gxey.remotemedicalplatform.utils.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.gxey.remotemedicalplatform.R.id.toolbar_left_btn;

/**
 * Created by Administrator on 2018-03-03.
 */

public class ActivityYiZhuDetails extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.toolbar_mid)
    TextView toolbarMid;
    @BindView(R.id.toolbar_left_btn)
    ImageButton toolbarLeftBtn;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.yizhudetails_content)
    TextView yizhudetailsContent;
    @BindView(R.id.yizhudetails_ID)
    TextView yizhudetailsID;
    @BindView(R.id.yizhudetails_yisheng)
    TextView yizhudetailsYisheng;
    @BindView(R.id.yizhudetails_starttime)
    TextView yizhudetailsStarttime;
    @BindView(R.id.yizhudetails_qixiao)
    TextView yizhudetailsQixiao;
    @BindView(R.id.yizhudetails_pl)
    TextView yizhudetailsPl;
    @BindView(R.id.yizhudetails_jigou)
    TextView yizhudetailsJigou;
    @BindView(R.id.yizhudetails_time)
    TextView yizhudetailsTime;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_yizhudetails;
    }

    @Override
    protected void initView() {
        toolbarLeftBtn.setVisibility(View.VISIBLE);
        toolbarLeftBtn.setOnClickListener(this);
        toolbarMid.setText(R.string.yizhuxiangqing);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ScreenUtils.setStatusBarLightMode(this, R.color.black);
    }

    @Override
    protected void initData() {

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
