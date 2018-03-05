package com.gxey.remotemedicalplatform.network;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import com.gxey.remotemedicalplatform.activity.LoginActivity;
import com.gxey.remotemedicalplatform.activity.secondactivity.ActivityMyOverConsultation;
import com.gxey.remotemedicalplatform.javaben.DoctorEntity;
import com.gxey.remotemedicalplatform.model.LocationConfig;
import com.gxey.remotemedicalplatform.utils.ActivityStack;
import com.zsoft.signala.hubs.HubOnDataCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.pchab.webrtcclient.SignalaUtils;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by lanluo on 2017/1/11.
 */

public class SendPushSigleR {
    public static final String DOCTORLISTCHEOSE="com.gxey.doctorlistcheose";

    private Context context;
    public static final List<DoctorEntity> list = new ArrayList<>();
    public  SendPushSigleR(Context context) {
        this.context = context.getApplicationContext();
        initDoctorList();

    }

    private void initDoctorList(){

        SignalaUtils.getInstance(context).getHub().On("updateDoctorListCallBack", new HubOnDataCallback() {
            @Override
            public void OnReceived(JSONArray args) {
                if(args!=null&&args.length()>0){
                    try {
                        args = args.getJSONArray(0);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    list.clear();
                    for(int i=0; i<args.length(); i++)
                    {
                        JSONObject json = null;
                        try {
                            json = args.getJSONObject(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        DoctorEntity entity = new DoctorEntity();
                        try {
                            entity.setConnectionId(json.getString("ConnectionId"));
                            entity.setDivision(json.getString("Division"));
                            entity.setHeadImg(json.getString("HeadImg"));
                            entity.setPosition(json.getString("Position"));
                            entity.setUserName(json.getString("Username"));
                            entity.setSN(json.getString("SN"));
                            entity.setRoomID(json.getString("RoomID"));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        list.add(entity);
                        sendBroadcastReceiver(DOCTORLISTCHEOSE);
                    }


                }
            }
        });



        SignalaUtils.getInstance(context).getHub().On("getMyInfoCallBack", new HubOnDataCallback()
        {
            @Override
            public void OnReceived(JSONArray args) {
                for(int i=0; i<args.length(); i++)
                {
                    try {
                        JSONObject json = args.getJSONObject(i);
                        LocationConfig.getConfig().setConnectionId(json.getString("ConnectionId"));
                        LocationConfig.getConfig().setRoomID(json.getString("RoomID"));
                        LocationConfig.getConfig().setHeadImg(json.getString("HeadImg"));
                        LocationConfig.getConfig().setGUID(json.getString("GUID"));
                        LocationConfig.getConfig().setMStoreID(json.getString("StoreID"));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //Log.d("getMyInfoCallBack",args.opt(i).toString());
                }
            }
        });

        SignalaUtils.getInstance(context).getHub().On("receiveSignal", new HubOnDataCallback() {

            @Override
            public void OnReceived(JSONArray args) {
                if(ActivityMyOverConsultation.client!=null){
                    ActivityMyOverConsultation.client.receivSignal(args);
                }


            }
        });

        SignalaUtils.getInstance(context).getHub().On("updateMsgCallBack", new HubOnDataCallback() {

            @Override
            public void OnReceived(JSONArray args) {
                if(ActivityMyOverConsultation.activity!=null){
                    ActivityMyOverConsultation.activity.getMessage(args);
                }


            }
        });




        SignalaUtils.getInstance(context).getHub().On("updateMsgCallBack", new HubOnDataCallback() {

            @Override
            public void OnReceived(JSONArray args) {
                if(ActivityMyOverConsultation.activity!=null){
                    ActivityMyOverConsultation.activity.getMessage(args);
                }


            }
        });


        SignalaUtils.getInstance(context).getHub().On("acceptMemberCallBack", new HubOnDataCallback() {

            @Override
            public void OnReceived(JSONArray args) {
                if(ActivityMyOverConsultation.activity!=null){
                    ActivityMyOverConsultation.activity.acceptMemberCallBack();
                }


            }
        });

        SignalaUtils.getInstance(context).getHub().On("sendLeaveCallBack", new HubOnDataCallback() {

            @Override
            public void OnReceived(JSONArray args) {
                if(ActivityMyOverConsultation.activity!=null){
                    ActivityMyOverConsultation.activity.isendLeaveCallBack();
                }


            }
        });

        SignalaUtils.getInstance(context).getHub().On("unAcceptMemberCallBack", new HubOnDataCallback() {

            @Override
            public void OnReceived(JSONArray args) {
                if(ActivityMyOverConsultation.activity!=null){
                    ActivityMyOverConsultation.activity.unAcceptMemberCallBack();
                }


            }
        });

        SignalaUtils.getInstance(context).getHub().On("sendCaseDualTGCallBack", new HubOnDataCallback() {

            @Override
            public void OnReceived(JSONArray args) {
                if(ActivityMyOverConsultation.activity!=null){
                    ActivityMyOverConsultation.activity.sendCaseDualCallBack();
                }


            }
        });

        SignalaUtils.getInstance(context).getHub().On("callAccepted", new HubOnDataCallback() {
            @Override
            public void OnReceived(JSONArray args) {
                if(args.length()>0){
                    try {
                        JSONObject jsonObject =  args.getJSONObject(0);
                        String connectionId =jsonObject.getString("ConnectionId");
                        ActivityMyOverConsultation.activity.initSDP(connectionId);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }
        });


        SignalaUtils.getInstance(context).getHub().On("getErrorCallBack", new HubOnDataCallback() {
            @Override
            public void OnReceived(JSONArray args) {
                try{
                    final int i = (int) args.get(0);
                    if(i==2){
                        new AlertDialog.Builder(context).setTitle("提示")
                                .setMessage((String) args.get(2))
                                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                      Intent intent = new Intent(context, LoginActivity.class);
                                        context.startActivity(intent);
                                        SharedPreferences sp = context.getSharedPreferences("saveUserNamePwd",MODE_PRIVATE);
                                        SharedPreferences.Editor edit = sp.edit();
                                        edit.putString("isMemory","no");
                                        edit.commit();
                                        ActivityStack.getInstance().finishAllActivity();
                                    }
                                });


                    }


                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }



    private void sendBroadcastReceiver(String broadcast){
        Intent intent = new  Intent();
        intent.setAction(broadcast);
        //发送无序广播
        context.sendBroadcast(intent);
    }
}
