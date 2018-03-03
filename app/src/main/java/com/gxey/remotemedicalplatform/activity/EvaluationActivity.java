package com.gxey.remotemedicalplatform.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.network.HttpClientHelper;
import com.gxey.remotemedicalplatform.network.HttpSubseiber;

import butterknife.BindView;

/**
 * Created by xusongsong on 2016/12/24.
 * 视屏诊疗评价
 */

public class EvaluationActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ratingBar)
    RatingBar mRatingBar;
    @BindView(R.id.medi_content)
    EditText mEdiText;
    @BindView(R.id.ok_eva)
    TextView ok_eva;
    @BindView(R.id.back)
    ImageView back;
    private String  star="5.0";
    private String mcontent;
    private String RoomID;
    private String doctorC;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_evaluation;
    }

    @Override
    protected void initView() {
        RoomID = getIntent().getStringExtra("roomID");
        doctorC = getIntent().getStringExtra("doctorc");
        ok_eva.setOnClickListener(this);
        back.setOnClickListener(this);
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                star = rating+"";
            }
        });



    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.ok_eva:
             dismisDialog();
             mcontent = mEdiText.getText().toString();
             if(TextUtils.isEmpty(RoomID)){
                 RoomID = mConfig.getRoomID();
             }

             if (TextUtils.isEmpty(star)){
                 Toast.makeText(EvaluationActivity.this,"请先评价",Toast.LENGTH_LONG).show();

             }else {
                 showLoadDialog();
                   iniHttp();
             }
             break;
         case R.id.back:
             finish();
             break;

     }


    }

    private void iniHttp() {
        HttpClientHelper.getInstance().AddAppraise(RoomID, doctorC,mcontent, star, new HttpSubseiber.ResponseHandler<String>() {
            @Override
            public void onSucceed(String data) {
                dismisDialog();
               // Toast.makeText(EvaluationActivity.this,"评价成功！",Toast.LENGTH_LONG).show();
                dialog();
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(EvaluationActivity.this,"评价失败！",Toast.LENGTH_LONG).show();
                dismisDialog();
            }
        });
    }

    private void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("评论成功是否退出?"); //设置内容
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
