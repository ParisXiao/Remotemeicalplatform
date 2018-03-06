package com.gxey.remotemedicalplatform.activity;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.fragment.FragmentPage3;
import com.gxey.remotemedicalplatform.fragment.HealthManagementFragment;
import com.gxey.remotemedicalplatform.fragment.HomeFragment;
import com.gxey.remotemedicalplatform.fragment.MyFragment;
import com.gxey.remotemedicalplatform.network.SendPushSigleR;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import fr.pchab.webrtcclient.SignalaUtils;

import static com.gxey.remotemedicalplatform.utils.KeyboardUtil.hideInputMethod;
import static com.gxey.remotemedicalplatform.utils.KeyboardUtil.isShouldHideInput;


public class MainActivity extends BaseActivity {
    private BottomNavigationBar my_bottom_bar;
    private LinearLayout my_line;
    private BadgeItem badge;
    private HomeFragment homefra;
    private HealthManagementFragment heafra;
    private FragmentPage3 fra3;
    private MyFragment myfra;
    private long mPressedTime = 0;
    private int position=0;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initViews();
        initBottomNavigationBar();
        setDefaultFragment();
        initPermission();
        new SendPushSigleR(this);
        loginUser();
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                if(hideInputMethod( this,v)) {
                    return true; //隐藏键盘时，其他控件不响应点击事件==》注释则不拦截点击事件
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }
    private void initPermission(){
        if(AndPermission.hasPermission(MainActivity.this, Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            // 有权限，直接do anything.
            //statrCanmear();
        } else {
            // 申请权限。
            AndPermission.with(MainActivity.this)
                    .requestCode(100)
                    .permission(Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .send();
        }
    }
    @PermissionNo(100)
    private void getCamearLocationNo(List<String> deniedPermissions) {
        // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
        if (AndPermission.hasAlwaysDeniedPermission(this, deniedPermissions)) {
            // 第一种：用默认的提示语。
            AndPermission.defaultSettingDialog(this, 100).show();
        }
    }
    @Override
    protected void initData() {

    }
    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.main_liner, HomeFragment.newInstance("Home"));
        transaction.commit();
    }
    private void initBottomNavigationBar() {
        my_bottom_bar.setMode(BottomNavigationBar.MODE_FIXED);
        my_bottom_bar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        //设置默认颜色
        my_bottom_bar
                .setInActiveColor(R.color.lin2)//设置未选中的Item的颜色，包括图片和文字
                .setActiveColor(R.color.background_green)//设置选中
              .setBarBackgroundColor(R.color.white);//背景

        //添加选项
        my_bottom_bar.addItem(new BottomNavigationItem(R.mipmap.shouye_xuanzhong, "首页"))
                .addItem(new BottomNavigationItem(R.mipmap.guanli_moren, "健康管理"))
                .addItem(new BottomNavigationItem(R.mipmap.sahngcehng_xuanzhong, "商城"))
                .addItem(new BottomNavigationItem(R.mipmap.wode_moren, "我的"))
                .initialise();//初始化BottomNavigationButton,所有设置需在调用该方法前完成



        my_bottom_bar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            FragmentManager fm = getFragmentManager() ;
            //开启事务
            FragmentTransaction transaction = fm.beginTransaction();
            @Override
            public void onTabSelected(int position) {//未选中 -> 选中
                FragmentTransaction transaction = fm.beginTransaction();
                switch (position) {
                    case 0:
                        MainActivity.this.position=0;
                    if (homefra == null) {
                        homefra = HomeFragment.newInstance("首页");
                    }
                    transaction.replace(R.id.main_liner, homefra);
                    break;
                    case 1:
                        MainActivity.this.position=1;
                        if (heafra == null) {
                            heafra = HealthManagementFragment.newInstance("健康管理");
                        }
                        transaction.replace(R.id.main_liner, heafra);
                        break;
                    case 2:
//                        if (fra3 == null) {
//                            fra3 = FragmentPage3.newInstance("药品商城");
//
//                        }
//                        transaction.replace(R.id.main_liner, fra3);
                        Intent intent = new Intent(MainActivity.this,ActivityDrugStore.class);
                        startActivity(intent);

                        my_bottom_bar.selectTab( MainActivity.this.position,true);

                        break;
                    case 3:
                        MainActivity.this.position=3;
                        if (myfra == null) {
                            myfra = MyFragment.newInstance("我的");
                        }
                        transaction.replace(R.id.main_liner, myfra);
                        break;
                    default:
                        break;

                }
                // 事务提交
                transaction.commit();

            }


            @Override
            public void onTabUnselected(int position) {//选中 -> 未选中

            }

            @Override
            public void onTabReselected(int position) {//选中 -> 选中
//                FragmentTransaction transaction = fm.beginTransaction();
//                if (homefra == null) {
//                    homefra = HomeFragment.newInstance("UI界面");
//                }
//                transaction.replace(R.id.main_liner, homefra);
//                transaction.commit();
            }
        });

    }


    public void hide(View view) {
        my_bottom_bar.hide();
    }
    private void initViews() {
        my_line = (LinearLayout)findViewById(R.id.main_liner);
        my_bottom_bar = (BottomNavigationBar)findViewById(R.id.bottom_navigation_bar);
    }
    @Override
    public void onBackPressed() {
        long mNowTime = System.currentTimeMillis();//获取第一次按键时间
        if((mNowTime - mPressedTime) > 2000){//比较两次按键时间差
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mPressedTime = mNowTime;
        }
        else{//退出程序
            this.finish();
            System.exit(0);
        }
    }

    public void loginUser(){
        JSONArray jsonArray=new JSONArray();

        JSONObject json = new JSONObject();
        try {
            json.put("Username",mConfig.getUserName());
            json.put("UserType","1");
            json.put("GUID",mConfig.getUserGUID());
            json.put("Division", mConfig.getDivision());
            json.put("IEGUID",mConfig.getDeviceId());
            json.put("Position","会员");
            json.put("StoreID",mConfig.getStoreID());
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonArray.put(json);

        SignalaUtils.getInstance(this).sendMessage("sendLogin",jsonArray);
    }
}
