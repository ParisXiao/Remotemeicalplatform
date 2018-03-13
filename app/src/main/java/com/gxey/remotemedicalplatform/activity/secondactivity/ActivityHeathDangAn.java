package com.gxey.remotemedicalplatform.activity.secondactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.activity.BaseActivity;
import com.gxey.remotemedicalplatform.adapter.AdapterMenu;
import com.gxey.remotemedicalplatform.bean.BeanMenu;
import com.gxey.remotemedicalplatform.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.gxey.remotemedicalplatform.R.id.toolbar_left_btn;
import static com.gxey.remotemedicalplatform.R.id.toolbar_mid;

/**
 * Created by Administrator on 2018/2/28 0028.
 */

public class ActivityHeathDangAn extends BaseActivity implements View.OnClickListener {
    @BindView(toolbar_mid)
    TextView toolbarMid;
    @BindView(toolbar_left_btn)
    ImageButton toolbarLeftBtn;
    @BindView(R.id.dangan_recycler)
    RecyclerView danganRecycler;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String[] menu_names = new String[]{"基本信息", "家族史", "遗传史", "既往史", "残疾情况", "过敏史", "生活环境", "家庭常备药", "体检数据", "健康报告"};
    private int[] menu_imgs = new int[]{R.drawable.jiebnxixni, R.drawable.jiazhushi, R.drawable.yichuanshi
            , R.drawable.jiwangshi, R.drawable.canjiqingkuang, R.drawable.guominshi, R.drawable.shenghuohuanjing, R.drawable.changbeiyao, R.drawable.tijianshuju, R.drawable.jiankangbaogao};

    private List<BeanMenu> beanMenus;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dangan;
    }

    @Override
    protected void initView() {
        toolbarLeftBtn.setVisibility(View.VISIBLE);
        toolbarLeftBtn.setOnClickListener(this);
        toolbarMid.setText(R.string.jiankangdangan);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ScreenUtils.setStatusBarLightMode(this,R.color.black);
        danganRecycler.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));


    }

    @Override
    protected void initData() {
        beanMenus = new ArrayList<>();
        for (int i = 0; i < menu_names.length; i++) {
            BeanMenu beanMenu = new BeanMenu();
            beanMenu.setMeunIocn(menu_imgs[i]);
            beanMenu.setMeunName(menu_names[i]);
            beanMenus.add(beanMenu);
        }
        AdapterMenu adapterMenu = new AdapterMenu(this, beanMenus);
        danganRecycler.setAdapter(adapterMenu);
        adapterMenu.setItemClickListener(new AdapterMenu.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        Intent intent = new Intent(ActivityHeathDangAn.this, ActivityMyMsg.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(ActivityHeathDangAn.this, ActivityJiaZu.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(ActivityHeathDangAn.this, ActivityYiChuan.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(ActivityHeathDangAn.this, ActivityJiWangShi.class);
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(ActivityHeathDangAn.this, ActivityCanJi.class);
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent(ActivityHeathDangAn.this, ActivityGuoMing.class);
                        startActivity(intent5);
                        break;
                    case 6:
                        Intent intent6 = new Intent(ActivityHeathDangAn.this, ActivityHuanJing.class);
                        startActivity(intent6);
                        break;
                    case 7:
                        Intent intent7 = new Intent(ActivityHeathDangAn.this, ActivityChangBeiYao.class);
                        startActivity(intent7);
                        break;
                    case 8:
                        Intent intent8 = new Intent(ActivityHeathDangAn.this, ActivityTiJian.class);
                        startActivity(intent8);
                        break;

                    case 9:
                        Intent intent9= new Intent(ActivityHeathDangAn.this, ActivityHealthBaoGao.class);
                        startActivity(intent9);
                        break;

                }
            }
        });
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
