package com.gxey.remotemedicalplatform.activity;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.javaben.BackPasswoedBen;
import com.gxey.remotemedicalplatform.network.HttpClientHelper;
import com.gxey.remotemedicalplatform.network.HttpSubseiber;

import butterknife.BindView;

/**
 * Created by xusongsong on 2016/12/22.
 * 找回密码
 */

public class BackPasswordActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.pass_ed_username)
    EditText user;

    @BindView(R.id.pass_ed_pihon)
    EditText pihon;

    @BindView(R.id.pass_phion_validation)
    TextView validation;

    @BindView(R.id.pass_ed_validation)
    EditText ed_validation;

    @BindView(R.id.new_ed_passworld)
    EditText new_pass;

    @BindView(R.id.new_ed_passworld_ok)
    EditText new_pass_ok;

    @BindView(R.id.re_tv_registered)
    TextView registered;

    @BindView(R.id.pass_number2)
    TextView pass_number2;

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.linear)
    LinearLayout linear;

    private TimeCount time;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_backpassword;
    }

    @Override
    protected void initView() {
        validation.setOnClickListener(this);
        back.setOnClickListener(this);
        registered.setOnClickListener(this);
        time = new BackPasswordActivity.TimeCount(60000, 1000);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
         String name = user.getText().toString();
         String phone = pihon.getText().toString();
         String pass =  new_pass_ok.getText().toString();
         String newpass =  new_pass.getText().toString();
         String captcha = ed_validation.getText().toString();
        switch (v.getId()){
            case R.id.pass_phion_validation:
               if (TextUtils.isEmpty(phone)){
                    Toast.makeText(BackPasswordActivity.this,"手机号不能为空！",Toast.LENGTH_LONG).show();
                }else if (phone.length()!=11){
                    Toast.makeText(BackPasswordActivity.this,"请输入正确的手机号码",Toast.LENGTH_LONG).show();
                } else {
                    getVerificationCode();
                    Log.d("============",name+phone+pass);
                }


                break;
            case R.id.re_tv_registered:
                if (TextUtils.isEmpty(captcha)){
                    Toast.makeText(BackPasswordActivity.this,"验证码不能为空！",Toast.LENGTH_LONG).show();
                }else if (newpass.equals(pass)){
                    showLoadDialog();
                    getbacpass();

                }else {
                    Toast.makeText(BackPasswordActivity.this,"两次密码不同！",Toast.LENGTH_LONG).show();
                }

                break;
              case R.id.back:
                finish();
                break;

        }
    }

    public void getbacpass() {
        String name = user.getText().toString();
        String phone = pihon.getText().toString();
        String pass =  new_pass_ok.getText().toString();
        String captcha = ed_validation.getText().toString();
        HttpClientHelper.getInstance().bacpassword("", phone, pass, captcha, new HttpSubseiber.ResponseHandler<String>() {
            @Override
            public void onSucceed(String data) {
                 dismisDialog();
                Toast.makeText(BackPasswordActivity.this,"密码更改成功！",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(BackPasswordActivity.this,"密码更改失败！",Toast.LENGTH_LONG).show();
                dismisDialog();
            }
        });
    }

    /**
     * 获取验证码
     */
    public void getVerificationCode() {

        HttpClientHelper.getInstance().getVerificationCode(pihon.getText().toString(), new HttpSubseiber.ResponseHandler<String>() {
            @Override
            public void onSucceed(String data) {
                time.start();
                Log.d("=================1","1++++++++++++++++");
                Toast.makeText(BackPasswordActivity.this,"成功获取验证码",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(String msg) {

                Toast.makeText(BackPasswordActivity.this,"获取验证码失败",Toast.LENGTH_LONG);

            }
        });
    }



    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {// 计时完毕
            validation.setText("重新获取验证码");
            validation.setClickable(true);
            linear.setVisibility(View.GONE);
            validation.setBackgroundDrawable(getResources().getDrawable(R.drawable.hometext3hape));
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            validation.setText("重新获取验证码");
            validation.setClickable(false);//防止重复点击
            linear.setVisibility(View.VISIBLE);
            validation.setBackgroundColor(getResources().getColor(R.color.gray));
            pass_number2.setText(millisUntilFinished / 1000 + "");
        }
    }
}
