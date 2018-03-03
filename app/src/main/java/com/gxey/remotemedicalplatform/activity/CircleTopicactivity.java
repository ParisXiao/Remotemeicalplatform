package com.gxey.remotemedicalplatform.activity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.db.MySQLiteOpenHelper;
import com.gxey.remotemedicalplatform.network.HttpClientHelper;
import com.gxey.remotemedicalplatform.network.HttpSubseiber;
import com.gxey.remotemedicalplatform.utils.AndroidUtil;
import com.gxey.remotemedicalplatform.utils.ImageUtils;
import java.util.ArrayList;
import butterknife.BindView;
import me.iwf.photopicker.PhotoPicker;

/**
 * Created by xusongsong on 2016/12/24.
 * 圈子话题发表
 */

public class CircleTopicactivity extends BaseActivity  implements View.OnClickListener{
    @BindView(R.id.release)
    TextView release;
    @BindView(R.id.delete1)
    TextView delete1;
    @BindView(R.id.delete2)
    TextView delete2;
    @BindView(R.id.delete3)
    TextView delete3;
    @BindView(R.id.name)
    EditText mname;
    @BindView(R.id.title)
    EditText mtitle;
    @BindView(R.id.content)
    EditText mcontent;
    @BindView(R.id.img1)
    ImageView mimg1;
    @BindView(R.id.img2)
    ImageView mimg2;
    @BindView(R.id.img3)
    ImageView mimg3;
    @BindView(R.id.back)
    ImageView back;
    private String number=null;
    private String MobileNum;
    private String patn2;
    private String patn3;
    private String patn4;
    private String UNum;
    private String UName;
    private String Id;
    ActivityImageChoose menuWindow;
    private  String circleId;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_circle_topic;
    }

    @Override
    protected void initView() {
        circleId= getIntent().getStringExtra("circleId");
        release.setOnClickListener(this);
        mimg1.setOnClickListener(this);
        mimg2.setOnClickListener(this);
        mimg3.setOnClickListener(this);
        delete3.setOnClickListener(this);
        delete2.setOnClickListener(this);
        delete1.setOnClickListener(this);
        back.setOnClickListener(this);
        inisql();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        String title = mtitle.getText().toString();
        String name = mname.getText().toString();
        String content = mcontent.getText().toString();
        inisql();
        switch (v.getId()){

            case R.id.release:
               if (TextUtils.isEmpty(name)){
                   Toast.makeText(CircleTopicactivity.this,"发布人不能为空",Toast.LENGTH_LONG).show();
               }else if (TextUtils.isEmpty(title)){
                   Toast.makeText(CircleTopicactivity.this,"标题不能为空",Toast.LENGTH_LONG).show();
               }else if (TextUtils.isEmpty(content)){
                   Toast.makeText(CircleTopicactivity.this,"内容不能为空",Toast.LENGTH_LONG).show();
               }else {
                   uprelease();

               }

            break;
            case R.id.back:
                finish();
                break;

            case R.id.img1:
                number="0";
                statrSelect();

                break;
            case R.id.img2:
                number="1";
                statrSelect();
                break;
            case R.id.img3:
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

                        AndroidUtil.showToast(CircleTopicactivity.this,msg,0);
                    }
                });

            }


        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void setImage(String url){
        if (number.equals("0")){
            patn2=url;
            ImageUtils.load(this,url,mimg1);
        }
        if (number.equals("1")){
            patn3=url;
            ImageUtils.load(this,url,mimg2);
        }
        if (number.equals("2")){
            patn4=url;
            ImageUtils.load(this,url,mimg3);
        }
    }


    private void inisql() {
        MySQLiteOpenHelper oh = new MySQLiteOpenHelper(this);
        SQLiteDatabase db = oh.getWritableDatabase();
        Cursor cursor = db.query("Information", null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            UNum = cursor.getString(cursor.getColumnIndex("UNum"));
            Id = cursor.getString(cursor.getColumnIndex("Id"));
            UName = cursor.getString(cursor.getColumnIndex("UName"));
            MobileNum = cursor.getString(cursor.getColumnIndex("MobileNum"));
            Log.d("--------",""+UName);
            mname.setText(UName);
        }


    }

    private void uprelease() {
        String title = mtitle.getText().toString();
        String content = mcontent.getText().toString();

        String url = "";
        if (!TextUtils.isEmpty(patn2)) {
            url = url + patn2 + ",";
        }

        if (!TextUtils.isEmpty(patn3)) {
            url = url + patn3 + ",";
        }
        if (!TextUtils.isEmpty(patn4)) {
            url = url + patn4 + ",";
        }
        if (!TextUtils.isEmpty(url)) {
            url = url.substring(0, url.length() - 1);
        }


        HttpClientHelper.getInstance().release(circleId, url, title, UName, Id, content, new HttpSubseiber.ResponseHandler<String>() {

            @Override
            public void onSucceed(String data) {
                Toast.makeText(CircleTopicactivity.this, "发布成功", Toast.LENGTH_LONG).show();

                dialog();
            }


            @Override
            public void onFail(String msg) {
                Toast.makeText(CircleTopicactivity.this, "发布失败", Toast.LENGTH_LONG).show();
            }
        });

    }


    private void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(CircleTopicactivity.this);
        builder.setTitle("提示");
        builder.setMessage("发布成功是否退出?"); //设置内容
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
