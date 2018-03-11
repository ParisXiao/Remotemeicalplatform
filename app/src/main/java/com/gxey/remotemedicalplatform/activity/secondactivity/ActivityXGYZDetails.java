package com.gxey.remotemedicalplatform.activity.secondactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.activity.BaseActivity;
import com.gxey.remotemedicalplatform.activity.LoginActivity;
import com.gxey.remotemedicalplatform.adapter.XGYZdapter;
import com.gxey.remotemedicalplatform.bean.DianZiBLBean;
import com.gxey.remotemedicalplatform.bean.XiangGuanYZBean;
import com.gxey.remotemedicalplatform.mynetwork.MyHttpHelper;
import com.gxey.remotemedicalplatform.newconfig.UrlConfig;
import com.gxey.remotemedicalplatform.utils.MyStrUtil;
import com.gxey.remotemedicalplatform.utils.ScreenUtils;
import com.gxey.remotemedicalplatform.utils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.gxey.remotemedicalplatform.R.id.toolbar_left_btn;

/**
 * Created by Administrator on 2018-03-03.
 */

public class ActivityXGYZDetails extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.toolbar_mid)
    TextView toolbarMid;
    @BindView(toolbar_left_btn)
    ImageButton toolbarLeftBtn;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.dz_blh)
    TextView dzBlh;
    @BindView(R.id.dz_chuzhen)
    TextView dzChuzhen;
    @BindView(R.id.dz_zhenzhuang)
    TextView dzZhenzhuang;
    @BindView(R.id.dz_yisheng)
    TextView dzYisheng;
    @BindView(R.id.dz_jiwangshi)
    TextView dzJiwangshi;
    @BindView(R.id.dz_gerenshi)
    TextView dzGerenshi;
    @BindView(R.id.dz_jiazushi)
    TextView dzJiazushi;
    @BindView(R.id.dz_tigejc)
    TextView dzTigejc;
    @BindView(R.id.dz_fuzhujc)
    TextView dzFuzhujc;
    @BindView(R.id.dz_zhenduan)
    TextView dzZhenduan;
    @BindView(R.id.dz_tsbz)
    TextView dzTsbz;
    @BindView(R.id.dz_clyj)
    TextView dzClyj;
    @BindView(R.id.dz_bz)
    TextView dzBz;
    @BindView(R.id.dz_shijian)
    TextView dzShijian;
    private XiangGuanYZBean dianZiBLBean=new XiangGuanYZBean();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dzblxq;
    }

    @Override
    protected void initView() {
        toolbarLeftBtn.setVisibility(View.VISIBLE);
        toolbarLeftBtn.setOnClickListener(this);
        toolbarMid.setText("相关详情");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ScreenUtils.setStatusBarLightMode(this, R.color.black);
    }

    @Override
    protected void initData() {
        dianZiBLBean= (XiangGuanYZBean) getIntent().getSerializableExtra("XGYZ");
        getData(dianZiBLBean.getID());
    }
    String msg;

    private void getData(final String id) {

        Observable.create(new Observable.OnSubscribe<Integer>() {

            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (MyHttpHelper.isConllection(ActivityXGYZDetails.this)) {
                    String[] key = new String[]{"id"};

                    Map<String, String> map = new HashMap<String, String>();

                    map.put("id",id);
                    String result = MyHttpHelper.GetMessage(ActivityXGYZDetails.this, UrlConfig.SelCorrelationHospitalExhort_Img, key, map);
                    if (!MyStrUtil.isEmpty(result)) {
                        JSONObject jsonObject;
                        try {
                            jsonObject = new JSONObject(result);
                            String code = jsonObject.getString("code");
                            msg = jsonObject.getString("desc");
                            if (code.equals("0")) {
//                                成功
                                JSONArray jsonArray = new JSONArray(jsonObject.getString("result"));
                                if (jsonArray.length() > 0) {
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        XiangGuanYZBean Bean = new XiangGuanYZBean();
                                        JSONObject temp = (JSONObject) jsonArray.get(i);
                                        Bean.setID(temp.getString("ID"));
                                        Bean.setCaseNumber(temp.getString("CaseNumber"));
                                        Bean.setThePatientId(temp.getString("ThePatientId"));
                                        Bean.setDoctorId(temp.getString("DoctorId"));
                                        Bean.setMainSuit(temp.getString("MainSuit"));
                                        Bean.setHistoryOfPresentIllness(temp.getString("HistoryOfPresentIllness"));
                                        Bean.setHistoryOfPastIllness(temp.getString("HistoryOfPastIllness"));
                                        Bean.setHandlingSuggestion(temp.getString("HandlingSuggestion"));
                                        Bean.setPersonalHistory(temp.getString("PersonalHistory"));
                                        Bean.setFHx(temp.getString("FHx"));
                                        Bean.setPhysicalExamination(temp.getString("PhysicalExamination"));
                                        Bean.setDiagnosis(temp.getString("Diagnosis"));
//                                        Bean.setPeriod(temp.getInt("Period"));
                                        Bean.setSpecial(temp.getString("Special"));
                                        Bean.setRemarks(temp.getString("Remarks"));
                                        Bean.setBoardingTime(temp.getString("BoardingTime"));
                                        Bean.setAdministrativeOrTechnicalOfficesid(temp.getString("AdministrativeOrTechnicalOfficesid"));
//                                        list.add(Bean);

                                    }
                                    subscriber.onNext(1);
                                } else {
                                    subscriber.onNext(0);
                                }
                            } else if (code.equals("1")) {
                                subscriber.onNext(4);
//                                离线
                            } else {
//                                失败
                                subscriber.onNext(2);
                            }

                        } catch (JSONException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                } else {
                    subscriber.onNext(3);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                switch (integer) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        ToastUtils.s(ActivityXGYZDetails.this, msg);
                        break;
                    case 3:
                        break;
                    case 4:
                        ToastUtils.s(ActivityXGYZDetails.this, msg);
                        Intent intent = new Intent(ActivityXGYZDetails.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
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
