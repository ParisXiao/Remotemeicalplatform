package com.gxey.remotemedicalplatform.activity;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.VpnService;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gxey.remotemedicalplatform.newconfig.UserConfig;
import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.db.Information;
import com.gxey.remotemedicalplatform.db.MySQLiteOpenHelper;
import com.gxey.remotemedicalplatform.javaben.LogBen;
import com.gxey.remotemedicalplatform.network.HttpClientHelper;
import com.gxey.remotemedicalplatform.network.HttpSubseiber;
import com.gxey.remotemedicalplatform.utils.PreferenceUtils;
import com.yanzhenjie.permission.AndPermission;

import fr.pchab.webrtcclient.SignalaUtils;


/**
 * Created by xusongsong on 2016/12/21.
 * 登录界面
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private TextView registered;
    private TextView backpassworld;
    private TextView login;
    private EditText user_name;
    private EditText password;
    private CheckBox CheckBox;
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private String isMemory = "";//isMemory变量用来判断SharedPreferences有没有数据，包括上面的YES和NO
    private String FILE = "saveUserNamePwd";//用于保存SharedPreferences的文件
    private SharedPreferences sp = null;//声明一个SharedPreferences
    static String YES = "yes";
    static String NO = "no";
    String name, password1;
    protected int getLayoutId() {
        return R.layout.acctivity_login;
    }

    @Override
    protected void initView() {
        //sysPrintln();
        SignalaUtils.getInstance(this);
        if(AndPermission.hasPermission(this, Manifest.permission.READ_PHONE_STATE)) {
            // 有权限，直接do anything.
            //statrCanmear();
        } else {
            // 申请权限。
            AndPermission.with(this)
                    .requestCode(100)
                    .permission(Manifest.permission.READ_PHONE_STATE)
                    .send();
        }
        initViews();
        sp = getSharedPreferences(FILE, MODE_PRIVATE);
        isMemory = sp.getString("isMemory", NO);
        if (isMemory.equals(YES)) {
            name = sp.getString("name", "");
            password1 = sp.getString("password", "");
            user_name.setText(name);
            password.setText(password1);
            inilog();
        }
        Editor editor = sp.edit();
        editor.putString(name, user_name.toString());
        editor.putString(password1, password.toString());
        editor.commit();
    }

    @Override
    protected void initData() {


    }
    private void initViews() {
        mySQLiteOpenHelper = new MySQLiteOpenHelper(LoginActivity.this);
        registered = (TextView)findViewById(R.id.lg_registered);
        backpassworld = (TextView)findViewById(R.id.lg_back);
        login = (TextView)findViewById(R.id.lg_tv_login);
        user_name = (EditText)findViewById(R.id.lg_ed_username);
        password = (EditText)findViewById(R.id.lg_ed_password);
        CheckBox = (CheckBox)findViewById(R.id.lg_radio);



        registered.setOnClickListener(this);
        backpassworld.setOnClickListener(this);
        login.setOnClickListener(this);

        //showLoadDialog();
    }
    private void remenber() {
        if (CheckBox.isChecked()) {
            if (sp == null) {
                sp = getSharedPreferences(FILE, MODE_PRIVATE);
            }
            Editor edit = sp.edit();
            edit.putString("name", user_name.getText().toString());
            edit.putString("password", password.getText().toString());
            edit.putString("isMemory", YES);
            edit.commit();
        } else if (!CheckBox.isChecked()) {
            if (sp == null) {
                sp = getSharedPreferences(FILE, MODE_PRIVATE);
            }
            Editor edit = sp.edit();
            edit.putString("isMemory", NO);
            edit.commit();
        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lg_registered:
                Intent intent = new Intent(LoginActivity.this,RegisteredActivity.class);
                startActivity(intent);
                break;
            case R.id.lg_back:
                Intent intent2 = new Intent(LoginActivity.this,BackPasswordActivity.class);
                startActivity(intent2);
                break;
            case R.id.lg_tv_login:
                remenber();
               inilog();

                break;

        }
    }

    private void inilog() {
        String mname = user_name.getText().toString();
        String mpwd = password.getText().toString();
        if (TextUtils.isEmpty(mname)){
            Toast.makeText(LoginActivity.this,"请输入用户名",Toast.LENGTH_LONG).show();

        }
        else if (TextUtils.isEmpty(mpwd)){

            Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_LONG).show();
        }
        else {
            showLoadDialog();
            HttpClientHelper.getInstance().loginUser(mname, mpwd,  new HttpSubseiber.ResponseHandler<LogBen>() {
                @Override
                public void onSucceed(LogBen data) {
                    dismisDialog();
                    PreferenceUtils.getInstance(LoginActivity.this).saveString(UserConfig.UserName,data.getUName());
                    PreferenceUtils.getInstance(LoginActivity.this).saveString(UserConfig.UserId,data.getID());
                    PreferenceUtils.getInstance(LoginActivity.this).saveString(UserConfig.RealName,data.getUNum());
                    PreferenceUtils.getInstance(LoginActivity.this).saveString(UserConfig.HeadImg,data.getHeadImg() );
                    PreferenceUtils.getInstance(LoginActivity.this).saveString(UserConfig.Sex,data.getSex());
                    PreferenceUtils.getInstance(LoginActivity.this).saveString(UserConfig.Address,data.getAddr());
                    PreferenceUtils.getInstance(LoginActivity.this).saveString(UserConfig.Phone,data.getMobileNum());
                    PreferenceUtils.getInstance(LoginActivity.this).saveString(UserConfig.SFZH,data.getSFZH());
                    PreferenceUtils.getInstance(LoginActivity.this).saveString(UserConfig.Age,data.getAge());
                    mConfig.setUserGUID(data.getID());
                    mConfig.setUserName(data.getUName());
                    mConfig.setStoreID(data.getStoreID());
                    String mpwd = password.getText().toString();
                    ContentValues cv = new ContentValues();
                    cv.put(Information.Id,data.getID());
                    cv.put(Information.PositionID,data.getPositionID());
                    cv.put(Information.UName,data.getUName());
                    cv.put(Information.UNum,data.getUNum());
                    cv.put(Information.Sex,data.getSex());
                    cv.put(Information.MobileNum,data.getMobileNum());
                    cv.put(Information.Addr,data.getAddr());
                    cv.put(Information.password,mpwd);
                    boolean flag = mySQLiteOpenHelper.insertData(cv);
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_LONG).show();
                    finish();

                }

                @Override
                public void onFail(String msg) {
                    Log.e("-----------","========"+msg);
                    Toast.makeText(LoginActivity.this,"用户名或密码错误",Toast.LENGTH_LONG).show();
                    dismisDialog();
                }
            });


        }
    }


    private long mPressedTime = 0;

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


    private void initVPN(){

        Intent intent = VpnService.prepare(this);
        if (intent != null) {
            startActivityForResult(intent, 0);
        } else {
            onActivityResult(0, RESULT_OK, null);
        }

    }

}
