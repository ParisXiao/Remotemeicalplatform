package com.gxey.remotemedicalplatform.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.activity.secondactivity.ActivityDoctorList;
import com.gxey.remotemedicalplatform.activity.secondactivity.ActivityFenZhen;
import com.gxey.remotemedicalplatform.activity.secondactivity.ActivityHeathDangAn;
import com.gxey.remotemedicalplatform.activity.secondactivity.ActivityHeathDianZi;
import com.gxey.remotemedicalplatform.javaben.DoctorEntity;
import com.gxey.remotemedicalplatform.network.SendPushSigleR;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xusongsong on 2016/12/21.
 */

public class HealthManagementFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.health_jkda)
    LinearLayout healthJkda;
    @BindView(R.id.health_yczl)
    LinearLayout healthYczl;
    @BindView(R.id.health_dzbl)
    LinearLayout healthDzbl;
    @BindView(R.id.health_fzzz)
    LinearLayout healthFzzz;
    Unbinder unbinder;
    private TextView yc;
    private TextView jiankang;


    private TextView mTVDoctorNUmber;
    private TextView mTVDotor;


    public static HealthManagementFragment newInstance(String param1) {
        HealthManagementFragment fragment = new HealthManagementFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public HealthManagementFragment() {

    }

    private void init() {
//        yc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), MedicalApplicationActivity.class);
//                startActivity(intent);
//            }
//        });
//        jiankang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), WebHealthRecordsActivity.class);
//                startActivity(intent);
//            }
//        });
//        upDateDoctorInfo();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_healthmanagement;
    }

    @Override
    protected void initView(View view) {
//        jiankang = (TextView)view.findViewById(R.id.jiankang);
        init();
        regDoctorList();
    }

    private MyBroadCastReceiver myBroadCastReceiver;

    private void regDoctorList() {
        myBroadCastReceiver = new MyBroadCastReceiver();
        IntentFilter intentFilter = new IntentFilter(SendPushSigleR.DOCTORLISTCHEOSE);
        getActivity().registerReceiver(myBroadCastReceiver, intentFilter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.health_jkda:
                Intent intent=new Intent(getActivity(), ActivityHeathDangAn.class);
                startActivity(intent);
                break;
            case R.id.health_dzbl:
                Intent intent1=new Intent(getActivity(), ActivityHeathDianZi.class);
                startActivity(intent1);
                break;
            case R.id.health_yczl:
                Intent intent2=new Intent(getActivity(), ActivityDoctorList.class);
                startActivity(intent2);
                break;
            case R.id.health_fzzz:
                Intent intent3=new Intent(getActivity(), ActivityFenZhen.class);
                startActivity(intent3);
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        healthJkda.setOnClickListener(this);
        healthYczl.setOnClickListener(this);
        healthDzbl.setOnClickListener(this);
        healthFzzz.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    class MyBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            upDateDoctorInfo();
        }
    }

    private void upDateDoctorInfo() {
        mTVDotor.setText(SendPushSigleR.list.size() + "");
        int num = 0;
        for (DoctorEntity entity : SendPushSigleR.list) {
            if (entity.getSN().equals("0")) {
                num++;
            }
        }
        mTVDoctorNUmber.setText(num + "");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (myBroadCastReceiver != null) {
            getActivity().unregisterReceiver(myBroadCastReceiver);
        }

    }

    @Override
    protected void initData() {

    }


}
