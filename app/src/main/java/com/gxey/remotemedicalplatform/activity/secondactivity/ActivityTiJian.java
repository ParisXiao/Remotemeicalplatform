package com.gxey.remotemedicalplatform.activity.secondactivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.activity.BaseActivity;
import com.gxey.remotemedicalplatform.adapter.TabFragmentPagerAdapter;
import com.gxey.remotemedicalplatform.fragment.FragmentTiWen;
import com.gxey.remotemedicalplatform.fragment.FragmentXinDianTu;
import com.gxey.remotemedicalplatform.fragment.FragmentXueTang;
import com.gxey.remotemedicalplatform.fragment.FragmentXueYa;
import com.gxey.remotemedicalplatform.fragment.FragmentXueYang;
import com.gxey.remotemedicalplatform.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.gxey.remotemedicalplatform.R.id.toolbar_left_btn;

/**
 * Created by Administrator on 2018-03-08.
 */

public class ActivityTiJian extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.toolbar_mid)
    TextView toolbarMid;
    @BindView(toolbar_left_btn)
    ImageButton toolbarLeftBtn;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tiwen)
    TextView tiwen;
    @BindView(R.id.tiwen_line)
    View tiwenLine;
    @BindView(R.id.xueyang)
    TextView xueyang;
    @BindView(R.id.xueyang_line)
    View xueyangLine;
    @BindView(R.id.xueya)
    TextView xueya;
    @BindView(R.id.xueya_line)
    View xueyaLine;
    @BindView(R.id.xuetang)
    TextView xuetang;
    @BindView(R.id.xuetang_line)
    View xuetangLine;
    @BindView(R.id.xindiantu)
    TextView xindiantu;
    @BindView(R.id.xindiantu_line)
    View xindiantuLine;
    @BindView(R.id.ViewPager_tijian)
    ViewPager ViewPagerTijian;
    @BindView(R.id.re_tiwen)
    RelativeLayout reTiwen;
    @BindView(R.id.re_xueyang)
    RelativeLayout reXueyang;
    @BindView(R.id.re_xueya)
    RelativeLayout reXueya;
    @BindView(R.id.re_xuetang)
    RelativeLayout reXuetang;
    @BindView(R.id.re_xindiantu)
    RelativeLayout reXindiantu;
    private List<Fragment> list;
    private TabFragmentPagerAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tianjian;
    }

    @Override
    protected void initView() {
        toolbarLeftBtn.setVisibility(View.VISIBLE);
        toolbarLeftBtn.setOnClickListener(this);
        toolbarMid.setText("健康数据采集");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ScreenUtils.setStatusBarLightMode(this, R.color.black);

    }

    @Override
    protected void initData() {
        reTiwen.setOnClickListener(this);
        reXueyang.setOnClickListener(this);
        reXueya.setOnClickListener(this);
        reXuetang.setOnClickListener(this);
        reXindiantu.setOnClickListener(this);
        ViewPagerTijian.setOnPageChangeListener(new MyPagerChangeListener());
//把Fragment添加到List集合里面
        list = new ArrayList<>();
        list.add(new FragmentTiWen());
        list.add(new FragmentXueYang());
        list.add(new FragmentXueYa());
        list.add(new FragmentXueTang());
        list.add(new FragmentXinDianTu());
        adapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), list);
        ViewPagerTijian.setAdapter(adapter);
        ViewPagerTijian.setCurrentItem(0);  //初始化显示第一个页面
        tiwen.setTextColor(getResources().getColor(R.color.background_green));
        tiwenLine.setVisibility(View.VISIBLE);
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
            case R.id.re_tiwen:
                tiwen.setTextColor(getResources().getColor(R.color.background_green));
                xueyang.setTextColor(getResources().getColor(R.color.text_gray));
                xuetang.setTextColor(getResources().getColor(R.color.text_gray));
                xindiantu.setTextColor(getResources().getColor(R.color.text_gray));
                xueya.setTextColor(getResources().getColor(R.color.text_gray));
                tiwenLine.setVisibility(View.VISIBLE);
                xindiantuLine.setVisibility(View.INVISIBLE);
                xuetangLine.setVisibility(View.INVISIBLE);
                xueyaLine.setVisibility(View.INVISIBLE);
                xueyangLine.setVisibility(View.INVISIBLE);
                break;
            case R.id.re_xueyang:
                tiwen.setTextColor(getResources().getColor(R.color.text_gray));
                xueyang.setTextColor(getResources().getColor(R.color.background_green));
                xuetang.setTextColor(getResources().getColor(R.color.text_gray));
                xindiantu.setTextColor(getResources().getColor(R.color.text_gray));
                xueya.setTextColor(getResources().getColor(R.color.text_gray));
                tiwenLine.setVisibility(View.INVISIBLE);
                xindiantuLine.setVisibility(View.INVISIBLE);
                xuetangLine.setVisibility(View.INVISIBLE);
                xueyaLine.setVisibility(View.INVISIBLE);
                xueyangLine.setVisibility(View.VISIBLE);
                break;
            case R.id.re_xueya:
                tiwen.setTextColor(getResources().getColor(R.color.text_gray));
                xueyang.setTextColor(getResources().getColor(R.color.text_gray));
                xuetang.setTextColor(getResources().getColor(R.color.text_gray));
                xindiantu.setTextColor(getResources().getColor(R.color.text_gray));
                xueya.setTextColor(getResources().getColor(R.color.background_green));
                tiwenLine.setVisibility(View.INVISIBLE);
                xindiantuLine.setVisibility(View.INVISIBLE);
                xuetangLine.setVisibility(View.INVISIBLE);
                xueyaLine.setVisibility(View.VISIBLE);
                xueyangLine.setVisibility(View.INVISIBLE);
                break;
            case R.id.re_xuetang:
                tiwen.setTextColor(getResources().getColor(R.color.text_gray));
                xueyang.setTextColor(getResources().getColor(R.color.text_gray));
                xuetang.setTextColor(getResources().getColor(R.color.background_green));
                xindiantu.setTextColor(getResources().getColor(R.color.text_gray));
                xueya.setTextColor(getResources().getColor(R.color.text_gray));
                tiwenLine.setVisibility(View.INVISIBLE);
                xindiantuLine.setVisibility(View.INVISIBLE);
                xuetangLine.setVisibility(View.VISIBLE);
                xueyaLine.setVisibility(View.INVISIBLE);
                xueyangLine.setVisibility(View.INVISIBLE);
                break;
            case R.id.re_xindiantu:
                tiwen.setTextColor(getResources().getColor(R.color.text_gray));
                xueyang.setTextColor(getResources().getColor(R.color.text_gray));
                xuetang.setTextColor(getResources().getColor(R.color.text_gray));
                xindiantu.setTextColor(getResources().getColor(R.color.background_green));
                xueya.setTextColor(getResources().getColor(R.color.text_gray));
                tiwenLine.setVisibility(View.INVISIBLE);
                xindiantuLine.setVisibility(View.VISIBLE);
                xuetangLine.setVisibility(View.INVISIBLE);
                xueyaLine.setVisibility(View.INVISIBLE);
                xueyangLine.setVisibility(View.INVISIBLE);
                break;
        }
    }

    /**
     * 设置一个ViewPager的侦听事件，当左右滑动ViewPager时菜单栏被选中状态跟着改变
     */
    public class MyPagerChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
            switch (arg0) {
                case 0:
                    tiwen.setTextColor(getResources().getColor(R.color.background_green));
                    xueyang.setTextColor(getResources().getColor(R.color.text_gray));
                    xuetang.setTextColor(getResources().getColor(R.color.text_gray));
                    xindiantu.setTextColor(getResources().getColor(R.color.text_gray));
                    xueya.setTextColor(getResources().getColor(R.color.text_gray));
                    tiwenLine.setVisibility(View.VISIBLE);
                    xindiantuLine.setVisibility(View.INVISIBLE);
                    xuetangLine.setVisibility(View.INVISIBLE);
                    xueyaLine.setVisibility(View.INVISIBLE);
                    xueyangLine.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    tiwen.setTextColor(getResources().getColor(R.color.text_gray));
                    xueyang.setTextColor(getResources().getColor(R.color.background_green));
                    xuetang.setTextColor(getResources().getColor(R.color.text_gray));
                    xindiantu.setTextColor(getResources().getColor(R.color.text_gray));
                    xueya.setTextColor(getResources().getColor(R.color.text_gray));
                    tiwenLine.setVisibility(View.INVISIBLE);
                    xindiantuLine.setVisibility(View.INVISIBLE);
                    xuetangLine.setVisibility(View.INVISIBLE);
                    xueyaLine.setVisibility(View.INVISIBLE);
                    xueyangLine.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    tiwen.setTextColor(getResources().getColor(R.color.text_gray));
                    xueyang.setTextColor(getResources().getColor(R.color.text_gray));
                    xuetang.setTextColor(getResources().getColor(R.color.text_gray));
                    xindiantu.setTextColor(getResources().getColor(R.color.text_gray));
                    xueya.setTextColor(getResources().getColor(R.color.background_green));
                    tiwenLine.setVisibility(View.INVISIBLE);
                    xindiantuLine.setVisibility(View.INVISIBLE);
                    xuetangLine.setVisibility(View.INVISIBLE);
                    xueyaLine.setVisibility(View.VISIBLE);
                    xueyangLine.setVisibility(View.INVISIBLE);
                    break;
                case 3:
                    tiwen.setTextColor(getResources().getColor(R.color.text_gray));
                    xueyang.setTextColor(getResources().getColor(R.color.text_gray));
                    xuetang.setTextColor(getResources().getColor(R.color.background_green));
                    xindiantu.setTextColor(getResources().getColor(R.color.text_gray));
                    xueya.setTextColor(getResources().getColor(R.color.text_gray));
                    tiwenLine.setVisibility(View.INVISIBLE);
                    xindiantuLine.setVisibility(View.INVISIBLE);
                    xuetangLine.setVisibility(View.VISIBLE);
                    xueyaLine.setVisibility(View.INVISIBLE);
                    xueyangLine.setVisibility(View.INVISIBLE);
                    break;
                case 4:
                    tiwen.setTextColor(getResources().getColor(R.color.text_gray));
                    xueyang.setTextColor(getResources().getColor(R.color.text_gray));
                    xuetang.setTextColor(getResources().getColor(R.color.text_gray));
                    xindiantu.setTextColor(getResources().getColor(R.color.background_green));
                    xueya.setTextColor(getResources().getColor(R.color.text_gray));
                    tiwenLine.setVisibility(View.INVISIBLE);
                    xindiantuLine.setVisibility(View.VISIBLE);
                    xuetangLine.setVisibility(View.INVISIBLE);
                    xueyaLine.setVisibility(View.INVISIBLE);
                    xueyangLine.setVisibility(View.INVISIBLE);
                    break;
            }
        }
    }
}
