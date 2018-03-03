package com.gxey.remotemedicalplatform.activity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.db.MySQLiteOpenHelper;
import com.gxey.remotemedicalplatform.javaben.MyPersonBen;
import com.gxey.remotemedicalplatform.javaben.StoresBen;
import com.gxey.remotemedicalplatform.model.ApiModel;
import com.gxey.remotemedicalplatform.network.HttpClientHelper;
import com.gxey.remotemedicalplatform.network.HttpSubseiber;
import com.gxey.remotemedicalplatform.utils.AndroidUtil;
import com.gxey.remotemedicalplatform.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.iwf.photopicker.PhotoPicker;

/**
 * Created by xusongsong on 2016/12/24.
 * 修改个人信息
 */

public class PersonalInformationActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.xg)
    TextView changpass;
    @BindView(R.id.mendian)
    TextView mendian;
    @BindView(R.id.mendianid)
    TextView mendianid;
    @BindView(R.id.re_tv_registered)
    TextView ok;
    @BindView(R.id.yongmuming)
    EditText yonghuming;
    @BindView(R.id.huiyaun)
    EditText huiyaun;
    @BindView(R.id.nicheng)
    EditText nicheng;
    @BindView(R.id.gerenjianjei)
    EditText gerenjianjei;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.iv_1)
    ImageView mIV1;
    @BindView(R.id.iv_2)
    ImageView mIV2;
    @BindView(R.id.iv_3)
    ImageView mIV3;
    @BindView(R.id.rg_six)
    RadioGroup mRGSix;
    @BindView(R.id.re_radio_man)
    RadioButton radio_man;
    @BindView(R.id.re_radio_wuman)
    RadioButton radio_wuman;

    private String path;
    private String password;
    private String number = null;

    private String sex;
    private String UNum;
    private String UName;
    private String UCode;
    private String patn2;
    private String patn3;
    private String patn4;
    private String mynicheng;
    private String six;
    private String myjj;
    private String id;
    private String id2;
    private String myStoreID;
    private String myParentId;
    private String jianjie;

    private String MobileNum;
    ApiModel api;

    private boolean isPermissions = false;
    public static final int REQUEST_CODE = 1;
    private List<StoresBen> list = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_information;
    }

    @Override
    protected void initView() {
        changpass.setOnClickListener(this);
        mendian.setOnClickListener(this);
        ok.setOnClickListener(this);
        back.setOnClickListener(this);
        mIV1.setOnClickListener(this);
        mIV2.setOnClickListener(this);
        mIV3.setOnClickListener(this);

        showLoadDialog();
        inisql();


        mRGSix.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton) findViewById(i);
                six = (String) radioButton.getText();
                if (six.equals("男")) {
                    sex = "M";
                } else {
                    sex = "W";
                }
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inipren();
        mystro();
    }

    //获取个人信息
    private void inipren() {

        HttpClientHelper.getInstance().GetMember(mConfig.getUserGUID(), new HttpSubseiber.ResponseHandler<MyPersonBen>() {
            @Override
            public void onSucceed(MyPersonBen data) {
                dismisDialog();

                yonghuming.setText(data.getUNum());
                huiyaun.setText(data.getUNum());
                gerenjianjei.setText(data.getIntroduce());
                myStoreID = data.getStoreID();
                nicheng.setText(data.getUName());
                myParentId = data.getStoreID();
                id = data.getStoreID();
                if (!TextUtils.isEmpty(data.getImages())) {
                    String[] imgs = data.getImages().split(",");
                    for (int i = 0; i < imgs.length; i++) {
                        if (i == 0) {
                            patn2 = imgs[i];
                            ImageUtils.load(PersonalInformationActivity.this, imgs[i], mIV1);
                        } else if (i == 1) {
                            patn3 = imgs[i];
                            ImageUtils.load(PersonalInformationActivity.this, imgs[i], mIV2);
                        } else if (i == 2) {
                            patn4 = imgs[i];
                            ImageUtils.load(PersonalInformationActivity.this, imgs[i], mIV3);
                        }
                    }

                }


                ImageUtils.load(PersonalInformationActivity.this, data.getHeadImg(), mIV1);

                if (data.getSex().equals("M")) {
                    radio_man.setChecked(true);

                } else {

                    radio_wuman.setChecked(true);
                }

            }

            @Override
            public void onFail(String msg) {
                dismisDialog();
            }
        });
    }


    //获取药房信息
    private void mystro() {
        HttpClientHelper.getInstance().

                stores(new HttpSubseiber.ResponseHandler<List<StoresBen>>() {
                           @Override
                           public void onSucceed(List<StoresBen> data) {
                               list.addAll(data);

                              Log.e("----------------", list.size()+"=============");
                               for(int i = 0 ;i < list.size(); i++){
                                   Log.e("----------------", list.get(i).getId()+"=============");
                                   if ( myParentId.equals(list.get(i).getId()) )
                                   mendianid.setText(list.get(i).getName());
                               }

                           }

                           @Override
                           public void onFail(String msg) {

                           }
                       }

                );
    }

    private void inisql() {
        MySQLiteOpenHelper oh = new MySQLiteOpenHelper(this);
        SQLiteDatabase db = oh.getWritableDatabase();
        Cursor cursor = db.query("Information", null, null, null, null, null, null);
        if (cursor.moveToFirst()){
//            UNum = cursor.getString(cursor.getColumnIndex("UNum"));
//            Id = cursor.getString(cursor.getColumnIndex("Id"));
//            UName = cursor.getString(cursor.getColumnIndex("UName"));
            password = cursor.getString(cursor.getColumnIndex("password"));
//            sex = cursor.getString(cursor.getColumnIndex("Sex"));
            MobileNum = cursor.getString(cursor.getColumnIndex("MobileNum"));
//            if (sex=="M"){
//                mRGSix.check(R.id.re_radio_man);
//            }else if(sex=="W"){
//                mRGSix.check(R.id.re_radio_wuman);
//            }

//            yonghuming.setText(UNum);
//            huiyaun.setText(UName);
        }


    }

    @Override
    protected void initData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.xg :
                Intent intent = new Intent(PersonalInformationActivity.this,ChangePasswordActivity.class);
                startActivity(intent);
            break;
            case R.id.mendian :
                Intent intent2 = new Intent(PersonalInformationActivity.this,storesActivity.class);

                startActivityForResult(intent2, REQUEST_CODE);
                break;
            case R.id.re_tv_registered :
                initperson();
                break;
            case R.id.back :
                finish();
                break;
            case R.id.iv_1:
                number="0";
                statrSelect();

                break;
            case R.id.iv_2:
                number="1";
                statrSelect();
                break;
            case R.id.iv_3:
                number="2";
                statrSelect();
                break;

        }
    }

    private void statrSelect(){
        PhotoPicker.builder()
                .setPhotoCount(1)
                .setShowCamera(true)
                .setShowGif(false)
                .setPreviewEnabled(false)
                .start(this, PhotoPicker.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
                if (data != null) {
                    ArrayList<String> photos =
                            data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                    mHttpHelper.upLoadImg(photos.get(0), new HttpSubseiber.ResponseHandler<String>() {
                        @Override
                        public void onSucceed(String data) {

                            setImage(data);
                        }

                        @Override
                        public void onFail(String msg) {

                            AndroidUtil.showToast(PersonalInformationActivity.this,msg,0);
                        }
                    });

                }



        }
        if (resultCode==REQUEST_CODE){

                Bundle bundle = data.getExtras();
                String name = bundle.getString("name");
                 id = bundle.getString("ParentId");
            Log.d("-------huilai",id+"=====");
//                String name = intent.getStringExtra("name");

                mendianid.setText(name);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setImage(String url){
        if (number.equals("0")){
            patn2=url;
            ImageUtils.load(this,url,mIV1);
        }
        if (number.equals("1")){
            patn3=url;
            ImageUtils.load(this,url,mIV2);
        }
        if (number.equals("2")){
            patn4=url;
            ImageUtils.load(this,url,mIV3);
        }
    }

    public void initperson(){
        showLoadDialog();

        Log.e("------上传",id+"=======");
         jianjie = gerenjianjei.getText().toString();
         UName = nicheng.getText().toString();
         UNum = yonghuming.getText().toString();
//        UCode = nicheng.getText().toString();
        String url="";
        if(!TextUtils.isEmpty(patn2)){
            url=url+patn2+",";
        }

        if(!TextUtils.isEmpty(patn3)){
            url=url+patn3+",";
        }
        if(!TextUtils.isEmpty(patn4)){
            url=url+patn4+",";
        }
        if(!TextUtils.isEmpty(url)){
            url = url.substring(0,url.length()-1);
        }
        HttpClientHelper.getInstance().person(mConfig.getUserGUID(),MobileNum, password, sex, UName, UNum, jianjie, url, id, new HttpSubseiber.ResponseHandler<String>() {
            @Override
            public void onSucceed(String data) {
                Toast.makeText(PersonalInformationActivity.this,"修改成功" ,Toast.LENGTH_LONG);
                  dismisDialog();
                dialog();
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(PersonalInformationActivity.this,"修改失败",Toast.LENGTH_LONG);
                  dismisDialog();
            }
        });

    }
    private void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PersonalInformationActivity.this);
        builder.setTitle("提示");
        builder.setMessage("修改成功是否退出?"); //设置内容
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss(); //关闭dialog
                finish();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();

    }

}
