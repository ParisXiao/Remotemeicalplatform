package com.gxey.remotemedicalplatform.activity;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.VpnService;
import android.os.Environment;
import android.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.gxey.remotemedicalplatform.inter.UpdataApkInterface;
import com.gxey.remotemedicalplatform.mynetwork.MyHttpHelper;
import com.gxey.remotemedicalplatform.newconfig.UrlConfig;
import com.gxey.remotemedicalplatform.newconfig.UserConfig;
import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.db.Information;
import com.gxey.remotemedicalplatform.db.MySQLiteOpenHelper;
import com.gxey.remotemedicalplatform.javaben.LogBen;
import com.gxey.remotemedicalplatform.network.HttpClientHelper;
import com.gxey.remotemedicalplatform.network.HttpSubseiber;
import com.gxey.remotemedicalplatform.utils.MyStrUtil;
import com.gxey.remotemedicalplatform.utils.PreferenceUtils;
import com.gxey.remotemedicalplatform.utils.ToastUtils;
import com.yanzhenjie.permission.AndPermission;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import fr.pchab.webrtcclient.SignalaUtils;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


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
    private boolean isUpdata=false;

    protected int getLayoutId() {
        return R.layout.acctivity_login;
    }

    @Override
    protected void initView() {
        //sysPrintln();
        SignalaUtils.getInstance(this);
        if (AndPermission.hasPermission(this, Manifest.permission.READ_PHONE_STATE)) {
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
        showUpdata();
        sp = getSharedPreferences(FILE, MODE_PRIVATE);
        isMemory = sp.getString("isMemory", NO);
        if (isMemory.equals(YES)) {
            name = sp.getString("name", "");
            password1 = sp.getString("password", "");
            user_name.setText(name);
            password.setText(password1);

        }
        Editor editor = sp.edit();
        editor.putString(name, user_name.toString());
        editor.putString(password1, password.toString());
        editor.commit();
    }
    String downUrl;
    String version;
    private void showUpdata() {
        rx.Observable.create(new rx.Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                String[] key = new String[]{"Type"};
                Map<String, String> map = new HashMap<>();
                map.put("Type", "Android");
                String result=  MyHttpHelper.GetUpdata(LoginActivity.this, UrlConfig.Updata, key, map);
                String msg;
                if (!MyStrUtil.isEmpty(result)) {
                    JSONObject jsonObject;
                    try {
                        jsonObject = new JSONObject(result);
                        String code = jsonObject.getString("code");
                        msg = jsonObject.getString("desc");
                        if (code.equals("0")) {
                            JSONObject jsonObject1=new JSONObject(jsonObject.getString("result"));
                            downUrl=jsonObject1.getString("Url");
                            version=jsonObject1.getString("Version");
                            PackageInfo info = getPackageManager().getPackageInfo(
                                   LoginActivity.this.getPackageName(), 0);
                            String versionName = info.versionName;
                            if (!versionName.equals(version)){
                                subscriber.onNext(0);
                            }else {
                                subscriber.onNext(1);
                            }

                        }else {
                            subscriber.onNext(1);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                }else {
                    subscriber.onNext(1);
                }

            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                switch (integer) {
                    case 0:
                        downLoadApk(downUrl,version);
                        break;
                    case 1:
                        inilog();
                        break;
                }
            }
        });

    }

    protected void downLoadApk(final String url, String AppVersion) {
        isUpdata=true;
        deleteApk();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        window.setGravity(Gravity.CENTER);
        lp.alpha = 1f;
        window.setAttributes(lp);
        window.setContentView(R.layout.updata_progress);
        TextView updata_progress_versions = (TextView) window.findViewById(R.id.updata_progress_versions);
        updata_progress_versions.setText("最新版本：v" + AppVersion);
        final NumberProgressBar updata_progress_numberbar = (NumberProgressBar) window.findViewById(R.id.updata_progress_numberbar);
        new Thread() {
            @Override
            public void run() {
                try {
//
                    File file = getFileFromServerForNumberProgressBar(url, "updata.apk", new UpdataApkInterface() {
                        @Override
                        public void getCurrentProcestion(final int data) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    updata_progress_numberbar.setProgress(data);
                                }
                            });
                        }
                    });
                    sleep(300);
                    installApk(file);
                    dialog.dismiss(); //结束掉进度条对话框
                    finish();
                } catch (Exception e) {
                    dialog.dismiss();
                    deleteApk();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "更新失败,请检查网络问题并重新下载",
                                    Toast.LENGTH_LONG).show();
                            finish();
                        }
                    });
                    Log.d("WelcomeActivity", e.toString());
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private File getFileFromServerForNumberProgressBar(String path, String name, UpdataApkInterface apkInterface) throws Exception {
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //获取到文件的大小
            final int contentLength = conn.getContentLength();
//            pd.setMax(conn.getContentLength() / 1024);
            InputStream is = conn.getInputStream();
            File file = new File(Environment.getExternalStorageDirectory(), name);
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            double total = 0;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                total += len;
                //获取当前下载量
                apkInterface.getCurrentProcestion((int) (total / contentLength * 100));
//                activity.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        pd.setProgress(finalTotal / contentLength*100);
//                    }
//                });

            }
            fos.close();
            bis.close();
            is.close();
            return file;
        } else {
            return null;
        }
    }

    //删除之前下载的apk文件
    public void deleteApk() {
        File file = new File(Environment.getExternalStorageDirectory(), "updata.apk");
        if (file.exists()) {
            file.delete();
        }
    }

    //安装apk
    protected void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
    }

    @Override
    protected void initData() {


    }

    private void initViews() {
        mySQLiteOpenHelper = new MySQLiteOpenHelper(LoginActivity.this);
        registered = (TextView) findViewById(R.id.lg_registered);
        backpassworld = (TextView) findViewById(R.id.lg_back);
        login = (TextView) findViewById(R.id.lg_tv_login);
        user_name = (EditText) findViewById(R.id.lg_ed_username);
        password = (EditText) findViewById(R.id.lg_ed_password);
        CheckBox = (CheckBox) findViewById(R.id.lg_radio);


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
        switch (v.getId()) {
            case R.id.lg_registered:
                if (isUpdata){
                    ToastUtils.s(LoginActivity.this,"请升级到最新版本");
                    return;
                }
                Intent intent = new Intent(LoginActivity.this, RegisteredActivity.class);
                startActivity(intent);
                break;
            case R.id.lg_back:
                if (isUpdata){
                    ToastUtils.s(LoginActivity.this,"请升级到最新版本");
                    return;
                }
                Intent intent2 = new Intent(LoginActivity.this, BackPasswordActivity.class);
                startActivity(intent2);
                break;
            case R.id.lg_tv_login:
                if (isUpdata){
                    ToastUtils.s(LoginActivity.this,"请升级到最新版本");
                    return;
                }
                remenber();
                inilog();

                break;

        }
    }

    private void inilog() {
        String mname = user_name.getText().toString();
        String mpwd = password.getText().toString();
        if (TextUtils.isEmpty(mname)) {
            Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_LONG).show();

        } else if (TextUtils.isEmpty(mpwd)) {

            Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_LONG).show();
        } else {
            showLoadDialog();
            HttpClientHelper.getInstance().loginUser(mname, mpwd, new HttpSubseiber.ResponseHandler<LogBen>() {
                @Override
                public void onSucceed(LogBen data) {
                    dismisDialog();
                    PreferenceUtils.getInstance(LoginActivity.this).saveString(UserConfig.UserName, data.getUName());
                    PreferenceUtils.getInstance(LoginActivity.this).saveString(UserConfig.UserId, data.getID());
                    PreferenceUtils.getInstance(LoginActivity.this).saveString(UserConfig.RealName, data.getUNum());
                    PreferenceUtils.getInstance(LoginActivity.this).saveString(UserConfig.HeadImg, data.getHeadImg());
                    PreferenceUtils.getInstance(LoginActivity.this).saveString(UserConfig.Sex, data.getSex());
                    PreferenceUtils.getInstance(LoginActivity.this).saveString(UserConfig.Address, data.getAddr());
                    PreferenceUtils.getInstance(LoginActivity.this).saveString(UserConfig.Phone, data.getMobileNum());
                    PreferenceUtils.getInstance(LoginActivity.this).saveString(UserConfig.SFZH, data.getSFZH());
                    PreferenceUtils.getInstance(LoginActivity.this).saveString(UserConfig.Age, data.getAge());
                    mConfig.setUserGUID(data.getID());
                    mConfig.setUserName(data.getUName());
                    mConfig.setStoreID(data.getStoreID());
                    String mpwd = password.getText().toString();
                    ContentValues cv = new ContentValues();
                    cv.put(Information.Id, data.getID());
                    cv.put(Information.PositionID, data.getPositionID());
                    cv.put(Information.UName, data.getUName());
                    cv.put(Information.UNum, data.getUNum());
                    cv.put(Information.Sex, data.getSex());
                    cv.put(Information.MobileNum, data.getMobileNum());
                    cv.put(Information.Addr, data.getAddr());
                    cv.put(Information.password, mpwd);
                    PreferenceUtils.getInstance(LoginActivity.this).saveString(UserConfig.Token, data.getPassword());

                    boolean flag = mySQLiteOpenHelper.insertData(cv);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                    finish();

                }

                @Override
                public void onFail(String msg) {
                    Log.e("-----------", "========" + msg);
                    Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_LONG).show();
                    dismisDialog();
                }
            });


        }
    }


    private long mPressedTime = 0;

    @Override
    public void onBackPressed() {
        long mNowTime = System.currentTimeMillis();//获取第一次按键时间
        if ((mNowTime - mPressedTime) > 2000) {//比较两次按键时间差
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mPressedTime = mNowTime;
        } else {//退出程序
            this.finish();
            System.exit(0);
        }
    }


    private void initVPN() {

        Intent intent = VpnService.prepare(this);
        if (intent != null) {
            startActivityForResult(intent, 0);
        } else {
            onActivityResult(0, RESULT_OK, null);
        }

    }

}
