package com.gxey.remotemedicalplatform.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.network.HttpClientHelper;
import com.gxey.remotemedicalplatform.network.HttpSubseiber;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xusongsong on 2016/12/21.
 * 注册界面
 */

public class RegisteredActivity extends BaseActivity {
    @BindView(R.id.tv_title_name)
    TextView mTVTitleName;
    @BindView(R.id.re_username)
    TextView reUsername;
    @BindView(R.id.re_ed_username)
    EditText reEdUsername;
    @BindView(R.id.re_line1)
    TextView reLine1;
    @BindView(R.id.re_name)
    TextView reName;
    @BindView(R.id.re_ed_name)
    EditText reEdName;
    @BindView(R.id.re_line2)
    TextView reLine2;
    @BindView(R.id.re_six)
    TextView reSix;
    @BindView(R.id.re_radio_man)
    RadioButton reRadioMan;
    @BindView(R.id.re_radio_wuman)
    RadioButton reRadioWuman;
    @BindView(R.id.re_pihon)
    TextView rePihon;
    @BindView(R.id.re_ed_pihon)
    EditText reEdPihon;
    @BindView(R.id.re_phion_validation)
    TextView rePhionValidation;
    @BindView(R.id.re_line3)
    TextView reLine3;
    @BindView(R.id.re_validation)
    TextView reValidation;
    @BindView(R.id.re_ed_validation)
    EditText reEdValidation;
    @BindView(R.id.re_number)
    TextView reNumber;
    @BindView(R.id.re_number2)
    TextView reNumber2;
    @BindView(R.id.re_number3)
    TextView reNumber3;
    @BindView(R.id.re_line4)
    TextView reLine4;
    @BindView(R.id.re_radio_ok)
    CheckBox reRadioOk;
    @BindView(R.id.re_tv_registered)
    TextView reTvRegistered;
    @BindView(R.id.agreement)
    TextView agreement;
    @BindView(R.id.ll_re_number2)
    LinearLayout ll_re_number2;
    @BindView(R.id.rg_six)
    RadioGroup mRGSix;
    @BindView(R.id.re_ed_pass)
    EditText pass;
    private TimeCount time;
    private String six;
    private String six1;

    private boolean isOK = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_registered;
    }

    @Override
    protected void initView() {

        mTVTitleName.setText("注册");
        time = new TimeCount(60000, 1000);

        rePhionValidation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(reEdPihon.getText().toString())){
                    Toast.makeText(RegisteredActivity.this,"请输入手机号码",Toast.LENGTH_LONG).show();
                    return;
                }if (reEdPihon.getText().toString().length()!=11){
                    Toast.makeText(RegisteredActivity.this,"请输入正确的手机号码",Toast.LENGTH_LONG).show();
                    return;
                }
                getVerificationCode();

            }
        });

        mRGSix.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton) findViewById(i);
                six = (String) radioButton.getText();
                if (six.equals("男")){
                    six1 = "M";
                }else {
                    six1 = "W";
                }
            }
        });
        reRadioOk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    isOK = true;
                }else {
                    isOK = false;
                }
            }
        });

        agreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisteredActivity.this,WebAgreementActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * 注册接口
     */
    public void getRegistered() {

        HttpClientHelper.getInstance().getRegistered(
                reEdPihon.getText().toString(),
                pass.getText().toString(),
                six1,
                reEdUsername.getText().toString(),
                reEdName.getText().toString(),
                reEdValidation.getText().toString(), new HttpSubseiber.ResponseHandler<String>() {
            @Override
            public void onSucceed(String data) {

                Toast.makeText(RegisteredActivity.this, "注册成功",Toast.LENGTH_LONG).show();
                finish();

            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(RegisteredActivity.this,"注册失败",Toast.LENGTH_LONG).show();

            }
        });
    }

    /**
     * 获取验证码
     */
    public void getVerificationCode() {


        HttpClientHelper.getInstance().getVerificationCode(reEdPihon.getText().toString(), new HttpSubseiber.ResponseHandler<String>() {


            @Override
            public void onSucceed(String data) {

                Toast.makeText(RegisteredActivity.this,"验证码获取成功！",Toast.LENGTH_LONG).show();
                time.start();

            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(RegisteredActivity.this,"验证码获取失败",Toast.LENGTH_LONG).show();

            }
        });
    }


    @OnClick({ R.id.re_tv_registered})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.re_tv_registered://注册

                if(TextUtils.isEmpty(reEdUsername.getText().toString())){
                    Toast.makeText(RegisteredActivity.this,"请输入用户名",Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(pass.getText().toString())){
                    Toast.makeText(RegisteredActivity.this,"请输入密码",Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(reEdName.getText().toString())){
                    Toast.makeText(RegisteredActivity.this,"请输入会员姓名",Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(six1)){
                    Toast.makeText(RegisteredActivity.this,"请选择性别",Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(reEdPihon.getText().toString())){
                    Toast.makeText(RegisteredActivity.this,"请输入手机号码",Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(reEdValidation.getText().toString())){
                    Toast.makeText(RegisteredActivity.this,"请输入验证码",Toast.LENGTH_LONG).show();
                    return;
                }
                if (isOK == false){
                Toast.makeText(RegisteredActivity.this,"请同意医疗服务协议协议",Toast.LENGTH_LONG).show();
                    return;
                }


                getRegistered();

                break;
        }
    }


    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {// 计时完毕
            rePhionValidation.setText("重新获取验证码");
            rePhionValidation.setClickable(true);
            ll_re_number2.setVisibility(View.GONE);
            rePhionValidation.setBackgroundDrawable(getResources().getDrawable(R.drawable.hometext3hape));
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            rePhionValidation.setText("重新获取验证码");
            ll_re_number2.setVisibility(View.VISIBLE);
            rePhionValidation.setClickable(false);//防止重复点击
            rePhionValidation.setBackgroundColor(getResources().getColor(R.color.gray));
            reNumber2.setText(millisUntilFinished / 1000 + "");
        }
    }
}
