package com.gxey.remotemedicalplatform.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.adapter.CommonAdapter;
import com.gxey.remotemedicalplatform.adapter.ViewHolder;
import com.gxey.remotemedicalplatform.common.ApiConstant;
import com.gxey.remotemedicalplatform.javaben.DepartmentBen;
import com.gxey.remotemedicalplatform.javaben.DoctorEntity;
import com.gxey.remotemedicalplatform.javaben.WDoctorEntity;
import com.gxey.remotemedicalplatform.network.HttpClientHelper;
import com.gxey.remotemedicalplatform.network.HttpSubseiber;
import com.gxey.remotemedicalplatform.network.SendPushSigleR;
import com.gxey.remotemedicalplatform.utils.AndroidUtil;
import com.makeramen.roundedimageview.RoundedImageView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import fr.pchab.webrtcclient.SignalaUtils;


/**
 * Created by xusongsong on 2016/12/22.
 * 申请医疗
 */

public class MedicalApplicationActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.apply)
    TextView apply;

    @BindView(R.id.medical_lv)
    ListView mLV;

    @BindView(R.id.spinner2)
    Spinner mSpinner;
    private String  Type="";// 1.离线，2.全部

    @BindView(R.id.et_search)
    EditText mETSearch;
    @BindView(R.id.tv_search)
    TextView mTVSearch;
    private List<String> strList =new ArrayList<>();

    private CommonAdapter<DepartmentBen>adapter2;
    private CommonAdapter<DoctorEntity> adapter;
    private ArrayAdapter<String> aspnCountries;
    private DoctorEntity entity;
    private  List<DoctorEntity> list = new ArrayList<>();
    @BindView(R.id.tv_doctor_on_line)
     TextView mtVDoctorOnLine;
    @BindView(R.id.tv_doctor_no_line)
     TextView mtVDoctorNOLine;
    @BindView(R.id.tv_doctor_all)
     TextView mtVDoctorALl;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_medical_application;
    }

    @Override
    protected void initView() {
        back.setOnClickListener(this);
        apply.setOnClickListener(this);
        mtVDoctorALl.setOnClickListener(this);
        mtVDoctorNOLine.setOnClickListener(this);
        mtVDoctorOnLine.setOnClickListener(this);
        list.addAll(SendPushSigleR.list);
        mTVSearch.setOnClickListener(this);
        adapter = new CommonAdapter<DoctorEntity>(this,list,R.layout.item_medical_application) {
            @Override
            public void convert(ViewHolder helper, DoctorEntity item) {
                helper.setText(R.id.tv_1,item.getUserName());
                helper.setText(R.id.tv_n3,item.getPosition());
                helper.setText(R.id.tv_n5,item.getDivision());
                helper.setText(R.id.tv_7,item.getSN());
                if(item.isTrue()){
                    helper.setText(R.id.tv_n6,"已选择");
                }else{
                    helper.setText(R.id.tv_n6,"选择");
                }
                View tv = helper.getView(R.id.tv_n6);
                View vPaidui = helper.getView(R.id.tv_pandui);
                if(!TextUtils.isEmpty(Type)){
                    tv.setVisibility(View.INVISIBLE);
                    vPaidui.setVisibility(View.INVISIBLE);
                }else{
                    tv.setVisibility(View.VISIBLE);
                    vPaidui.setVisibility(View.VISIBLE);
                }
                tv.setTag(item);
                tv.setOnClickListener(MedicalApplicationActivity.this);
                RoundedImageView v= (RoundedImageView) helper.getView(R.id.doc_img);
                helper.setImageBitmapUrl(v,item.getHeadImg());



            }
        };

        mLV.setAdapter(adapter);
        mLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MedicalApplicationActivity.this,WebBannerbenActgvity.class);
                intent.putExtra("url", ApiConstant.H5BASE+ApiConstant.ShowDoctor+list.get(position).getConnectionId());
                startActivity(intent);




            }
        });

        loginUser();
        regDoctorList();
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = (String) mSpinner.getSelectedItem();
                mConfig.setDivision(str);
                loginUser();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        iniHttp();

    }

    private void getDoctor(String userName){
        showLoadDialog();
        mHttpHelper.getDoctor(userName, Type, new HttpSubseiber.ResponseHandler<List<WDoctorEntity>>() {
            @Override
            public void onSucceed(List<WDoctorEntity> data) {
                dismisDialog();
                list.clear();
                for(WDoctorEntity entity:data){
                    DoctorEntity doctorEntity = new DoctorEntity();
                    doctorEntity.setUserName(entity.getUName());
                    doctorEntity.setDivision(entity.getManageDeptName());
                    doctorEntity.setHeadImg(entity.getHeadImg());
                    doctorEntity.setConnectionId(entity.getID());
                    doctorEntity.setPosition(entity.getPositionName());
                    list.add(doctorEntity);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFail(String msg) {
                dismisDialog();
                AndroidUtil.showToast(MedicalApplicationActivity.this,msg,0);
            }
        });
    }




    private void iniHttp() {
        HttpClientHelper.getInstance().GetOrganization(new HttpSubseiber.ResponseHandler<List<DepartmentBen>>() {
            @Override
            public void onSucceed(List<DepartmentBen> data) {
                strList.clear();
                for(DepartmentBen departmentBen:data){
                    strList.add(departmentBen.getName());
                }
                aspnCountries = new ArrayAdapter<String>(MedicalApplicationActivity.this,
                        R.layout.item_simple_spinner_ite, strList);
                mSpinner.setAdapter(aspnCountries);
                for(int i=0;i<strList.size();i++){
                    if(strList.get(1).equals(mConfig.getDivision())){
                        mSpinner.setSelection(1);
                        break;
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    private MyBroadCastReceiver myBroadCastReceiver;
    private void regDoctorList(){
        myBroadCastReceiver  = new MyBroadCastReceiver();
        IntentFilter intentFilter = new IntentFilter(SendPushSigleR.DOCTORLISTCHEOSE);
        registerReceiver(myBroadCastReceiver,intentFilter);
    }




    class MyBroadCastReceiver extends BroadcastReceiver{


        @Override
        public void onReceive(Context context, Intent intent) {
            list.clear();
            list.addAll(SendPushSigleR.list);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // SignalaUtils.getInstance(this).getHub().
        if(myBroadCastReceiver!=null){
            unregisterReceiver(myBroadCastReceiver);
        }
    }

    /**
     * 登录
     */
    public void loginUser(){
        JSONArray jsonArray=new JSONArray();

        JSONObject json = new JSONObject();
        try {
            json.put("IEGUID",mConfig.getDeviceId());
            json.put("Username",mConfig.getUserName());
            json.put("UserType","1");
            json.put("GUID",mConfig.getUserGUID());
            json.put("Division", mConfig.getDivision());
            json.put("Position","会员");
            json.put("StoreID",mConfig.getStoreID());
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonArray.put(json);

        SignalaUtils.getInstance(this).sendMessage("sendLogin",jsonArray);
    }
    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.apply:
                entity = getSelectDoctor();
                if(entity!=null){
                    Intent intent = new Intent(MedicalApplicationActivity.this,OverConsultationActivity.class);
                    intent.putExtra("entity",entity);
                    startActivity(intent);
                }else{
                    AndroidUtil.showToast(this,"请选择医生",0);

                }
                break;
            case R.id.tv_n6:
                initSelectDoctor();
                entity = (DoctorEntity) v.getTag();
                entity.setTrue(true);
                adapter.notifyDataSetChanged();
                break;
            case R.id.tv_search:
               String search =  mETSearch.getText().toString();
                if(!TextUtils.isEmpty(search)){
                    if(TextUtils.isEmpty(Type)){
                        List<DoctorEntity> temp = new ArrayList<>();
                        Pattern pn = Pattern.compile(search+"\\w|\\w"+search+"\\w|\\w"+search);
                        Matcher mr = null;
                        for(DoctorEntity entity:list){
                            mr = pn.matcher(entity.getUserName());
                            if (mr.find()){
                                temp.add(entity);
                            }
                        }
                        list.clear();
                        list.addAll(temp);
                        adapter.notifyDataSetChanged();
                    }else{

                        getDoctor(search);

                    }

                }else{
                    AndroidUtil.showToast(this,"搜索内容不能为空",0);

                }


                break;

            case R.id.tv_doctor_on_line:
                list.clear();
                list.addAll(SendPushSigleR.list);
                adapter.notifyDataSetChanged();
                Type="";
                break;

            case R.id.tv_doctor_no_line:
                Type="1";
                getDoctor("");
                break;

            case R.id.tv_doctor_all:
                Type="2";
                getDoctor("");
                break;


        }
    }

    private void initSelectDoctor(){
        for(DoctorEntity entity:list){
            entity.setTrue(false);
        }
    }

    private DoctorEntity getSelectDoctor(){

        for(DoctorEntity entity:list){
            if(entity.isTrue()){
                return entity;
            }
        }
        return null;
    }


}
