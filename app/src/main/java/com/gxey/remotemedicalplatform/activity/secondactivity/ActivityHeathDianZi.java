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
import com.gxey.remotemedicalplatform.activity.WebMedicationActivity;
import com.gxey.remotemedicalplatform.activity.WebMyDiagnosisActivity;
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

public class ActivityHeathDianZi extends BaseActivity implements View.OnClickListener {
    @BindView(toolbar_mid)
    TextView toolbarMid;
    @BindView(toolbar_left_btn)
    ImageButton toolbarLeftBtn;
    @BindView(R.id.dangan_recycler)
    RecyclerView danganRecycler;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String[] menu_names = new String[]{"医嘱", "电子病历", "门诊病历", "相关医院病历", "相关医院医嘱", "用药咨询", "诊疗记录"};
    private int[] menu_imgs = new int[]{R.drawable.yizhu, R.drawable.dianzibingli, R.drawable.menzhenbingli
            , R.drawable.xianguanyiyanbingli, R.drawable.xiangguanyiyuanyizhu, R.drawable.yongyaozixun, R.drawable.zhenliaojilu};

    private List<BeanMenu> beanMenus;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dangan;
    }

    @Override
    protected void initView() {
        toolbarLeftBtn.setVisibility(View.VISIBLE);
        toolbarLeftBtn.setOnClickListener(this);
        toolbarMid.setText(R.string.dianzibingli);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ScreenUtils.setStatusBarLightMode(this,R.color.black);
        danganRecycler.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));


    }

    @Override
    protected void initData() {
        beanMenus=new ArrayList<>();
        for (int i = 0; i < menu_names.length; i++) {
            BeanMenu beanMenu=new BeanMenu();
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
                        Intent intent1=new Intent(ActivityHeathDianZi.this,ActivityYiZhu.class);
                        startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2=new Intent(ActivityHeathDianZi.this,ActivityDZBL.class);
                        startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3=new Intent(ActivityHeathDianZi.this,ActivityMZBL.class);
                        startActivity(intent3);
                        break;
                    case 3:
                        Intent intent4=new Intent(ActivityHeathDianZi.this,ActivityXGBL.class);
                        startActivity(intent4);
                        break;
                    case 4:
                        Intent intent5=new Intent(ActivityHeathDianZi.this,ActivityXGYZ.class);
                        startActivity(intent5);
                        break;
                    case 5:
                        Intent intent6= new Intent(ActivityHeathDianZi.this, WebMedicationActivity.class);
                        startActivity(intent6);
                        break;
                    case 6:
                        Intent intent7= new Intent(ActivityHeathDianZi.this, WebMyDiagnosisActivity.class);
                        startActivity(intent7);
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
